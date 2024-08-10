package xyz.iwolfking.woldsvaults.api.registry;

import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultObjectiveEntry;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.init.ModCustomVaultObjectiveEntries;

import java.util.ArrayList;
import java.util.List;

public class CustomVaultObjectiveRegistry {

    private static final List<CustomVaultObjectiveEntry> CUSTOM_VAULT_OBJECTIVE_ENTRIES = new ArrayList<>();

    public static void addEntry(CustomVaultObjectiveEntry entry) {
        CUSTOM_VAULT_OBJECTIVE_ENTRIES.add(entry);
    }

    public static int getSize() {
        return CUSTOM_VAULT_OBJECTIVE_ENTRIES.size();
    }

    public static void registerAllCustomVaultObjectives() {
        ModCustomVaultObjectiveEntries.registerCustomObjectives();
        if(WoldsVaultsConfig.COMMON.enableDebugMode.get()) {
            System.out.println("Wold's Vaults - Registered " + getSize() + " Custom Vault Objective Entries.");
        }
    }


    public static List<CustomVaultObjectiveEntry> getCustomVaultObjectiveEntries() {
        return CUSTOM_VAULT_OBJECTIVE_ENTRIES;
    }

    public static boolean contains(String id) {
        for(CustomVaultObjectiveEntry entry : getCustomVaultObjectiveEntries()) {
            if(id.equals(entry.id())) {
                return true;
            }
        }
        return false;
    }
}
