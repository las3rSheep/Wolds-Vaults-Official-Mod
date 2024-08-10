package xyz.iwolfking.woldsvaults.api.registry;

import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.init.ModCatalystModels;
import xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors.MixinInfusedCatalystItem;

import java.util.HashMap;
import java.util.Map;

public class CustomCatalystModelRegistry {
    private static final Map<Integer, String> CUSTOM_CATALYST_MODELS = new HashMap<>();

    public static void addModel(int id, String modelName) {
        CUSTOM_CATALYST_MODELS.put(id, modelName);
    }

    public static int getSize() {
        return CUSTOM_CATALYST_MODELS.size();
    }

    public static void registerModels() {
        ModCatalystModels.registerModels();
        MixinInfusedCatalystItem.setModels(MixinInfusedCatalystItem.getModels() +  getSize());
        if(WoldsVaultsConfig.COMMON.enableDebugMode.get()) {
            System.out.println("Wold's Vaults - Registered " + getSize() + " Custom Catalyst Models.");
        }
    }
}
