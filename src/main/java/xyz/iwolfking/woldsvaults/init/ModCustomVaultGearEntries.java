package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;
import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultGearEntry;
import xyz.iwolfking.woldsvaults.config.fake.CustomVaultGearModelRollRaritiesConfig;
import xyz.iwolfking.woldsvaults.models.Battlestaffs;
import xyz.iwolfking.woldsvaults.models.Tridents;

public class ModCustomVaultGearEntries {
    public static void registerGearEntries() {
        CustomVaultGearRegistry.addEntry(new CustomVaultGearEntry(ModItems.BATTLESTAFF, "Battlestaff", Battlestaffs.REGISTRY, CustomVaultGearModelRollRaritiesConfig.BATTLESTAFF_MODEL_ROLLS));
        CustomVaultGearRegistry.addEntry(new CustomVaultGearEntry(ModItems.TRIDENT, "Trident", Tridents.REGISTRY, CustomVaultGearModelRollRaritiesConfig.TRIDENT_MODEL_ROLLS));
    }
}
