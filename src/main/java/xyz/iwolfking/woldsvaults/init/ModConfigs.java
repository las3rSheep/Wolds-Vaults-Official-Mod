package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.vhapi.api.data.api.CustomRecyclerOutputs;
import xyz.iwolfking.vhapi.api.loaders.workstation.lib.CustomVaultRecyclerConfig;
import xyz.iwolfking.woldsvaults.config.*;
import xyz.iwolfking.woldsvaults.config.recipes.augment.AugmentRecipesConfig;

public class ModConfigs {
    public static GemBoxConfig GEM_BOX;
    public static SupplyBoxConfig SUPPLY_BOX;
    public static AugmentBoxConfig AUGMENT_BOX;
    public static InscriptionBoxConfig INSCRIPTION_BOX;
    public static OmegaBoxConfig OMEGA_BOX;
    public static CatalystBoxConfig CATALYST_BOX;
    public static EnigmaEggConfig ENIGMA_EGG;
    public static VaultarBoxConfig VAULTAR_BOX;
    public static UnhingedScavengerConfig UNHINGED_SCAVENGER;
    public static BallisticBingoConfig BALLISTIC_BINGO_CONFIG;

    public static HauntedBraziersConfig HAUNTED_BRAZIERS;
    public static EnchantedElixirConfig ENCHANTED_ELIXIR;
    public static AugmentRecipesConfig AUGMENT_RECIPES;
    public static ThemeTooltipsConfig THEME_TOOLTIPS;
    public static CustomVaultRecyclerConfig CUSTOM_RECYCLER_CONFIG;
    public static EternalAttributesConfig ETERNAL_ATTRIBUTES;
    public static GreedVaultAltarIngredientsConfig GREED_VAULT_ALTAR_INGREDIENTS;

    public static void register() {
        GEM_BOX = (GemBoxConfig) (new GemBoxConfig()).readConfig();
        SUPPLY_BOX = (SupplyBoxConfig) (new SupplyBoxConfig()).readConfig();
        AUGMENT_BOX = (AugmentBoxConfig) (new AugmentBoxConfig()).readConfig();
        INSCRIPTION_BOX = (InscriptionBoxConfig) (new InscriptionBoxConfig()).readConfig();
        OMEGA_BOX = (OmegaBoxConfig) (new OmegaBoxConfig()).readConfig();
        CATALYST_BOX = (CatalystBoxConfig) (new CatalystBoxConfig()).readConfig();
        ENIGMA_EGG = (EnigmaEggConfig) (new EnigmaEggConfig()).readConfig();
        VAULTAR_BOX = (VaultarBoxConfig) (new VaultarBoxConfig()).readConfig();
        UNHINGED_SCAVENGER = (UnhingedScavengerConfig) (new UnhingedScavengerConfig().readConfig());
        BALLISTIC_BINGO_CONFIG = (BallisticBingoConfig) (new BallisticBingoConfig().readConfig());
        HAUNTED_BRAZIERS = (HauntedBraziersConfig) (new HauntedBraziersConfig().readConfig());
        ENCHANTED_ELIXIR = (EnchantedElixirConfig) (new EnchantedElixirConfig().readConfig());
        AUGMENT_RECIPES = (AugmentRecipesConfig)(new AugmentRecipesConfig()).readConfig();
        THEME_TOOLTIPS = (ThemeTooltipsConfig)(new ThemeTooltipsConfig()).readConfig();
        CUSTOM_RECYCLER_CONFIG = (CustomVaultRecyclerConfig) (new CustomVaultRecyclerConfig()).readConfig();
        CustomRecyclerOutputs.CUSTOM_OUTPUTS.putAll(CUSTOM_RECYCLER_CONFIG.getOutputs());
        ETERNAL_ATTRIBUTES = (new EternalAttributesConfig()).readConfig();
        GREED_VAULT_ALTAR_INGREDIENTS = (new GreedVaultAltarIngredientsConfig()).readConfig();
    }
}
