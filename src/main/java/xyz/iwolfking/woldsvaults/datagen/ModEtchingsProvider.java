package xyz.iwolfking.woldsvaults.datagen;

import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.attribute.config.BooleanFlagGenerator;
import iskallia.vault.gear.attribute.config.FloatAttributeGenerator;
import net.minecraft.data.DataGenerator;
import xyz.iwolfking.vhapi.api.datagen.AbstractEtchingProvider;
import xyz.iwolfking.vhapi.api.util.gear.GearModifierRegistryHelper;
import xyz.iwolfking.woldsvaults.WoldsVaults;

public class ModEtchingsProvider extends AbstractEtchingProvider {
    protected ModEtchingsProvider(DataGenerator generator) {
        super(generator, WoldsVaults.MOD_ID);
    }

    @Override
    public void registerConfigs() {
        add("wolds_etchings", builder -> {
            builder.addEtching(WoldsVaults.id("concentrate_drain"), "Concentrated Drain",  "<gray>Concentrate drains <yellow>%.0fpvalue% <gray>current health from mob per effect drained.", 11153926, VaultGearModifier.AffixType.IMPLICIT, etchingEntryBuilder -> {
                etchingEntryBuilder.typeGroups(stringBasicListBuilder -> {
                    stringBasicListBuilder.add("Offensive");
                });
                etchingEntryBuilder.attribute(WoldsVaults.id("concentrate_drain"), "ModEtching", WoldsVaults.id("concenrate_drain_etching"), tierBasicListBuilder -> {
                   tierBasicListBuilder.add(GearModifierRegistryHelper.createEtchingTier(2, 4, 10, 1, new FloatAttributeGenerator.Range(0.02F, 0.04F, 0.01F)));
                   tierBasicListBuilder.add(GearModifierRegistryHelper.createEtchingTier(4, 7, 10, 2, new FloatAttributeGenerator.Range(0.04F, 0.06F, 0.01F)));
                   tierBasicListBuilder.add(GearModifierRegistryHelper.createEtchingTier(7, 12, 10, 3, new FloatAttributeGenerator.Range(0.06F, 0.08F, 0.01F)));
                   tierBasicListBuilder.add(GearModifierRegistryHelper.createEtchingTier(12, -1, 10, 3, new FloatAttributeGenerator.Range(0.08F, 0.1F, 0.01F)));
                });
            });

            builder.addEtching(WoldsVaults.id("levitation_slow_falling"), "Levitation Hover",  "<#34b7eb>Levitate<gray> adds <yellow>Slow Falling<gray> for a brief period when deactivated", 3093151, VaultGearModifier.AffixType.IMPLICIT, etchingEntryBuilder -> {
                etchingEntryBuilder.typeGroups(stringBasicListBuilder -> {
                    stringBasicListBuilder.add("Utility");
                });
                etchingEntryBuilder.attribute(WoldsVaults.id("levitate_slow_falling"), "ModEtching", WoldsVaults.id("levitate_slow_falling_etching"), tierBasicListBuilder -> {
                    tierBasicListBuilder.add(GearModifierRegistryHelper.createEtchingTier(4, -1, 10, 1, new BooleanFlagGenerator.BooleanFlag(true)));
                });
            });

            builder.addEtching(WoldsVaults.id("colossus_titan_resistance"), "Colossus Titan",  "<#41880E>Colossus<gray> adds <yellow>%.0fpvalue%<gray> additional <#FF7400>Resistance Cap Limit<gray> while active", 4092928, VaultGearModifier.AffixType.IMPLICIT, etchingEntryBuilder -> {
                etchingEntryBuilder.typeGroups(stringBasicListBuilder -> {
                    stringBasicListBuilder.add("Defensive");
                });
                etchingEntryBuilder.attribute(WoldsVaults.id("colossus_titan_resistance"), "ModEtching", WoldsVaults.id("colossus_titan_resistance_etching"), tierBasicListBuilder -> {
                    tierBasicListBuilder.add(GearModifierRegistryHelper.createEtchingTier(9, 11, 10, 1, new FloatAttributeGenerator.Range(0.04F, 0.06F, 0.01F)));
                    tierBasicListBuilder.add(GearModifierRegistryHelper.createEtchingTier(11, -1, 10, 1, new FloatAttributeGenerator.Range(0.05F, 0.08F, 0.01F)));
                });
            });
        });
    }
}
