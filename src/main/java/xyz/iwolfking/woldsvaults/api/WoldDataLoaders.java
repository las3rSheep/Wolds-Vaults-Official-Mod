package xyz.iwolfking.woldsvaults.api;

import xyz.iwolfking.vhapi.api.lib.config.loaders.box.WeightedProductEntryConfigLoader;
import xyz.iwolfking.woldsvaults.api.objectives.enchanted_elixir.EnchantedElixirEventLoader;
import xyz.iwolfking.woldsvaults.init.ModConfigs;

public class WoldDataLoaders {

    public static final WeightedProductEntryConfigLoader AUGMENT_BOX_LOADER;
    public static final WeightedProductEntryConfigLoader INSCRIPTION_BOX_LOADER;
    public static final WeightedProductEntryConfigLoader OMEGA_BOX_LOADER;
    public static final WeightedProductEntryConfigLoader VAULTAR_BOX_LOADER;
    public static final WeightedProductEntryConfigLoader CATALYST_BOX_LOADER;
    public static final WeightedProductEntryConfigLoader GEM_BOX_LOADER;
    public static final WeightedProductEntryConfigLoader SUPPLY_BOX_LOADER;
    public static final WeightedProductEntryConfigLoader ENIGMA_EGG_LOADER;
    public static final EnchantedElixirEventLoader ENCHANTED_ELIXIR_EVENT_LOADER;

    static {
        AUGMENT_BOX_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.AUGMENT_BOX.POOL;
        }, "augment_box");

        INSCRIPTION_BOX_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.INSCRIPTION_BOX.POOL;
        }, "inscription_box");

        OMEGA_BOX_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.OMEGA_BOX.POOL;
        }, "omega_box");

        VAULTAR_BOX_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.VAULTAR_BOX.POOL;
        }, "vaultar_box");

        CATALYST_BOX_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.VAULTAR_BOX.POOL;
        }, "catalyst_box");

        GEM_BOX_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.GEM_BOX.POOL;
        }, "gem_box");

        SUPPLY_BOX_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.SUPPLY_BOX.POOL;
        }, "supply_box");

        ENIGMA_EGG_LOADER = new WeightedProductEntryConfigLoader("vhapi", () -> {
            return ModConfigs.ENIGMA_EGG.POOL;
        }, "enigma_egg");
        ENCHANTED_ELIXIR_EVENT_LOADER = new EnchantedElixirEventLoader();
    }

}
