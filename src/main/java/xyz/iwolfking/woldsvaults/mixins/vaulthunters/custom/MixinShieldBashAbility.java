package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.skill.ability.effect.ShieldBashAbility;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ShieldBashAbility.class, remap = false)
public class MixinShieldBashAbility {
}
