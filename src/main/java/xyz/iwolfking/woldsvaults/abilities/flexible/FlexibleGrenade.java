package xyz.iwolfking.woldsvaults.abilities.flexible;

import iskallia.vault.entity.entity.VaultGrenade;
import iskallia.vault.skill.ability.effect.GrenadeAbility;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class FlexibleGrenade extends GrenadeAbility {
    public void cast(Player player, GrenadeAbility ability, float xRotation, float yRotation) {
        VaultGrenade grenade = new VaultGrenade(player.level, player, this.getAttackDamage((ServerPlayer) player), ability.getRadius(player), ability.getDuration());
        grenade.shootFromRotation(player, xRotation, yRotation, 0.0F, ability.getThrowPower(), 0.0F);
        player.level.addFreshEntity(grenade);
        player.level.playSound(null, player, SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
    }

    public void cast(Player player, GrenadeAbility ability) {
        cast(player, ability, player.getXRot(), player.getYRot());
    }
}
