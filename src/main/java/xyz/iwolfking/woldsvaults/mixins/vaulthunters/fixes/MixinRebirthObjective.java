package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.core.vault.objective.RebirthObjective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * make the greed boss damage target use a float instead of an int capped at MAX_INT
 */
@Mixin(RebirthObjective.class)
abstract class MixinRebirthObjective extends Objective {

    @Unique
    private static float woldsVaults$getPhaseDamageGoalFloat(int phase) {
        return (float)(50000.0 * Math.pow(2, phase));
    }

    @Inject(method = "checkPhaseTransition",
            at = @At(value = "INVOKE",
                    target = "Liskallia/vault/core/vault/objective/RebirthObjective;getPhaseDamageGoal(I)I",
                    shift = At.Shift.AFTER),
            cancellable = true,
            remap = false)
    private void checkPhaseTransition(CallbackInfo ci, @Local float damageDealt, @Local int currentPhase) {
        if (damageDealt < woldsVaults$getPhaseDamageGoalFloat(currentPhase))
            ci.cancel();
    }

}
