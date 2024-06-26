package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import iskallia.vault.item.modification.ReforgeTagModificationFocus;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.init.ModItems;

import java.util.Map;

@Mixin(value = ReforgeTagModificationFocus.class, remap = false)
public class MixinReforgeTagModificationFocus {
    @Shadow @Final private static Map<Item, String> ITEM_TO_NAME;

    static {
        ITEM_TO_NAME.put(ModItems.TRIDENT, "Trident");
        ITEM_TO_NAME.put(ModItems.BATTLESTAFF, "Battlestaff");
    }
}
