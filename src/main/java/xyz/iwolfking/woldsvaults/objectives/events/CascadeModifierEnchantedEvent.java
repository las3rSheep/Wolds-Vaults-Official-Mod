package xyz.iwolfking.woldsvaults.objectives.events;

import iskallia.vault.core.vault.Vault;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import xyz.iwolfking.woldsvaults.objectives.lib.BasicEnchantedEvent;

public class CascadeModifierEnchantedEvent extends BasicEnchantedEvent {
    public CascadeModifierEnchantedEvent(String eventName, String eventDescription, String primaryColor) {
        super(eventName, eventDescription, primaryColor);
    }

    @Override
    public void triggerEvent(BlockPos pos, ServerPlayer player, Vault vault) {
        super.triggerEvent(pos, player, vault);
    }
}
