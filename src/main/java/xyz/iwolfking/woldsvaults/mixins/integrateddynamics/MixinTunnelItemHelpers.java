package xyz.iwolfking.woldsvaults.mixins.integrateddynamics;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.cyclops.integratedtunnels.core.TunnelItemHelpers;
import org.spongepowered.asm.mixin.Mixin;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "integrateddynamics")
        }
)
@Mixin(value = TunnelItemHelpers.class, remap = false)
public class MixinTunnelItemHelpers {
//    @Inject(method = "areItemStackEqual", at = @At("HEAD"), cancellable = true)
//    private static void areItemStacksEqual(ItemStack stackA, ItemStack stackB, boolean checkStackSize, boolean checkItem, boolean checkNbt, CallbackInfoReturnable<Boolean> cir) {
//        if(stackA.getItem() instanceof FilterItem) {
//            cir.setReturnValue(!checkItem || VaultFilters.checkFilter(stackB, stackA, true, null));
//        }
//
//        if(stackB.getItem() instanceof FilterItem) {
//            cir.setReturnValue(!checkItem || VaultFilters.checkFilter(stackA, stackB, true, null));
//        }
//    }
}
