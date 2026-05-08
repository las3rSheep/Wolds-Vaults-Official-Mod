package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.block.entity.UnboxingStationTileEntity;
import iskallia.vault.item.BoosterPackItem;
import iskallia.vault.item.JewelPouchItem;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = UnboxingStationTileEntity.class, remap = false)
public abstract class MixinUnboxingStationTileEntity {
    @Redirect(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/SimpleContainer;setItem(ILnet/minecraft/world/item/ItemStack;)V"
            ), remap = true
    )
    private static void preventSlotClearing(SimpleContainer instance, int index, ItemStack emptyStack, @Local(argsOnly = true) UnboxingStationTileEntity tile) {
        ItemStack originalStack = instance.getItem(index);

        if ((originalStack.getItem() instanceof JewelPouchItem || originalStack.getItem() instanceof BoosterPackItem)
                && originalStack.getCount() > 1) {

            originalStack.shrink(1);
        } else {
            instance.setItem(index, emptyStack);
        }
    }
}