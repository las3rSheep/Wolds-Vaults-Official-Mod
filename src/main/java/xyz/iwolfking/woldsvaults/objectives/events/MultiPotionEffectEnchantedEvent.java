package xyz.iwolfking.woldsvaults.objectives.events;

import iskallia.vault.core.vault.Vault;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

import java.util.List;

public class MultiPotionEffectEnchantedEvent extends BasicEnchantedEvent {

    private final List<MobEffect> effects;
    private final Integer effectLevel;
    private final Integer effectDuration;
    public MultiPotionEffectEnchantedEvent(String eventName, String eventDescription, ChatFormatting primaryColor, ChatFormatting secondaryColor, List<MobEffect> effects, Integer duration, Integer level) {
        super(eventName, eventDescription, primaryColor, secondaryColor);
        this.effects = effects;
        this.effectDuration = duration;
        this.effectLevel = level;
    }

    @Override
    public void triggerEvent(BlockPos pos, ServerPlayer player, Vault vault) {
        effects.forEach(mobEffect -> {
            player.addEffect(new MobEffectInstance(mobEffect, effectDuration, effectLevel));
        });
        super.triggerEvent(pos, player, vault);
    }
}
