package xyz.iwolfking.woldsvaults.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Entity.class)
public class MixinEntity {

    @ModifyArg(method = "thunderHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 0)
    public DamageSource thunderHit(DamageSource x, @Local(argsOnly = true) LightningBolt bolt) {
        return bolt.getCause() != null
                ? DamageSource.playerAttack(bolt.getCause())
                : DamageSource.LIGHTNING_BOLT;
    }
}
