package xyz.iwolfking.woldsvaults.models;

import iskallia.vault.VaultMod;
import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.HandHeldModel;
import iskallia.vault.dynamodel.registry.DynamicModelRegistry;

public class Plushies {
    public static final DynamicModelRegistry<HandHeldModel> REGISTRY = new DynamicModelRegistry();

    public static final HandHeldModel AXOLOTL;
    public static final HandHeldModel WOLD;

    public Plushies() {
    }

    static {
        AXOLOTL = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/plushie/axolotl"), "Aquee the Axolotl")).properties(new DynamicModelProperties().allowTransmogrification().discoverOnRoll()));
        WOLD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/plushie/wold"), "Wold the Tyrant")).properties(new DynamicModelProperties().allowTransmogrification().discoverOnRoll()));
    }
}
