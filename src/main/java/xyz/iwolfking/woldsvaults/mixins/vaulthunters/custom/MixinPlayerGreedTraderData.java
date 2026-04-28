package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.world.data.PlayerGreedTraderData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.UUID;

@Mixin(value = PlayerGreedTraderData.class, remap = false)
public class MixinPlayerGreedTraderData {
    @Inject(method = "getResetCost", at = @At("TAIL"), cancellable = true)
    private void capRerollCost(UUID playerUuid, CallbackInfoReturnable<Integer> cir) {
        if(cir.getReturnValue() > 36) {
            cir.setReturnValue(36);
        }
    }
}
