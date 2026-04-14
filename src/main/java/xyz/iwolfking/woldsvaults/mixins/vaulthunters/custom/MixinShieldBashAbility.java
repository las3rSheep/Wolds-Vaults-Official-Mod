package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.skill.ability.effect.ShieldBashAbility;
import iskallia.vault.util.calc.ThornsHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ShieldBashAbility.class, remap = false)
public class MixinShieldBashAbility {
    @Redirect(method = "lambda$doAction$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", remap = true))
    private boolean doThornsDamage(LivingEntity instance, DamageSource pSource, float pAmount, @Local(name = "player") ServerPlayer player) {
        float thornsDamage = ThornsHelper.getAdditionalThornsFlatDamage(player);
        return instance.hurt(pSource, pAmount + thornsDamage);
    }
}
