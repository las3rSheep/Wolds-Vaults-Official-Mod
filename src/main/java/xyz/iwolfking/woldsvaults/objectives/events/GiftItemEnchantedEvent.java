package xyz.iwolfking.woldsvaults.objectives.events;

import iskallia.vault.core.util.WeightedList;
import iskallia.vault.core.vault.Vault;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

public class GiftItemEnchantedEvent extends BasicEnchantedEvent {

    private final WeightedList<ItemStack> items;
    public GiftItemEnchantedEvent(String eventName, String eventDescription, ChatFormatting primaryColor, ChatFormatting secondaryColor, WeightedList<ItemStack> items) {
        super(eventName, eventDescription, primaryColor, secondaryColor);
        this.items = items;
    }

    @Override
    public void triggerEvent(BlockPos pos, ServerPlayer player, Vault vault) {
        ItemStack randomGiftItem = items.getRandom().get();
        items.getRandom().ifPresent(itemStack -> {
            player.addItem(randomGiftItem);
        });
        player.displayClientMessage(new TextComponent("You have been gifted " + randomGiftItem.getCount() + "x ").append(randomGiftItem.getDisplayName()), true);
        super.triggerEvent(pos, player, vault);
    }
}
