package xyz.iwolfking.woldsvaults.api.helper;

import iskallia.vault.altar.AltarInfusionRecipe;
import iskallia.vault.world.data.PlayerVaultAltarData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import xyz.iwolfking.woldsvaults.init.ModConfigs;
import xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors.PlayerVaultAltarDataAccessor;

public class PlayerVaultAltarDataHelper {
    public static AltarInfusionRecipe generateGreedRecipe(ServerPlayer player, BlockPos pos, boolean isPogInfused) {
        PlayerVaultAltarData data = PlayerVaultAltarData.get();
        AltarInfusionRecipe recipe = ((PlayerVaultAltarDataAccessor)data).getPlayerMap().computeIfAbsent(player.getUUID(), (k) -> new AltarInfusionRecipe(player.getUUID(), ModConfigs.GREED_VAULT_ALTAR_INGREDIENTS.getIngredients(player, pos), isPogInfused));
        data.setDirty();
        return recipe;
    }
}
