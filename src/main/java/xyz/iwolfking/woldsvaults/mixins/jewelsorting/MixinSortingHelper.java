package xyz.iwolfking.woldsvaults.mixins.jewelsorting;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import iskallia.vault.gear.data.VaultGearData;
import lv.id.bonne.vaulthunters.jewelsorting.config.Configuration;
import lv.id.bonne.vaulthunters.jewelsorting.utils.SortingHelper;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;
import xyz.iwolfking.woldsvaults.init.ModItems;

import java.util.List;
import java.util.Optional;


@Restriction(
    require = {
        @Condition(type = Condition.Type.MOD, value = "vault_hunters_jewel_sorting")
    }
)
@Mixin(value = SortingHelper.class, remap = false)
public class MixinSortingHelper {
    @Inject(method = "compareVaultGear", at = @At(value = "INVOKE", target = "Llv/id/bonne/vaulthunters/jewelsorting/config/Configuration;getGearSortingOptions(Llv/id/bonne/vaulthunters/jewelsorting/config/Configuration$SortBy;)Ljava/util/List;"), cancellable = true)
    private static void sortMapTiers(String leftName, ItemStack leftStack, String rightName, ItemStack rightStack,
                                     Configuration.SortBy sortBy, boolean ascending, CallbackInfoReturnable<Integer> cir,
                                     @Local(name = "leftData") LocalRef<VaultGearData> leftDataRef, @Local(name = "rightData") LocalRef<VaultGearData> rightDataRef
    ){
        if (leftStack.getItem() != ModItems.MAP || rightStack.getItem() != ModItems.MAP) {
            return;
        }

        var leftData = leftDataRef.get();
        var rightData = rightDataRef.get();
        if (leftData == null) {
            leftData = VaultGearData.read(leftStack);
            leftDataRef.set(leftData);
        }
        if (rightData == null) {
            rightData = VaultGearData.read(rightStack);
            rightDataRef.set(rightData);
        }

        Optional<Integer> leftTier = leftData.getFirstValue(ModGearAttributes.MAP_TIER);
        Optional<Integer> rightTier = rightData.getFirstValue(ModGearAttributes.MAP_TIER);
        var returnValue = (leftTier.orElse(0)).compareTo(rightTier.orElse(0));
        if (returnValue != 0) {
            cir.setReturnValue(ascending ? returnValue : -returnValue );
        }
    }
}
