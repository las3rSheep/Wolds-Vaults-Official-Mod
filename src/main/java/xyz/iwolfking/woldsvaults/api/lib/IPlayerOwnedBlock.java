package xyz.iwolfking.woldsvaults.api.lib;

import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public interface IPlayerOwnedBlock {
    void setPlacingPlayer(Player player);

    UUID getPlayerUUID();
}
