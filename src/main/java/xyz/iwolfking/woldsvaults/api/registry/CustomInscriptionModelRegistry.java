package xyz.iwolfking.woldsvaults.api.registry;

import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.init.ModInscriptionModels;

import java.util.HashMap;
import java.util.Map;

public class CustomInscriptionModelRegistry {
    private static final Map<Integer, String> CUSTOM_INSCRIPTION_MODELS = new HashMap<>();

    public static void addModel(int id, String modelName) {
        CUSTOM_INSCRIPTION_MODELS.put(id, modelName);
    }

    public static int getSize() {
        return CUSTOM_INSCRIPTION_MODELS.size() + 16;
    }

    public static void registerModels() {
        ModInscriptionModels.registerModels();
        if(WoldsVaultsConfig.COMMON.enableDebugMode.get()) {
            System.out.println("Wold's Vaults - Registered " + getSize() + " Custom Inscription Models.");
        }
    }
}
