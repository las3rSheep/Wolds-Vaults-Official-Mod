package xyz.iwolfking.woldsvaults.api.registry.record;

import iskallia.vault.dynamodel.registry.DynamicModelRegistry;
import net.minecraft.world.item.Item;

public record CustomVaultGearEntry(Item registryItem, String name,
                                   DynamicModelRegistry<?> dynamicModelRegistry) {

}
