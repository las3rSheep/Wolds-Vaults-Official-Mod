package xyz.iwolfking.woldsvaults.mixins.integrateddynamics;

import com.simibubi.create.content.logistics.filter.FilterItem;
import net.joseph.vaultfilters.VaultFilters;
import net.minecraft.world.item.ItemStack;
import org.cyclops.integratedtunnels.core.TunnelItemHelpers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TunnelItemHelpers.class, remap = false)
public class MixinTunnelItemHelpers {
    @Inject(method = "areItemStackEqual", at = @At("HEAD"), cancellable = true)
    private static void areItemStacksEqual(ItemStack stackA, ItemStack stackB, boolean checkStackSize, boolean checkItem, boolean checkNbt, CallbackInfoReturnable<Boolean> cir) {
        if(stackA.getItem() instanceof FilterItem) {
            cir.setReturnValue(!checkItem || VaultFilters.checkFilter(stackB, stackA, true, null));
        }

        if(stackB.getItem() instanceof FilterItem) {
            cir.setReturnValue(!checkItem || VaultFilters.checkFilter(stackA, stackB, true, null));
        }
    }
}
