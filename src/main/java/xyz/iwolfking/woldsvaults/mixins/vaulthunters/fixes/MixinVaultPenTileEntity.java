package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.block.entity.AnimalPenTileEntity;
import iskallia.vault.init.ModItems;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = AnimalPenTileEntity.class)
public class MixinVaultPenTileEntity {
    @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;", ordinal = 0))
    private Item returnSwordForAxe(ItemStack instance) {
        if(instance.getItem() instanceof AxeItem) {
            return new ItemStack(ModItems.SWORD).getItem();
        }
        return instance.getItem();
    }
}
