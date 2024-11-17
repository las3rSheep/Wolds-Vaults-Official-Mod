package xyz.iwolfking.woldsvaults.mixins.vaulthunters.testing;

import iskallia.vault.config.VaultCrystalConfig;
import iskallia.vault.config.entry.LevelEntryList;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = VaultCrystalConfig.class, remap = false)
public interface CrystalConfigAccessor {
    @Accessor("SEALS")
    Map<ResourceLocation, LevelEntryList<VaultCrystalConfig.SealEntry>> getSeals();
}
