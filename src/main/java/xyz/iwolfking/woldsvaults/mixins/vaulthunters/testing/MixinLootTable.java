package xyz.iwolfking.woldsvaults.mixins.vaulthunters.testing;

import iskallia.vault.core.world.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = LootTable.class, remap = false)
public class MixinLootTable {
}
