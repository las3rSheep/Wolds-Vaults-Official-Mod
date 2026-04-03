package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.core.vault.RoomCache;
import iskallia.vault.core.vault.modifier.modifier.DecoratorAddModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DecoratorAddModifier.class, remap = false)
public class MixinDecoratorAddModifier {
    @Inject(method = "isRoomTypeWhitelisted", at = @At("HEAD"), cancellable = true)
    private void removeRoomTypeRestriction(RoomCache.RoomType currentRoomType, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
    }
}
