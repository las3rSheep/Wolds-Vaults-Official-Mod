package xyz.iwolfking.woldsvaults.config;

import com.google.gson.*;
import com.google.gson.annotations.Expose;
import iskallia.vault.VaultMod;
import iskallia.vault.config.Config;
import iskallia.vault.core.util.WeightedList;
import iskallia.vault.core.vault.influence.VaultGod;
import iskallia.vault.gear.charm.CharmEffect;
import iskallia.vault.gear.charm.CharmEffectRegistry;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;
import xyz.iwolfking.woldsvaults.attributes.AttributeAmulet;
import xyz.iwolfking.woldsvaults.items.gear.amulet.VaultAmuletEffect;
import xyz.iwolfking.woldsvaults.items.gear.amulet.VaultAmuletEffectRegistry;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class VaultAmuletConfig extends Config {
    @Expose
    public VaultAmuletConfig.CharmMap CHARMS = new VaultAmuletConfig.CharmMap();
    public static final List<ResourceLocation> LOC = List.of(VaultMod.id("particle/charm/small_idona"), VaultMod.id("particle/charm/large_idona"), VaultMod.id("particle/charm/grand_idona"), VaultMod.id("particle/charm/majestic_idona"), VaultMod.id("particle/charm/small_tenos"), VaultMod.id("particle/charm/large_tenos"), VaultMod.id("particle/charm/grand_tenos"), VaultMod.id("particle/charm/majestic_tenos"), VaultMod.id("particle/charm/small_wendarr"), VaultMod.id("particle/charm/large_wendarr"), VaultMod.id("particle/charm/grand_wendarr"), VaultMod.id("particle/charm/majestic_wendarr"), VaultMod.id("particle/charm/small_velara"), VaultMod.id("particle/charm/large_velara"), VaultMod.id("particle/charm/grand_velara"), VaultMod.id("particle/charm/majestic_velara"));

    public VaultAmuletConfig() {
    }

    public String getName() {
        return "amulet";
    }

    @Nullable
    public VaultAmuletConfig.Charm getCharmConfig(VaultAmuletEffect<?> charmEffect) {
        return this.getTrinketConfig(charmEffect.getRegistryName());
    }

    @Nullable
    private VaultAmuletConfig.Charm getTrinketConfig(ResourceLocation key) {
        return (VaultAmuletConfig.Charm)this.CHARMS.get(key);
    }

    @Nullable
    public VaultAmuletEffect<?> getRandomTrinketSet(VaultAmuletConfig.Size size) {
        return this.getRandomTrinketSet((charmEffect, weight) -> {
            return weight;
        }, size);
    }

    @Nullable
    public VaultAmuletEffect<?> getRandomTrinketSet(BiFunction<VaultAmuletEffect<?>, Integer, Integer> weightAdjustFn, VaultAmuletConfig.Size size) {
        WeightedList<VaultAmuletEffect<?>> list = new WeightedList();
        this.CHARMS.forEach((key, config) -> {
            VaultAmuletEffect<?> charmEffect = VaultAmuletEffectRegistry.getEffect(key);
            if (charmEffect instanceof AttributeAmulet<?> attributeCharm) {
                if (attributeCharm.getSize() == size) {
                    list.add(charmEffect, (Number)weightAdjustFn.apply(charmEffect, config.getWeight()));
                }
            }

        });
        return list.getRandom().orElse(null);
    }

    public Set<ResourceLocation> getTrinketIds() {
        return this.CHARMS.keySet();
    }

    protected void reset() {
        this.CHARMS.clear();
        Iterator var1 = VaultAmuletEffectRegistry.getOrderedEntries().iterator();

        while(var1.hasNext()) {
            VaultAmuletEffect<?> effect = (VaultAmuletEffect)var1.next();
            VaultAmuletConfig.Charm charm = new VaultAmuletConfig.Charm(100, VaultGod.VELARA, effect.getRegistryName().getPath(), "textures/particle/charm/" + effect.getRegistryName().getPath() + ".png", 16733525, 16733525);
            charm.charmConfig = effect.getDefaultConfig();
            this.CHARMS.put(effect.getRegistryName(), charm);
        }

    }

    public static class CharmMap extends LinkedHashMap<ResourceLocation, VaultAmuletConfig.Charm> {
        public CharmMap() {
        }

        public static class Serializer implements JsonSerializer<VaultAmuletConfig.CharmMap>, JsonDeserializer<VaultAmuletConfig.CharmMap> {
            public Serializer() {
            }

            public VaultAmuletConfig.CharmMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                VaultAmuletConfig.CharmMap trinketMap = new VaultAmuletConfig.CharmMap();
                JsonObject map = json.getAsJsonObject();
                Iterator var6 = map.keySet().iterator();

                while(var6.hasNext()) {
                    String trinketKeyStr = (String)var6.next();
                    ResourceLocation trinketKey = new ResourceLocation(trinketKeyStr);
                    CharmEffect<?> trinketEffect = CharmEffectRegistry.getEffect(trinketKey);
                    if (trinketEffect != null) {
                        JsonObject trinketConfigObject = map.getAsJsonObject(trinketKeyStr);
                        VaultAmuletConfig.Charm trinketConfig = (VaultAmuletConfig.Charm)context.deserialize(trinketConfigObject, VaultAmuletConfig.Charm.class);
                        trinketConfig.charmConfig = context.deserialize(trinketConfigObject.get("config"), trinketEffect.getConfigClass());
                        trinketMap.put(trinketKey, trinketConfig);
                    }
                }

                return trinketMap;
            }

            public JsonElement serialize(VaultAmuletConfig.CharmMap src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject map = new JsonObject();
                src.forEach((charmKey, charmConfig) -> {
                    VaultAmuletEffect<?> charmEffect = VaultAmuletEffectRegistry.getEffect(charmKey);
                    if (charmEffect != null) {
                        JsonObject charmConfigObject = context.serialize(charmConfig).getAsJsonObject();
                        charmConfigObject.add("config", context.serialize(charmConfig.charmConfig, charmEffect.getConfigClass()));
                        map.add(charmKey.toString(), charmConfigObject);
                    }
                });
                return map;
            }
        }
    }

    public static class Charm {
        @Expose
        private int weight;
        @Expose
        private VaultGod god;
        @Expose
        private String name;
        @Expose
        private String particleLoc;
        @Expose
        private int color;
        @Expose
        private int majesticColor;
        @Expose
        private int minUses;
        @Expose
        private int maxUses;
        @Expose
        private int minAffinity;
        @Expose
        private int maxAffinity;
        private Object charmConfig;

        public Charm(int weight, VaultGod god, String name, String particleLoc, int color, int majesticColor) {
            this(weight, god, name, particleLoc, color, majesticColor, 6, 9, 0, 1);
        }

        public Charm(int weight, VaultGod god, String name, String particleLoc, int color, int majesticColor, int minUses, int maxUses, int minAffinity, int maxAffinity) {
            this.weight = weight;
            this.god = god;
            this.name = name;
            this.particleLoc = particleLoc;
            this.color = color;
            this.majesticColor = majesticColor;
            this.minUses = minUses;
            this.maxUses = maxUses;
            this.minAffinity = minAffinity;
            this.maxAffinity = maxAffinity;
        }

        public int getWeight() {
            return this.weight;
        }

        public VaultGod getGod() {
            return this.god;
        }

        public String getName() {
            return this.name;
        }

        public String getParticleLoc() {
            return this.particleLoc;
        }

        public int getColor() {
            return this.color;
        }

        public int getMajesticColor() {
            return this.majesticColor;
        }

        public TextColor getComponentColor() {
            return TextColor.fromRgb(this.getColor());
        }

        public int getRandomUses() {
            return Mth.randomBetweenInclusive(Config.rand, this.minUses, this.maxUses);
        }

        public int getRandomAffinity() {
            return Mth.randomBetweenInclusive(Config.rand, this.minAffinity, this.maxAffinity);
        }

        public Object getConfig() {
            return this.charmConfig;
        }
    }

    public static enum Size implements StringRepresentable {
        SMALL,
        LARGE,
        GRAND,
        MAJESTIC;

        private Size() {
        }

        public String getSerializedName() {
            return this.name();
        }
    }
}

