package xyz.iwolfking.woldsvaults.blocks.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedstorage.block.*;
import net.p3pp3rf1y.sophisticatedstorage.common.gui.StorageContainerMenu;
import xyz.iwolfking.woldsvaults.init.ModBlocks;
import xyz.iwolfking.woldsvaults.items.sophisticated.SophisticatedVaultStorageBlockItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class SophisticatedVaultChestEntity extends StorageBlockEntity implements IAdditionalDropDataBlock {
    private boolean packed = false;
    private static final String PACKED_TAG = "packed";

    protected void saveSynchronizedData(CompoundTag tag) {
        super.saveSynchronizedData(tag);

        tag.putBoolean("packed", this.packed);
    }

    public CompoundTag getStorageContentsTag() {
        CompoundTag contents = this.saveWithoutMetadata();
        contents.putBoolean("packed", false);
        return contents;
    }

    public void loadSynchronizedData(CompoundTag tag) {
        super.loadSynchronizedData(tag);
        this.packed = tag.getBoolean("packed");
    }
    private final ChestLidController chestLidController = new ChestLidController();
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            SophisticatedVaultChestEntity.this.playSound(state, SoundEvents.CHEST_OPEN);
        }

        protected void onClose(Level level, BlockPos pos, BlockState state) {
            SophisticatedVaultChestEntity.this.playSound(state, SoundEvents.CHEST_CLOSE);
        }

        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int previousOpenCount, int openCount) {
            SophisticatedVaultChestEntity.this.chestLidController.shouldBeOpen(openCount > 0);
        }

        protected boolean isOwnContainer(Player player) {
            AbstractContainerMenu var3 = player.containerMenu;
            if (var3 instanceof StorageContainerMenu storageContainerMenu) {
                return storageContainerMenu.getStorageBlockEntity() == SophisticatedVaultChestEntity.this;
            } else {
                return false;
            }
        }
    };

    protected ContainerOpenersCounter getOpenersCounter() {
        return this.openersCounter;
    }

    public SophisticatedVaultChestEntity(BlockPos pos, BlockState state) {
        super(pos, state, (BlockEntityType) ModBlocks.SOPHISTICATED_VAULT_CHEST_ENTITY_BLOCK_ENTITY_TYPE);
    }



    public static void lidAnimateTick(SophisticatedVaultChestEntity chestBlockEntity) {
        if(chestBlockEntity.getBlockState().getBlock().equals(ModBlocks.SOPHISTICATED_VAULT_GILDED_STRONGBOX) || chestBlockEntity.getBlockState().getBlock().equals(ModBlocks.SOPHISTICATED_VAULT_ORNATE_STRONGBOX) || chestBlockEntity.getBlockState().getBlock().equals(ModBlocks.SOPHISTICATED_VAULT_LIVING_STRONGBOX)) {
            return;
        }
        else {
            chestBlockEntity.chestLidController.tickLid();
        }
    }

    public float getOpenNess(float partialTicks) {
        return this.chestLidController.getOpenness(partialTicks);
    }

    void playSound(BlockState state, SoundEvent sound) {
        if (this.level != null) {
            Block var4 = state.getBlock();
            if (var4 instanceof StorageBlockBase) {
                StorageBlockBase storageBlock = (StorageBlockBase)var4;
                Vec3i vec3i = storageBlock.getFacing(state).getNormal();
                double d0 = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
                double d1 = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
                double d2 = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
                this.level.playSound((Player)null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
                return;
            }
        }

    }

    public boolean isPacked() {
        return this.packed;
    }

    public void setPacked(boolean packed) {
        this.packed = packed;
    }

    public boolean shouldDropContents() {
        return !this.isPacked();
    }

    @Nonnull
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        return this.isPacked() && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? LazyOptional.empty() : super.getCapability(cap, side);
    }

    public boolean canConnectStorages() {
        return !this.packed && super.canConnectStorages();
    }

    public boolean canBeConnected() {
        return !this.packed && super.canBeConnected();
    }

    public boolean canBeLinked() {
        return !this.packed;
    }

    @Override
    public void addDropData(ItemStack itemStack, StorageBlockEntity storageBlockEntity) {
        if (storageBlockEntity instanceof SophisticatedVaultChestEntity wbe) {
            if (wbe.isPacked()) {
                StorageWrapper storageWrapper = storageBlockEntity.getStorageWrapper();
                UUID storageUuid = storageWrapper.getContentsUuid().orElse(UUID.randomUUID());
                CompoundTag storageContents = wbe.getStorageContentsTag();
                if (!storageContents.isEmpty()) {
                    ItemContentsStorage.get().setStorageContents(storageUuid, storageContents);
                    NBTHelper.setUniqueId(itemStack, "uuid", storageUuid);
                }
                SophisticatedVaultStorageBlockItem.setPacked(itemStack, true);
            }
        }
    }
}
