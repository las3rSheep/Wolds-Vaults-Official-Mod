package xyz.iwolfking.woldsvaults.events;

import iskallia.vault.event.event.VaultJoinEvent;
import iskallia.vault.event.event.VaultLeaveEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.data.WoldsVaultPlayerData;

import java.util.List;

public class VaultEvents {


    public static void initiateTotemData(VaultJoinEvent event) {
        List<ServerPlayer> players = event.getPlayers();

        for(ServerPlayer player : players) {
            if(!WoldsVaultPlayerData.totemVaultDataMap.containsKey(player.getUUID())) {
                WoldsVaultPlayerData.totemVaultDataMap.put(player.getUUID(), 0);
            }
        }
    }
    public static void clearTotemData(VaultLeaveEvent event) {
        WoldsVaultPlayerData.totemVaultDataMap.remove(event.getPlayer().getUUID());
    }
}
