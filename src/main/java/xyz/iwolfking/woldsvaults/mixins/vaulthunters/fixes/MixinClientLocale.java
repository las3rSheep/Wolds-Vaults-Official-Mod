package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.client.ClientLocale;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ClientLocale.class, remap = false)
public class MixinClientLocale {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private static void whyYouBreakMyDatagenBoys(CallbackInfoReturnable<String> cir) {
        if(Minecraft.getInstance() == null) {
            cir.setReturnValue("en_us");
        }
    }
}
