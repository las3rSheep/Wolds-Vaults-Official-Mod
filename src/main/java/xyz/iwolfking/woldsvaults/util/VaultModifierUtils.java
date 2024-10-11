package xyz.iwolfking.woldsvaults.util;

import iskallia.vault.core.vault.modifier.spi.VaultModifier;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;

public class VaultModifierUtils {
    private static final TextComponent MODIFIER_ADDED_SUFFIX = new TextComponent(" was added to the vault.");
    public static void sendModifierAddedMessage(ServerPlayer player, VaultModifier<?> modifier, Integer stackSize) {
        player.displayClientMessage(modifier.getChatDisplayNameComponent(stackSize).copy().append(MODIFIER_ADDED_SUFFIX), false);
    }
}
