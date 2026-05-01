package xyz.iwolfking.woldsvaults.blocks.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.iwolfking.woldsvaults.init.ModBlocks;

import java.util.UUID;

public class OwnedCraftingTableTileEntity extends BlockEntity {
    private UUID ownerUUID;

    public OwnedCraftingTableTileEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlocks.OWNED_CRAFTING_TABLE_TILE_ENTITY_BLOCK_ENTITY_TYPE, pPos, pBlockState);
    }

    public void setOwner(UUID uuid) {
        this.ownerUUID =  uuid;
        setChanged();
    }

    public UUID getOwner() {
        return ownerUUID;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if(tag.hasUUID("Owner")) {
            ownerUUID = tag.getUUID("Owner");
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if(ownerUUID != null) {
            tag.putUUID("Owner", ownerUUID);
        }
    }

}
