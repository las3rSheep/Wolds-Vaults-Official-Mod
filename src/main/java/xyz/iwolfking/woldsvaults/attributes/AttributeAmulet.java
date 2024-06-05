package xyz.iwolfking.woldsvaults.attributes;

import com.google.common.collect.Lists;
import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.attribute.VaultGearAttributeInstance;
import iskallia.vault.gear.charm.GearAttributeCharm;
import iskallia.vault.util.MiscUtils;
import net.minecraft.resources.ResourceLocation;
import xyz.iwolfking.woldsvaults.config.VaultAmuletConfig;
import xyz.iwolfking.woldsvaults.items.gear.amulet.VaultAmuletEffect;

import java.util.List;

public class AttributeAmulet<T extends Number> extends VaultAmuletEffect<VaultAmuletEffect.Config<?>> implements GearAttributeCharm{
    private final VaultGearAttribute<T> defaultAttribute;
    private final VaultAmuletConfig.Size size;

    public AttributeAmulet(VaultAmuletConfig.Size size, ResourceLocation name, VaultGearAttribute<T> attribute) {
        super(name);
        this.size = size;
        this.defaultAttribute = attribute;
    }

    public VaultAmuletConfig.Size getSize() {
        return this.size;
    }

    public Class<VaultAmuletEffect.Config<?>> getConfigClass() {
        return MiscUtils.cast(VaultAmuletEffect.Config.class);
    }

    public VaultAmuletEffect.Config<T> getDefaultConfig() {
        return new VaultAmuletEffect.Config(this.defaultAttribute, 0.0F);
    }

    public List<VaultGearAttributeInstance<?>> getAttributes() {
        return Lists.newArrayList(new VaultGearAttributeInstance[]{this.getConfig().toAttributeInstance()});
    }

    public List<VaultGearAttributeInstance<?>> getAttributes(float value) {
        return Lists.newArrayList(new VaultGearAttributeInstance[]{this.getConfig().toAttributeInstance(value)});
    }
}
