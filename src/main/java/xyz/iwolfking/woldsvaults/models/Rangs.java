package xyz.iwolfking.woldsvaults.models;

import iskallia.vault.VaultMod;
import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.PlainItemModel;
import iskallia.vault.dynamodel.registry.DynamicModelRegistry;

public class Rangs {
    public static final DynamicModelRegistry<PlainItemModel> REGISTRY = new DynamicModelRegistry<>();
    public static final PlainItemModel WOODEN_RANG;

    static {
        WOODEN_RANG = (PlainItemModel)REGISTRY.register((new PlainItemModel(VaultMod.id("gear/rang/wooden"), "Wooden Rang")).properties(new DynamicModelProperties().allowTransmogrification().discoverOnRoll()));
    }
}
