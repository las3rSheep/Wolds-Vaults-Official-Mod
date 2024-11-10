package xyz.iwolfking.woldsvaults.mixins.vaulthunters.skills;

import iskallia.vault.skill.ability.effect.HealEffectAbility;
import iskallia.vault.skill.ability.effect.spi.AbstractHealAbility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = HealEffectAbility.class, remap = false)
public class MixinHealEffectAbility extends AbstractHealAbility {
    @Shadow private HealEffectAbility.RemovalStrategy removalStrategy;

//    /**
//     * @author iwolfking
//     * @reason Cleanse still heals for a quarter of the normal value.
//     */
//    @Overwrite
//    protected Ability.ActionResult doAction(SkillContext context) {
//        return (Ability.ActionResult) context.getSource().as(ServerPlayer.class).map((player) -> {
//            float healed = getFlatLifeHealed();
//            for (ConfiguredModification<IntValueConfig, HealAdditionalHealthModification> mod : (Iterable<ConfiguredModification<IntValueConfig, HealAdditionalHealthModification>>) SpecialAbilityModification.getModifications((LivingEntity)player, HealAdditionalHealthModification.class)) {
//                healed = ((HealAdditionalHealthModification)mod.modification()).adjustHealHealth((IntValueConfig)mod.config(), healed);
//             }
//             player.heal(healed);
//            this.removalStrategy.apply(player, (HealEffectAbility) (Object)this);
//            return Ability.ActionResult.successCooldownImmediate();
//        }).orElse(Ability.ActionResult.fail());
//    }
}
