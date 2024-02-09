package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.block.VaultCrateBlock;
import iskallia.vault.block.entity.VaultCrateTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;

@Mixin(value = VaultCrateTileEntity.class, remap = false)
public abstract class MixinVaultCrateTileEntity extends BlockEntity {
    public MixinVaultCrateTileEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }

    @Shadow public abstract void sendUpdates();

    /**
     * @author iwolfking
     * @reason Allow items to be inserted
     */
    @Overwrite
    private ItemStackHandler createHandler() {
        return new ItemStackHandler(54) {
            protected void onContentsChanged(int slot) {
                MixinVaultCrateTileEntity.this.sendUpdates();
            }

            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (!stack.getItem().canFitInsideContainerItems()) {
                    return false;
                } else {
                    return !(Block.byItem(stack.getItem()) instanceof ShulkerBoxBlock) && !(Block.byItem(stack.getItem()) instanceof VaultCrateBlock);
                }
            }

            @Nonnull
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return super.extractItem(slot, amount, simulate);
            }

            @Nonnull
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }
}
