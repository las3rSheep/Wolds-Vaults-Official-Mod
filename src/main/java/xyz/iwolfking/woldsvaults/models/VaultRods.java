package xyz.iwolfking.woldsvaults.models;

import iskallia.vault.VaultMod;
import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.HandHeldModel;
import iskallia.vault.dynamodel.registry.DynamicModelRegistry;

public class VaultRods {
    public static final DynamicModelRegistry<HandHeldModel> REGISTRY = new DynamicModelRegistry();

    public static final HandHeldModel BLAHAJ;
    public static final HandHeldModel SAFER_SPACES;

    public VaultRods() {
    }

    static {
        BLAHAJ = REGISTRY.register(new HandHeldModel(VaultMod.id("gear/plushie/blahaj"), "Blåhaj the Actually a VaultRod")).properties(new DynamicModelProperties().allowTransmogrification().discoverOnRoll());
        SAFER_SPACES = REGISTRY.register(new HandHeldModel(VaultMod.id("gear/plushie/safer_spaces"), "Terry the Actually a VaultRod")).properties(new DynamicModelProperties());
    }
}
