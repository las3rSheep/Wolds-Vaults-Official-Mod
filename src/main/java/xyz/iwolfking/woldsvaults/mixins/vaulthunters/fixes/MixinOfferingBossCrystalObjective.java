package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.core.vault.objective.Objective;
import iskallia.vault.core.vault.objective.OfferingBossObjective;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = OfferingBossObjective.class, remap = false)
public abstract class MixinOfferingBossCrystalObjective extends Objective {

}
