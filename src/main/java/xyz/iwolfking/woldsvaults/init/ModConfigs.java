package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.config.GemBoxConfig;

public class ModConfigs {
    public static GemBoxConfig GEM_BOX;

    public static void register() {
        GEM_BOX = (GemBoxConfig) (new GemBoxConfig()).readConfig();


    }
}
