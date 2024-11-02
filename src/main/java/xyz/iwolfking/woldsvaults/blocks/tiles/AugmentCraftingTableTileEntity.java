package xyz.iwolfking.woldsvaults.blocks.tiles;

import iskallia.vault.block.entity.base.ForgeRecipeTileEntity;
import iskallia.vault.config.recipe.ForgeRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import xyz.iwolfking.woldsvaults.blocks.containers.AugmentCraftingTableContainer;
import xyz.iwolfking.woldsvaults.init.ModBlocks;

public class AugmentCraftingTableTileEntity extends ForgeRecipeTileEntity implements MenuProvider {

    public AugmentCraftingTableTileEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlocks.AUGMENT_CRAFTING_TABLE_ENTITY, pWorldPosition, pBlockState, 6, new ForgeRecipeType[]{ForgeRecipeType.valueOf("AUGMENT")});
    }

    protected AbstractContainerMenu createMenu(int windowId, Inventory playerInventory) {
        return new AugmentCraftingTableContainer(windowId, this.getLevel(), this.getBlockPos(), playerInventory);
    }
}
