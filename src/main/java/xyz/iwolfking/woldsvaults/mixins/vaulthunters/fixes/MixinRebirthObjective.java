package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.core.vault.objective.RebirthObjective;
import iskallia.vault.core.vault.time.TickClock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static iskallia.vault.core.vault.objective.RebirthObjective.PHASE_DAMAGE_DEALT;

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

    @ModifyArg(method = "render",
                    at = @At(value = "INVOKE",
                             target = "Lnet/minecraft/client/gui/GuiComponent;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIFFIIII)V",
                             ordinal = 1),
                    index = 5)
    private int makeProgressUseFloat(int pWidth, @Local boolean transitioning, @Local(ordinal = 2) int currentPhase) {
        final float goal = woldsVaults$getPhaseDamageGoalFloat(currentPhase);
        return (int)(15 + 130.0F * (transitioning ? 1.0F : Math.min(this.getOr(PHASE_DAMAGE_DEALT,0.0F)/goal, 1.0F)));
    }

    @ModifyVariable(method = "render",
                    at = @At("STORE"),
                    ordinal = 0,
                    remap = false)
    private String makeTextUseFloat(String value, @Local boolean transitioning, @Local(ordinal = 2) int currentPhase) {
        final float dealt = this.getOr(PHASE_DAMAGE_DEALT,0.0F);
        final float goal = woldsVaults$getPhaseDamageGoalFloat(currentPhase);
        final float thr = 10000000000.0F; //10bn
        String format = (dealt>=thr ? "%.5e/":"%.0f/") + (goal>=thr ? "%.5e":"%.0f");
        return transitioning ? "PHASE SHIFT" : String.format(format, dealt, goal);
    }

    @ModifyConstant(method = "tickServer",
                    constant = @Constant(intValue = 1),
                    remap = false)
    private int onlyCountOnline(int constant, @Local(argsOnly = true) Vault vault) {
        return vault.get(Vault.CLOCK).has(TickClock.PAUSED) ? 0 : 1;
    }
}
