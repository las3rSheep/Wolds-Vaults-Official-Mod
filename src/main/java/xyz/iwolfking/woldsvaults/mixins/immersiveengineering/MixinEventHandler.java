package xyz.iwolfking.woldsvaults.mixins.immersiveengineering;

import blusunrize.immersiveengineering.common.EventHandler;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EventHandler.class, remap = false)
public class MixinEventHandler {

    /**
     * @author iwolfking
     * @reason Disable dropping shader bags in vaults
     */
    @Inject(method = "onLivingDrops", at = @At("HEAD"), cancellable = true)
    public void onLivingDrops(LivingDropsEvent event, CallbackInfo ci) {
        ci.cancel();
    }
}
