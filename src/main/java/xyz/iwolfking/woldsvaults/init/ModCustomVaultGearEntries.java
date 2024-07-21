package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;
import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultGearEntry;
import xyz.iwolfking.woldsvaults.models.Battlestaffs;
import xyz.iwolfking.woldsvaults.models.Tridents;

public class ModCustomVaultGearEntries {
    public static void registerGearEntries() {
        CustomVaultGearRegistry.addModel(new CustomVaultGearEntry(ModItems.BATTLESTAFF, "Battlestaff", Battlestaffs.REGISTRY));
        CustomVaultGearRegistry.addModel(new CustomVaultGearEntry(ModItems.TRIDENT, "Trident", Tridents.REGISTRY));
    }
}
