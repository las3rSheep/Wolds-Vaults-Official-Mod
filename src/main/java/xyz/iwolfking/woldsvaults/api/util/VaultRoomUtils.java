package xyz.iwolfking.woldsvaults.api.util;

import iskallia.vault.client.map.VaultMapData;
import iskallia.vault.client.map.VaultMapDataCache;
import iskallia.vault.core.util.RegionPos;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.stat.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Optional;

public class VaultRoomUtils {
    public static ResourceLocation getCurrentRoomId(Vault vault, Player player, BlockPos playerPos) {
        Optional<StatsCollector> stats = vault.getOptional(Vault.STATS);
        if (stats.isPresent() && player != null) {
            if (stats.get().getMap() != null) {
                StatCollector stat = stats.get().get(player.getUUID());
                if (stat != null) {
                    DiscoveredRoomStat rooms = stat.get(StatCollector.ROOMS_DISCOVERED);
                    DiscoveredTunnelStat tunnels = stat.get(StatCollector.DISCOVERED_TUNNELS);
                    DetailedRoomStat detailed = stat.getDetailedRoomsDiscovered();
                    if (rooms != null && !rooms.isEmpty()) {
                        VaultMapData mapData = VaultMapDataCache.getOrBuild(vault, rooms, tunnels, detailed);

                        RegionPos region = RegionPos.ofBlockPos(
                                playerPos,
                                47, 47
                        );

                        int gridX = region.getX();
                        int gridZ = region.getZ();

                        for (VaultMapData.Room room : mapData.getRooms()) {
                            if (room.getPos().getX() == gridX && room.getPos().getZ() == gridZ) {
                                return room.getRoomId();
                            }
                        }
                        return null;
                    }
                }
            }
        }

        return null;
    }

}
