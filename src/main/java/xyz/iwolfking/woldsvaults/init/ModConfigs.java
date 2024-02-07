package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.config.GemBoxConfig;
import xyz.iwolfking.woldsvaults.config.UnhingedScavengerConfig;

public class ModConfigs {
    public static GemBoxConfig GEM_BOX;
    public static UnhingedScavengerConfig UNHINGED_SCAVENGER;
    public static void register() {
        GEM_BOX = (GemBoxConfig) (new GemBoxConfig()).readConfig();
        UNHINGED_SCAVENGER = (UnhingedScavengerConfig) (new UnhingedScavengerConfig().readConfig());

    }
}
