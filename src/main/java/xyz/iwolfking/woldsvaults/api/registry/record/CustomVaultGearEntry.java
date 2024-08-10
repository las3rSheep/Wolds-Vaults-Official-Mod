package xyz.iwolfking.woldsvaults.api.registry.record;

import iskallia.vault.dynamodel.registry.DynamicModelRegistry;
import iskallia.vault.gear.VaultGearRarity;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.Map;

public record CustomVaultGearEntry(Item registryItem, String name,
                                   DynamicModelRegistry<?> dynamicModelRegistry, Map<VaultGearRarity, List<String>> modelRarityMap) {

}
