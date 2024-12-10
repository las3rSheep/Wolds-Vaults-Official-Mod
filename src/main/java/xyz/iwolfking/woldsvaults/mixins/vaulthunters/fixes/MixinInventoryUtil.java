package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.item.BasicScavengerItem;
import iskallia.vault.item.CatalystInhibitorItem;
import iskallia.vault.item.GodBlessingItem;
import iskallia.vault.item.OfferingItem;
import iskallia.vault.util.InventoryUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors.InventoryUtilItemAccessAccessor;

import java.util.List;

@Mixin(value = InventoryUtil.class, remap = false)
public abstract class MixinInventoryUtil {

    @Shadow
    public static List<InventoryUtil.ItemAccess> findAllItems(Player player) {
        return null;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void makeItemsRotten(Player player) {
        for (InventoryUtil.ItemAccess items : findAllItems(player)) {
            ItemStack stack = items.getStack();
            if (stack.getItem() instanceof BasicScavengerItem || stack.getItem() instanceof CatalystInhibitorItem || stack.getItem() instanceof GodBlessingItem || stack.getItem() instanceof OfferingItem) {
                CompoundTag tag = stack.getOrCreateTag();
                tag.putBoolean("rotten", true);
                tag.remove("VaultId");
                ((InventoryUtilItemAccessAccessor)items).getSetter().accept(stack);
            }
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void makeItemsRotten(List<ItemStack> items) {
        for (ItemStack stack : items) {
            if (stack.getItem() instanceof BasicScavengerItem || stack.getItem() instanceof CatalystInhibitorItem || stack.getItem() instanceof GodBlessingItem || stack.getItem() instanceof OfferingItem) {
                CompoundTag tag = stack.getOrCreateTag();
                tag.putBoolean("rotten", true);
                tag.remove("VaultId");
            }
        }
    }
}
