package xyz.iwolfking.woldsvaults.lib;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.p3pp3rf1y.sophisticatedcore.util.BlockItemBase;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedstorage.block.IStorageBlock;
import net.p3pp3rf1y.sophisticatedstorage.block.ItemContentsStorage;
import net.p3pp3rf1y.sophisticatedstorage.block.StorageWrapper;

import java.util.Optional;
import java.util.UUID;

public abstract class StackStorageWrapper extends StorageWrapper {
    private final ItemStack storageStack;

    public StackStorageWrapper(ItemStack storageStack) {
        super(() -> {
            return () -> {
            };
        }, () -> {
        }, () -> {
        });
        this.storageStack = storageStack;
    }

    private UUID getNewUuid() {
        UUID newUuid = UUID.randomUUID();
        NBTHelper.setUniqueId(this.storageStack, "uuid", newUuid);
        CompoundTag mainTag = new CompoundTag();
        CompoundTag storageWrapperTag = new CompoundTag();
        storageWrapperTag.put("contents", new CompoundTag());
        mainTag.put("storageWrapper", storageWrapperTag);
        ItemContentsStorage.get().setStorageContents(newUuid, mainTag);
        return newUuid;
    }

    public Optional<UUID> getContentsUuid() {
        return Optional.ofNullable(this.contentsUuid);
    }

    protected CompoundTag getContentsNbt() {
        if (this.contentsUuid == null) {
            this.contentsUuid = this.getNewUuid();
        }

        return ItemContentsStorage.get().getOrCreateStorageContents(this.contentsUuid).getCompound("storageWrapper").getCompound("contents");
    }

    protected void onUpgradeRefresh() {
    }

    public int getDefaultNumberOfInventorySlots() {
        Item var3 = this.storageStack.getItem();
        int var10000;
        if (var3 instanceof BlockItemBase blockItem) {
            Block var4 = blockItem.getBlock();
            if (var4 instanceof IStorageBlock storageBlock) {
                var10000 = storageBlock.getNumberOfInventorySlots();
                return var10000;
            }
        }

        var10000 = 0;
        return var10000;
    }

    protected void loadSlotNumbers(CompoundTag tag) {
        this.numberOfInventorySlots = (Integer)NBTHelper.getInt(this.storageStack, "numberOfInventorySlots").orElse(0);
        this.numberOfUpgradeSlots = (Integer)NBTHelper.getInt(this.storageStack, "numberOfUpgradeSlots").orElse(0);
    }

    public int getDefaultNumberOfUpgradeSlots() {
        Item var3 = this.storageStack.getItem();
        int var10000;
        if (var3 instanceof BlockItemBase blockItem) {
            Block var4 = blockItem.getBlock();
            if (var4 instanceof IStorageBlock storageBlock) {
                var10000 = storageBlock.getNumberOfUpgradeSlots();
                return var10000;
            }
        }

        var10000 = 0;
        return var10000;
    }
}
