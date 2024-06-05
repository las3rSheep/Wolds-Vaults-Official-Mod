package xyz.iwolfking.woldsvaults.init;

import xyz.iwolfking.woldsvaults.config.*;

public class ModConfigs {
    public static GemBoxConfig GEM_BOX;
    public static SupplyBoxConfig SUPPLY_BOX;
    public static AugmentBoxConfig AUGMENT_BOX;
    public static InscriptionBoxConfig INSCRIPTION_BOX;
    public static OmegaBoxConfig OMEGA_BOX;
    public static CatalystBoxConfig CATALYST_BOX;
    public static EnigmaEggConfig ENIGMA_EGG;
    public static UnhingedScavengerConfig UNHINGED_SCAVENGER;

    public static HauntedBraziersConfig HAUNTED_BRAZIERS;
    public static EnchantedElixirConfig ENCHANTED_ELIXIR;
    public static VaultAmuletConfig VAULT_AMULET;
    public static void register() {
        GEM_BOX = (GemBoxConfig) (new GemBoxConfig()).readConfig();
        SUPPLY_BOX = (SupplyBoxConfig) (new SupplyBoxConfig()).readConfig();
        AUGMENT_BOX = (AugmentBoxConfig) (new AugmentBoxConfig()).readConfig();
        INSCRIPTION_BOX = (InscriptionBoxConfig) (new InscriptionBoxConfig()).readConfig();
        OMEGA_BOX = (OmegaBoxConfig) (new OmegaBoxConfig()).readConfig();
        CATALYST_BOX = (CatalystBoxConfig) (new CatalystBoxConfig()).readConfig();
        ENIGMA_EGG = (EnigmaEggConfig) (new EnigmaEggConfig()).readConfig();
        UNHINGED_SCAVENGER = (UnhingedScavengerConfig) (new UnhingedScavengerConfig().readConfig());
        HAUNTED_BRAZIERS = (HauntedBraziersConfig) (new HauntedBraziersConfig().readConfig());
        ENCHANTED_ELIXIR = (EnchantedElixirConfig) (new EnchantedElixirConfig().readConfig());
        VAULT_AMULET = (VaultAmuletConfig) (new VaultAmuletConfig().readConfig());
    }
}
