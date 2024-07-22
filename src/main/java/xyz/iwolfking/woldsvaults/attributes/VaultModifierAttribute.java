package xyz.iwolfking.woldsvaults.attributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import io.netty.buffer.ByteBuf;
import iskallia.vault.core.net.BitBuffer;
import iskallia.vault.core.vault.modifier.registry.VaultModifierRegistry;
import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import iskallia.vault.gear.attribute.VaultGearAttributeInstance;
import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.attribute.config.ConfigurableAttributeGenerator;
import iskallia.vault.gear.attribute.type.VaultGearAttributeType;
import iskallia.vault.gear.reader.VaultGearModifierReader;
import iskallia.vault.util.NetcodeUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class VaultModifierAttribute {
    private final VaultModifier<?> modifier;
    private final int size;

    public VaultModifierAttribute(VaultModifier<?> effect, int amount) {
        this.modifier = effect;
        this.size = amount;
    }


    public VaultModifier<?> getModifier() {
        return this.modifier;
    }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        String var10000 = this.modifier == null ? "null" : this.modifier.getDisplayName();
        return "VaultModifierAttribute{modifier=" + var10000 + ", size=" + this.size + "}";
    }

    public static VaultGearAttributeType<VaultModifierAttribute> type() {
        BiConsumer<BitBuffer, VaultModifierAttribute> var10000 = (buf, attribute) -> {
            buf.writeIdentifier(attribute.getModifier().getId());
            buf.writeInt(attribute.getSize());
        };
        Function var10001 = (buf) -> {
            VaultModifier<?> modifier = VaultModifierRegistry.get((((BitBuffer)buf).readIdentifier()));
            return new VaultModifierAttribute(modifier, ((BitBuffer)buf).readInt());
        };
        BiConsumer<ByteBuf, VaultModifierAttribute> var10002 = (buf, attribute) -> {
            NetcodeUtils.writeIdentifier(buf, attribute.getModifier().getId());
            buf.writeInt(attribute.getSize());
        };
        Function var10003 = (buf) -> {
            VaultModifier<?> modifier = VaultModifierRegistry.get((NetcodeUtils.readIdentifier(((ByteBuf)buf))));
            return new VaultModifierAttribute(modifier, ((ByteBuf)buf).readInt());
        };
        Gson var10004 = VaultGearAttributeType.GSON;
        Objects.requireNonNull(var10004);
        return VaultGearAttributeType.of(var10000, var10001, var10002, var10003, var10004::toJsonTree, VaultModifierAttribute::read, VaultModifierAttribute::write);
    }

    private static VaultModifierAttribute read(Tag nbt) {
        CompoundTag tag = (CompoundTag)nbt;
        VaultModifier<?> effect = VaultModifierRegistry.get(new ResourceLocation(tag.getString("modifier")));
        int amplifier = tag.getInt("size");
        return new VaultModifierAttribute(effect, amplifier);
    }

    private static Tag write(VaultModifierAttribute attribute) {
        CompoundTag tag = new CompoundTag();
        tag.putString("modifier", attribute.getModifier().getId().toString());
        tag.putInt("size", attribute.getSize());
        return tag;
    }

    public static VaultModifierAttribute.Generator generator() {
        return new VaultModifierAttribute.Generator();
    }

    public static VaultModifierAttribute.Reader reader() {
        return new VaultModifierAttribute.Reader();
    }

    private static class Generator extends ConfigurableAttributeGenerator<VaultModifierAttribute, VaultModifierAttribute.Config> {
        private Generator() {
        }

        @Nullable
        public Class<VaultModifierAttribute.Config> getConfigurationObjectClass() {
            return VaultModifierAttribute.Config.class;
        }

        public VaultModifierAttribute generateRandomValue(VaultModifierAttribute.Config object, Random random) {
            VaultModifier<?> modifier = VaultModifierRegistry.get(new ResourceLocation("the_vault", "swift"));
            return new VaultModifierAttribute(modifier, 3);
        }

        @Nullable
        public MutableComponent getConfigDisplay(VaultGearModifierReader<VaultModifierAttribute> reader, VaultModifierAttribute.Config object) {
            VaultModifier<?> modifier = VaultModifierRegistry.get(object.modifierKey);
            return modifier == null ? null : (new TextComponent(String.valueOf(object.size))).withStyle(reader.getColoredTextStyle()).append(" ").append(modifier.getDisplayName());
        }
    }

    private static class Reader extends VaultGearModifierReader<VaultModifierAttribute> {
        private Reader() {
            super("", 14111487);
        }

        @Nullable
        public MutableComponent getDisplay(VaultGearAttributeInstance<VaultModifierAttribute> instance, VaultGearModifier.AffixType type) {
            VaultModifierAttribute modifierAttribute = (VaultModifierAttribute)instance.getValue();
            MutableComponent valueDisplay = this.getValueDisplay(modifierAttribute);
            return valueDisplay == null ? null : (new TextComponent(type.getAffixPrefix(true))).append(modifierAttribute.getModifier().getDisplayNameFormatted(modifierAttribute.getSize())).setStyle(this.getColoredTextStyle());
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public MutableComponent getValueDisplay(VaultModifierAttribute vaultModifierAttribute) {
            return new TextComponent(vaultModifierAttribute.getModifier().getDisplayNameFormatted(vaultModifierAttribute.getSize()));
        }

        protected void serializeTextElements(JsonArray out, VaultGearAttributeInstance<VaultModifierAttribute> instance, VaultGearModifier.AffixType type) {
            VaultModifierAttribute effect = (VaultModifierAttribute)instance.getValue();
            if(this.getValueDisplay(effect) != null) {
                out.add(type.getAffixPrefix(true));
                out.add(effect.getModifier().getDisplayNameFormatted(effect.getSize()));
            }
        }
    }

    public static class Config {
        @Expose
        private final ResourceLocation modifierKey;
        @Expose
        private final int size;

        public Config(VaultModifier<?> modifier, int amplifier) {
            this(modifier.getId(), amplifier);
        }

        public Config(ResourceLocation effectKey, int amplifier) {
            this.modifierKey = effectKey;
            this.size = amplifier;
        }
    }
}
