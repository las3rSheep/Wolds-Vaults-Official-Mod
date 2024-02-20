package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.config.GemBoxConfig;
import xyz.iwolfking.woldsvaults.config.HauntedBraziersConfig;
import xyz.iwolfking.woldsvaults.config.SupplyBoxConfig;
import xyz.iwolfking.woldsvaults.config.UnhingedScavengerConfig;

public class ModConfigs {
    public static GemBoxConfig GEM_BOX;
    public static SupplyBoxConfig SUPPLY_BOX;
    public static UnhingedScavengerConfig UNHINGED_SCAVENGER;

    public static HauntedBraziersConfig HAUNTED_BRAZIERS;
    public static void register() {
        GEM_BOX = (GemBoxConfig) (new GemBoxConfig()).readConfig();
        SUPPLY_BOX = (SupplyBoxConfig) (new SupplyBoxConfig()).readConfig();
        UNHINGED_SCAVENGER = (UnhingedScavengerConfig) (new UnhingedScavengerConfig().readConfig());
        HAUNTED_BRAZIERS = (HauntedBraziersConfig) (new HauntedBraziersConfig().readConfig());
    }
}
