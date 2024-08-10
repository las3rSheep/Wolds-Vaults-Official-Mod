package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.api.registry.CustomVaultObjectiveRegistry;
import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultObjectiveEntry;
import xyz.iwolfking.woldsvaults.objectives.*;

public class ModCustomVaultObjectiveEntries {
    public static void registerCustomObjectives() {
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("brutal_bosses", "Brutal Bosses", BrutalBossesCrystalObjective.class, BrutalBossesCrystalObjective::new, BrutalBossesObjective.E_KEY));
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("enchanted_elixir", "Enchanted Elixir", EnchantedElixirCrystalObjective.class, EnchantedElixirCrystalObjective::new, EnchantedElixirObjective.E_KEY));
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("unhinged_scavenger", "Unhinged Scavenger Hunt", UnhingedScavengerCrystalObjective.class, UnhingedScavengerCrystalObjective::new, UnhingedScavengerObjective.E_KEY));
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("haunted_braziers", "Haunted Braziers", HauntedBraziersCrystalObjective.class, HauntedBraziersCrystalObjective::new, HauntedBraziersObjective.E_KEY));
    }
}
