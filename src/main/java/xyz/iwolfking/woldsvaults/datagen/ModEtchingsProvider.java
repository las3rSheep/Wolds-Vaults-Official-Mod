package xyz.iwolfking.woldsvaults.datagen;

import iskallia.vault.gear.attribute.VaultGearModifier;
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
        });
    }
}
