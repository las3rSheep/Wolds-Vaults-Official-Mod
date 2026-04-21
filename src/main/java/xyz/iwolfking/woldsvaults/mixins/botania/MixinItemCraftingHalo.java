package xyz.iwolfking.woldsvaults.mixins.botania;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.research.Restrictions;
import iskallia.vault.research.StageManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.ItemCraftingHalo;

@Mixin(value = ItemCraftingHalo.class, remap = false)
public class MixinItemCraftingHalo {
    @Inject(method = "tryCraft", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/crafting/Recipe;matches(Lnet/minecraft/world/Container;Lnet/minecraft/world/level/Level;)Z"), cancellable = true, remap = true)
    private void cancelIfNoResearch(Player player, ItemStack halo, int slot, boolean particles, CallbackInfo ci, @Local(name = "recipe") Recipe<?> recipe, @Local(name = "placer") ItemCraftingHalo.RecipePlacer placer) {
            String restrictedBy = StageManager.getResearchTree(player).restrictedBy(recipe.getResultItem(), Restrictions.Type.CRAFTABILITY);
            if(restrictedBy != null) {
                placer.clearGrid(false);
                ci.cancel();
            }
    }

    @Inject(method = "canCraftHeuristic", at = @At("TAIL"), cancellable = true)
    private static void renderResearchLock(Player player, Recipe<CraftingContainer> recipe, CallbackInfoReturnable<Boolean> cir) {
        if(!cir.getReturnValue()) {
            return;
        }

        cir.setReturnValue(StageManager.getResearchTree(player).restrictedBy(recipe.getResultItem(), Restrictions.Type.CRAFTABILITY) == null);
    }
}
