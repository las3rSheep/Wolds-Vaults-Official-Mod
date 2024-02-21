package xyz.iwolfking.woldsvaults.objectives.events;

import iskallia.vault.core.util.WeightedList;
import iskallia.vault.core.vault.Vault;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

public class InceptionEnchantedEvent extends BasicEnchantedEvent {

    private final WeightedList<BasicEnchantedEvent> events;

    private final boolean shouldEventsBeRandom;

    private final Integer count;
    public InceptionEnchantedEvent(String eventName, String eventDescription, ChatFormatting primaryColor, ChatFormatting secondaryColor, WeightedList<BasicEnchantedEvent> events, boolean shouldEventsBeRandom, Integer count) {
        super(eventName, eventDescription, primaryColor, secondaryColor);
        this.events = events;
        this.shouldEventsBeRandom = shouldEventsBeRandom;
        this.count = count;
    }

    @Override
    public void triggerEvent(BlockPos pos, ServerPlayer player, Vault vault) {
        if(shouldEventsBeRandom) {
            for(int i = 0; i < count; i++) {
                events.getRandom().ifPresent(basicEnchantedEvent -> {
                    basicEnchantedEvent.triggerEvent(pos, player, vault);
                });
            }
        }
        else {
            events.forEach((basicEnchantedEvent, aDouble) -> {
                basicEnchantedEvent.triggerEvent(pos, player, vault);
            });
        }

        super.triggerEvent(pos, player, vault);

    }
}
