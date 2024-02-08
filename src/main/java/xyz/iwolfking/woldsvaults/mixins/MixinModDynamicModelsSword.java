package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.VaultMod;
import iskallia.vault.dynamodel.DynamicModelProperties;
import iskallia.vault.dynamodel.model.item.HandHeldModel;
import iskallia.vault.dynamodel.registry.DynamicModelRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "iskallia.vault.init.ModDynamicModels$Swords", remap = false)
public class MixinModDynamicModelsSword {
    @Shadow @Final public static DynamicModelRegistry<HandHeldModel> REGISTRY;

    private static final HandHeldModel WOODEN_FLOWER_SWORD;
    private static final HandHeldModel IRON_FLOWER_SWORD;
    private static final HandHeldModel GOLDEN_FLOWER_SWORD;
    private static final HandHeldModel DIAMOND_FLOWER_SWORD;
    private static final HandHeldModel STONE_FLOWER_SWORD;
    private static final HandHeldModel NETHERITE_FLOWER_SWORD;

    static {
        WOODEN_FLOWER_SWORD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/sword/wooden_flower_sword"), "Wooden Flower Sword")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        IRON_FLOWER_SWORD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/sword/iron_flower_sword"), "Iron Flower Sword")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        GOLDEN_FLOWER_SWORD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/sword/golden_flower_sword"), "Golden Flower Sword")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        DIAMOND_FLOWER_SWORD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/sword/diamond_flower_sword"), "Diamond Flower Sword")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        STONE_FLOWER_SWORD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/sword/stone_flower_sword"), "Stone Flower Sword")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
        NETHERITE_FLOWER_SWORD = (HandHeldModel)REGISTRY.register((HandHeldModel)(new HandHeldModel(VaultMod.id("gear/sword/netherite_flower_sword"), "Netherite Flower Sword")).properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()));
    }



}
