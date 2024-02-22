package xyz.iwolfking.woldsvaults.objectives.events;

import iskallia.vault.core.vault.Vault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

public class PotionEffectEnchantedEvent extends BasicEnchantedEvent {


    private final MobEffect effect;
    private final Integer effectLevel;
    private final Integer effectDuration;


    public PotionEffectEnchantedEvent(String eventName, String eventDescription, String primaryColor, MobEffect effect, Integer duration, Integer level) {
        super(eventName, eventDescription, primaryColor);
        this.effect = effect;
        this.effectDuration = duration;
        this.effectLevel = level;
    }


    @Override
    public void triggerEvent(BlockPos pos, ServerPlayer player, Vault vault) {
        player.addEffect(new MobEffectInstance(effect, effectDuration, effectLevel));
        super.triggerEvent(pos, player, vault);
    }



}
