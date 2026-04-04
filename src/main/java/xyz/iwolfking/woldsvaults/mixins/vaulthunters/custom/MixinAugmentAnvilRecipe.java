package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.item.crystal.CrystalData;
import iskallia.vault.item.crystal.theme.PoolCrystalTheme;
import iskallia.vault.item.crystal.theme.ValueCrystalTheme;
import iskallia.vault.recipe.anvil.AnvilContext;
import iskallia.vault.recipe.anvil.AugmentAnvilRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors.PoolCrystalThemeAccessor;

@Mixin(value = AugmentAnvilRecipe.class, remap = false)
public class MixinAugmentAnvilRecipe {
    @Inject(method = "onSimpleCraft", at = @At(value = "INVOKE", target = "Liskallia/vault/item/AugmentItem;getTheme(Lnet/minecraft/world/item/ItemStack;)Ljava/util/Optional;"), cancellable = true)
    private void preventOverridingMapThemes(AnvilContext context, CallbackInfoReturnable<Boolean> cir, @Local(name = "crystal") CrystalData crystal) {
        if(crystal.getTheme() instanceof PoolCrystalTheme poolCrystalTheme) {
            if(((PoolCrystalThemeAccessor)poolCrystalTheme).getId().getPath().contains("map_themes")) {
                cir.setReturnValue(false);
            }
        }
    }
}
