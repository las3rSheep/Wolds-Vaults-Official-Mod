package xyz.iwolfking.woldsvaults.models;

import iskallia.vault.VaultMod;
import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.HandHeldModel;
import iskallia.vault.init.ModDynamicModels;

public class AdditionalModels {

    public static final HandHeldModel LEVIATHAN_AXE;

    static {
       LEVIATHAN_AXE = (HandHeldModel) ModDynamicModels.Axes.REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/axe/leviathan"), "Leviathan")).properties(new DynamicModelProperties()));
    }
}
