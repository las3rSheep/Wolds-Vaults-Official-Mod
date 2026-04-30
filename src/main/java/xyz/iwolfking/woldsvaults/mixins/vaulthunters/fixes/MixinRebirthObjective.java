package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.core.vault.objective.RebirthObjective;
import iskallia.vault.core.vault.time.TickClock;
import iskallia.vault.core.world.storage.VirtualWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

/**
 * make the greed boss damage target use a float instead of an int capped at MAX_INT
 */
@Mixin(value = RebirthObjective.class, remap = false)
abstract class MixinRebirthObjective extends Objective {

    @Shadow @Final private List<UUID> phaseKnightIds;

    @Shadow protected abstract void completePhaseTransition(VirtualWorld world, Vault vault);

    @Unique
    private static float woldsVaults$getPhaseDamageGoalFloat(int phase) {
        return (float)(50000.0 * Math.pow(2, phase));
    }

    @Inject(method = "checkPhaseTransition",
            at = @At(value = "INVOKE",
                    target = "Liskallia/vault/core/vault/objective/RebirthObjective;getPhaseDamageGoal(I)I",
                    shift = At.Shift.AFTER),
            cancellable = true)
    private void checkPhaseTransition(CallbackInfo ci, @Local float damageDealt, @Local int currentPhase) {
        if (damageDealt < woldsVaults$getPhaseDamageGoalFloat(currentPhase))
            ci.cancel();
    }
  
    @ModifyConstant(method = "tickServer",
                    constant = @Constant(intValue = 1),
                    remap = false)
    private int onlyCountOnline(int constant, @Local(argsOnly = true) Vault vault) {
        return vault.get(Vault.CLOCK).has(TickClock.PAUSED) ? 0 : 1;
    }


    @Inject(method = "tickPhaseTransition", at = @At("TAIL"))
    private void transitionWhenMinionsAreDead(VirtualWorld world, Vault vault, CallbackInfo ci, @Local(name = "countdown") int countdown){
        if (countdown <= 390) {
            for (var knight : this.phaseKnightIds) {
                if (world.getEntity(knight) != null) {
                    return;
                }
            }
            this.set(RebirthObjective.PHASE_TRANSITION_COUNTDOWN, 0);
            this.completePhaseTransition(world, vault);
        }
    }
}
