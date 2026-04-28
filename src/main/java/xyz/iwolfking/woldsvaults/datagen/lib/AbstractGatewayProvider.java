package xyz.iwolfking.woldsvaults.datagen.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractGatewayProvider implements DataProvider {

    protected final DataGenerator generator;
    protected final String modId;
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();

    protected AbstractGatewayProvider(DataGenerator generator, String modId) {
        this.generator = generator;
        this.modId = modId;
    }

    @Override
    public void run(HashCache cache) throws IOException {
        Map<String, JsonObject> outputs = new LinkedHashMap<>();

        registerGateways((name, consumer) -> {
            GatewayBuilder builder = new GatewayBuilder();
            consumer.accept(builder);
            outputs.put(name, builder.build());
        });

        for (var entry : outputs.entrySet()) {
            Path path = generator.getOutputFolder().resolve(
                "data/" + getNamespace() + "/gateways/" + entry.getKey() + ".json"
            );

            DataProvider.save(GSON, cache, entry.getValue(), path);
        }
    }

    private String getNamespace() {
        return modId;
    }

    protected abstract void registerGateways(GatewayRegistrar registrar);

    @Override
    public String getName() {
        return "Gateways: " + getNamespace();
    }

    // Functional interface
    @FunctionalInterface
    public interface GatewayRegistrar {
        void add(String name, Consumer<GatewayBuilder> builder);
    }

    public static class GatewayBuilder {

        private final JsonObject root = new JsonObject();
        private final JsonArray waves = new JsonArray();
        private final JsonArray rewards = new JsonArray();

        public GatewayBuilder size(GatewaySize size) {
            root.addProperty("size", size.getSerialized());
            return this;
        }

        public GatewayBuilder color(String color) {
            root.addProperty("color", color);
            return this;
        }

        public GatewayBuilder completionXp(int xp) {
            root.addProperty("completion_xp", xp);
            return this;
        }

        public GatewayBuilder spawnRange(int range) {
            root.addProperty("spawn_range", range);
            return this;
        }

        public GatewayBuilder wave(Consumer<WaveBuilder> consumer) {
            WaveBuilder wave = new WaveBuilder();
            consumer.accept(wave);
            waves.add(wave.build());
            return this;
        }

        public GatewayBuilder reward(String table, int rolls, String desc) {
            JsonObject obj = new JsonObject();
            obj.addProperty("type", "loot_table");
            obj.addProperty("loot_table", table);
            obj.addProperty("rolls", rolls);
            obj.addProperty("desc", desc);
            rewards.add(obj);
            return this;
        }

        public JsonObject build() {
            root.add("waves", waves);
            root.add("rewards", rewards);
            return root;
        }
    }

    public static class WaveBuilder {

        private final JsonObject wave = new JsonObject();
        private final JsonArray entities = new JsonArray();
        private final JsonArray modifiers = new JsonArray();
        private final JsonArray rewards = new JsonArray();

        public WaveBuilder entity(String id) {
            JsonObject obj = new JsonObject();
            obj.addProperty("entity", id);
            entities.add(obj);
            return this;
        }

        public WaveBuilder entity(ResourceLocation id) {
            return entity(id.toString());
        }

        public WaveBuilder entity(String id, int count) {
            for (int i = 0; i < count; i++) {
                entity(id);
            }
            return this;
        }

        public WaveBuilder entity(ResourceLocation id, int count) {
            return entity(id.toString(), count);
        }

        public WaveBuilder modifier(String attribute, AttributeOperation op, double value) {
            JsonObject obj = new JsonObject();
            obj.addProperty("attribute", attribute);
            obj.addProperty("operation", op.getSerialized());
            obj.addProperty("value", value);
            modifiers.add(obj);
            return this;
        }

        public WaveBuilder reward(String table, int rolls, String desc) {
            JsonObject obj = new JsonObject();
            obj.addProperty("type", "loot_table");
            obj.addProperty("loot_table", table);
            obj.addProperty("rolls", rolls);
            obj.addProperty("desc", desc);
            rewards.add(obj);
            return this;
        }

        public WaveBuilder maxWaveTime(int time) {
            wave.addProperty("max_wave_time", time);
            return this;
        }

        public WaveBuilder setupTime(int time) {
            wave.addProperty("setup_time", time);
            return this;
        }

        public JsonObject build() {
            wave.add("entities", entities);
            wave.add("modifiers", modifiers);
            wave.add("rewards", rewards);
            return wave;
        }
    }

    public enum GatewaySize {
        SMALL("small"),
        MEDIUM("medium"),
        LARGE("large");

        private final String serialized;

        GatewaySize(String serialized) {
            this.serialized = serialized;
        }

        public String getSerialized() {
            return serialized;
        }
    }

    public enum AttributeOperation {
        ADDITION("ADDITION"),
        MULTIPLY_BASE("MULTIPLY_BASE"),
        MULTIPLY_TOTAL("MULTIPLY_TOTAL");

        private final String serialized;

        AttributeOperation(String serialized) {
            this.serialized = serialized;
        }

        public String getSerialized() {
            return serialized;
        }
    }

}