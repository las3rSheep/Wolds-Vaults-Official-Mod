package xyz.iwolfking.woldsvaults.events;

import iskallia.vault.core.data.key.ThemeKey;
import iskallia.vault.core.vault.Vault;
import iskallia.vault.core.vault.VaultRegistry;
import iskallia.vault.core.vault.WorldManager;
import iskallia.vault.event.event.VaultJoinEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.data.discovery.DiscoveredThemesData;

@Mod.EventBusSubscriber(
        modid = WoldsVaults.MOD_ID
)
public class VaultEvents {
    @SubscribeEvent
    public static void onVaultJoin(VaultJoinEvent event) {
        ServerPlayer initialPlayer;

        ResourceLocation theme = event.getVault().get(Vault.WORLD).get(WorldManager.THEME);
        ThemeKey themeKey = VaultRegistry.THEME.getKey(theme);

        if(!event.getPlayers().isEmpty()) {
            initialPlayer = event.getPlayers().get(0);

            if(!DiscoveredThemesData.get(initialPlayer.server).hasDiscovered(initialPlayer, theme) && themeKey != null) {
                DiscoveredThemesData.get(initialPlayer.server).discoverThemeAndBroadcast(themeKey, initialPlayer);
            }
        }


    }
}
