package xyz.iwolfking.woldsvaults.mixins.vaulthunters;


import iskallia.vault.VaultMod;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.integration.jei.lootinfo.LootInfo;
import iskallia.vault.integration.jei.lootinfo.LootInfoGroupDefinition;
import iskallia.vault.integration.jei.lootinfo.LootInfoGroupDefinitionRegistry;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;
import java.util.function.Supplier;

@Mixin(value = LootInfoGroupDefinitionRegistry.class, remap = false)
public abstract class MixinLootInfoGroupDefinitionRegistry {

    @Shadow @Final private static Map<ResourceLocation, LootInfoGroupDefinition> MAP;

    static {
        woldsVaults$register("completion_crate_unhinged_scavenger", () -> new ItemStack((ItemLike) ModBlocks.VAULT_CRATE_SCAVENGER));
        woldsVaults$register("vendor_pedestal", () -> new ItemStack((ItemLike) ModBlocks.SHOP_PEDESTAL));
        woldsVaults$register("completion_crate_haunted_mono", () -> new ItemStack((ItemLike) ModBlocks.VAULT_CRATE_MONOLITH));
        woldsVaults$register("completion_crate_enchanted_elixir", () -> new ItemStack((ItemLike) ModBlocks.VAULT_CRATE_ELIXIR));
        woldsVaults$register("completion_crate_enchanted_brutal_bosses", () -> new ItemStack((ItemLike) ModBlocks.VAULT_CRATE_CHAMPION));
        woldsVaults$register("brazier_pillage", () -> new ItemStack((ItemLike) ModBlocks.MONOLITH));
    }

    @Unique
    private static void woldsVaults$register(String path, Supplier<ItemStack> catalystItemStackSupplier) {
        /* 65 */     MAP.put(VaultMod.id(path), new LootInfoGroupDefinition(catalystItemStackSupplier,
                /*    */
                /* 67 */           woldsVaults$recipeType(path), () -> new TranslatableComponent("jei.the_vault." + path + "_loot")));
        /*    */   }

    @Unique
    private static RecipeType<LootInfo> woldsVaults$recipeType(String path) {
        return RecipeType.create("the_vault", path, LootInfo.class);
    }

}
