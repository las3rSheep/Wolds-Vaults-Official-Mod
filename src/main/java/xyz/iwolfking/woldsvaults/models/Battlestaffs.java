package xyz.iwolfking.woldsvaults.models;

import iskallia.vault.VaultMod;
import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.HandHeldModel;
import iskallia.vault.dynamodel.registry.DynamicModelRegistry;

public class Battlestaffs {
    public static final DynamicModelRegistry<HandHeldModel> REGISTRY = new DynamicModelRegistry();
    public static final HandHeldModel BATTLESTAFF_0;
    public static final HandHeldModel BATTLESTAFF_COPPER;
    public static final HandHeldModel BATTLESTAFF_LAPIS;
    public static final HandHeldModel BATTLESTAFF_REDSTONE;
    public static final HandHeldModel BATTLESTAFF_GROWING;
    public static final HandHeldModel BATTLESTAFF_GOLDEN;
    public static final HandHeldModel BATTLESTAFF_EMERALD;
    public static final HandHeldModel BATTLESTAFF_DIAMOND;
    public static final HandHeldModel BATTLESTAFF_PURPLE;
    public static final HandHeldModel BATTLESTAFF_TERROR;
    public static final HandHeldModel BATTLESTAFF_LIGHT_BLUE;

    public Battlestaffs() {
    }

    static {
        BATTLESTAFF_0 = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_0"), "Battlestaff_0")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_COPPER = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_copper"), "Copper")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_LAPIS = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_lapis"), "Lapis")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_REDSTONE = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_redstone"), "Redstone")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_GROWING = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_growing"), "Growing")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_GOLDEN = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_golden"), "Golden")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_EMERALD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_emerald"), "Emerald")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_DIAMOND = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_diamond"), "Diamond")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_PURPLE = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_purple"), "Purple")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_TERROR = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_terror"), "Terror")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        BATTLESTAFF_LIGHT_BLUE = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/battlestaff/battlestaff_light_blue"), "Arcane")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));

    }
}
