package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.init.ModBlocks;
import xyz.iwolfking.vaultcrackerlib.api.registry.generic.records.CustomVaultObjectiveEntry;
import xyz.iwolfking.vaultcrackerlib.api.registry.objective.CustomVaultObjectiveRegistry;
import xyz.iwolfking.woldsvaults.objectives.*;

public class ModCustomVaultObjectiveEntries {
    public static void registerCustomObjectives() {
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("brutal_bosses", "Brutal Bosses", BrutalBossesCrystalObjective.class, BrutalBossesCrystalObjective::new, BrutalBossesObjective.E_KEY, ModBlocks.VAULT_CRATE_CHAMPION));
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("enchanted_elixir", "Enchanted Elixir", EnchantedElixirCrystalObjective.class, EnchantedElixirCrystalObjective::new, EnchantedElixirObjective.E_KEY, ModBlocks.VAULT_CRATE_ELIXIR));
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("unhinged_scavenger", "Unhinged Scavenger Hunt", UnhingedScavengerCrystalObjective.class, UnhingedScavengerCrystalObjective::new, UnhingedScavengerObjective.E_KEY, ModBlocks.VAULT_CRATE_SCAVENGER));
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("haunted_braziers", "Haunted Braziers", HauntedBraziersCrystalObjective.class, HauntedBraziersCrystalObjective::new, HauntedBraziersObjective.E_KEY, ModBlocks.VAULT_CRATE_MONOLITH));
        CustomVaultObjectiveRegistry.addEntry(new CustomVaultObjectiveEntry("ballistic_bingo", "Ballistic Bingo", BallisticBingoCrystalObjective.class, BallisticBingoCrystalObjective::new, BallisticBingoObjective.KEY, ModBlocks.VAULT_CRATE_BINGO));
    }
}
