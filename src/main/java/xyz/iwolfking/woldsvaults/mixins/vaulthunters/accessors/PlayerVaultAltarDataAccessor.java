package xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors;

import iskallia.vault.altar.AltarInfusionRecipe;
import iskallia.vault.world.data.PlayerVaultAltarData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.UUID;

@Mixin(value = PlayerVaultAltarData.class, remap = false)
public interface PlayerVaultAltarDataAccessor {
    @Accessor("playerMap")
    Map<UUID, AltarInfusionRecipe> getPlayerMap();


}
