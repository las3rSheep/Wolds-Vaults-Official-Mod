package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.item.LootableItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = LootableItem.class, remap = false)
public class MixinLootableItem {

}
