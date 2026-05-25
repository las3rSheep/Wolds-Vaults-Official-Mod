package xyz.iwolfking.woldsvaults.mixins;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.entities.astral.StarDevourerEntity;

@Mixin(value = Ravager.class)
public abstract class MixinRavagerEntity {
    @Shadow
    public abstract int getRoarTick();

    @Inject(method = "strongKnockback", at = @At("HEAD"))
    private void onStrongKnockback(Entity pEntity, CallbackInfo ci) {
        Ravager ravager = (Ravager) (Object) this;

        if (ravager instanceof StarDevourerEntity && this.getRoarTick() > 0) {
            if (pEntity instanceof Player player) {
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 2));

                player.setDeltaMovement(player.getDeltaMovement().add(0, 0.5, 0));
                player.hurtMarked = true;
            }
        }
    }
}
