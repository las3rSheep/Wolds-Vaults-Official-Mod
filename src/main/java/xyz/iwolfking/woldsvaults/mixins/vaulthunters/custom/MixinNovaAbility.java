package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.skill.ability.effect.NovaAbility;
import iskallia.vault.skill.base.SkillContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = NovaAbility.class, remap = false)
public abstract class MixinNovaAbility {

    @Shadow
    protected abstract float getDamageModifier(float radius, float dist);

    @Redirect(method = "lambda$doAction$4", at = @At(value = "INVOKE", target = "Liskallia/vault/skill/ability/effect/NovaAbility;getDamageModifier(FF)F"))
    private float callAdjustedDamageModifier(NovaAbility instance, float radius, float dist, @Local(argsOnly = true) SkillContext skillContext) {
        return woldsVaults$getLevelScaledDamageFallOff(this.getDamageModifier(radius, dist), skillContext.getLevel());
    }

    @Unique
    private float woldsVaults$getLevelScaledDamageFallOff(float originalFalloff, int abilityLevel) {
        float maxFallOffPerLevel = 0.2F + (abilityLevel * 0.025F);
        return Math.max(originalFalloff, maxFallOffPerLevel);
    }
}
