package xyz.iwolfking.woldsvaults.mixins.integrateddynamics;

import com.simibubi.create.content.logistics.filter.FilterItem;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.joseph.vaultfilters.VaultFilters;
import net.minecraft.world.item.ItemStack;
import org.cyclops.commoncapabilities.api.capability.itemhandler.ItemMatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "integrateddynamics")
        }
)
@Mixin(value = ItemMatch.class, remap = false)
public abstract class MixinIVariableFacade {


    @Inject(method = "areItemStacksEqual", at = @At("HEAD"), cancellable = true)
    private static void areItemStacksEqual(ItemStack a, ItemStack b, int matchFlags, CallbackInfoReturnable<Boolean> cir) {

        if(matchFlags == 0) {
            cir.setReturnValue(true);
        }
        else {
            boolean item = (matchFlags & 1) > 0;
            boolean nbt = (matchFlags & 4) > 0;
            boolean stackSize = (matchFlags & 8) > 0;

            if(a.getItem() instanceof FilterItem) {
                cir.setReturnValue(!item || VaultFilters.checkFilter(b, a, true, null));
            }

            if(b.getItem() instanceof FilterItem) {
                cir.setReturnValue(!item || VaultFilters.checkFilter(a, b, true, null));
            }
        }
    }
}
