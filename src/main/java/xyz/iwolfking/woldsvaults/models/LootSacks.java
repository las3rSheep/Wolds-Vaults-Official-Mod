package xyz.iwolfking.woldsvaults.models;

import iskallia.vault.VaultMod;
import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.HandHeldModel;
import iskallia.vault.dynamodel.registry.DynamicModelRegistry;

public class LootSacks {
    public static final DynamicModelRegistry<HandHeldModel> REGISTRY = new DynamicModelRegistry();

    public static final HandHeldModel BUNDLE;

    public LootSacks() {
    }

    static {
        BUNDLE = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/loot_sack/bundle"), "Bundle Sack")).properties(new DynamicModelProperties().allowTransmogrification().discoverOnRoll()));
    }
}
