package xyz.iwolfking.woldsvaults.mixins.hexal;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ram.talia.hexal.common.blocks.entity.BlockEntitySlipway;
import org.spongepowered.asm.mixin.Mixin;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;

@Mixin(value = BlockEntitySlipway.class, remap = false)
public class MixinBlockEntitySlipway {
    @Inject(method = "serverTick", cancellable = true, at = @At(value="HEAD"))
    private void serverTick(CallbackInfo ci) {
        if (WoldsVaultsConfig.COMMON.disableWanderingWispSpawning.get()) {
            ci.cancel();
        }
    }
}
