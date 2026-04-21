package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.skill.ability.effect.NovaAbility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = NovaAbility.class, remap = false)
public class MixinNovaAbility {
    @Inject(method = "getDamageModifier", at = @At("TAIL"), cancellable = true)
    private void reduceDamageFallOff(float radius, float dist, CallbackInfoReturnable<Float> cir) {
        if(cir.getReturnValue() <= 0.5F) {
            cir.setReturnValue(0.5F);
        }
    }
}
