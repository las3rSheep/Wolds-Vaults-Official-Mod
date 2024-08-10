package xyz.iwolfking.woldsvaults.mixins.vaulthunters.compat.shardpouchcurio;

import iskallia.vault.init.ModItems;
import iskallia.vault.item.ItemShardPouch;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import top.theillusivec4.curios.api.CuriosApi;

import static iskallia.vault.item.ItemShardPouch.getContainedStack;
import static iskallia.vault.item.ItemShardPouch.setContainedStack;

@Mixin(value = ItemShardPouch.class, remap = false)
public abstract class MixinItemShardPouch {


    /**
     * @author iwolfking
     * @reason Add Curios support for Black Market trades
     */
    @Overwrite
    public static int getShardCount(Inventory playerInventory) {
        int shards = 0;

        if(CuriosApi.getCuriosHelper().findFirstCurio(playerInventory.player, ModItems.SHARD_POUCH).isPresent()) {
            ItemStack pouchStack = CuriosApi.getCuriosHelper().findFirstCurio(playerInventory.player, ModItems.SHARD_POUCH).get().stack();
            shards += getContainedStack(pouchStack).getCount();
        }

        for (int slot = 0; slot < playerInventory.getContainerSize(); ++slot) {
            ItemStack stack = playerInventory.getItem(slot);
            if (stack.getItem() instanceof ItemShardPouch) {
                shards += getContainedStack(stack).getCount();
            } else if (stack.getItem() == ModItems.SOUL_SHARD) {
                shards += stack.getCount();
            }
        }

        return shards;
    }




    /**
     * @author iwolfking
     * @reason Add Curios support for buying Black Market trades
     */
    @Overwrite
    public static boolean reduceShardAmount(Inventory playerInventory, int count, boolean simulate) {
        if(CuriosApi.getCuriosHelper().findFirstCurio(playerInventory.player, ModItems.SHARD_POUCH).isPresent()) {
            ItemStack pouchStack = CuriosApi.getCuriosHelper().findFirstCurio(playerInventory.player, ModItems.SHARD_POUCH).get().stack();
            ItemStack shardStack = getContainedStack(pouchStack);
            int toReduce = Math.min(count, shardStack.getCount());
            if(!simulate) {
                shardStack.setCount(shardStack.getCount() - toReduce);
                setContainedStack(pouchStack, shardStack);
            }

            count -= toReduce;
        }
        for (int slot = 0; slot < playerInventory.getContainerSize(); ++slot) {
            ItemStack stack = playerInventory.getItem(slot);
            if (stack.getItem() instanceof ItemShardPouch) {
                ItemStack shardStack = getContainedStack(stack);
                int toReduce = Math.min(count, shardStack.getCount());
                if (!simulate) {
                    shardStack.setCount(shardStack.getCount() - toReduce);
                    setContainedStack(stack, shardStack);
                }

                count -= toReduce;
            } else if (stack.getItem() == ModItems.SOUL_SHARD) {
                int toReduce = Math.min(count, stack.getCount());
                if (!simulate) {
                    stack.shrink(toReduce);
                    playerInventory.setItem(slot, stack);
                }

                count -= toReduce;
            }

            if (count <= 0) {
                return true;
            }
        }

        return false;
    }
}
