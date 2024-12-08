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
import virtuoel.pehkui.api.ScaleTypes;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.data.discovery.DiscoveredThemesData;

@Mod.EventBusSubscriber(
        modid = WoldsVaults.MOD_ID
)
public class VaultEvents {
    @SubscribeEvent
    public static void onVaultJoin(VaultJoinEvent event) {

        ResourceLocation theme = event.getVault().get(Vault.WORLD).get(WorldManager.THEME);
        ThemeKey themeKey = VaultRegistry.THEME.getKey(theme);

        if(!event.getPlayers().isEmpty()) {
            for(ServerPlayer joinedPlayer : event.getPlayers()) {
                ScaleTypes.BASE.getScaleData(joinedPlayer).setScale(1.0F);
                if(!DiscoveredThemesData.get(joinedPlayer.server).hasDiscovered(joinedPlayer, theme) && themeKey != null) {
                    DiscoveredThemesData.get(joinedPlayer.server).discoverThemeAndBroadcast(themeKey, joinedPlayer);
                }
            }
        }

    }
}
