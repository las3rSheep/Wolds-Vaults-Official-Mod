package xyz.iwolfking.woldsvaults.blocks.containers;

import iskallia.vault.init.ModItems;
import iskallia.vault.item.gear.TrinketItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;
import xyz.iwolfking.woldsvaults.blocks.tiles.TrinketFusionForgeTileEntity;
import xyz.iwolfking.woldsvaults.init.ModContainers;

public class TrinketFusionContainer extends AbstractContainerMenu {

    private final TrinketFusionForgeTileEntity tile;

    private static final int TE_SLOT_COUNT = 3;

    private static final int INV_FIRST_SLOT = 3;
    private static final int INV_SLOT_COUNT = 27;

    private static final int HOTBAR_FIRST_SLOT = 30;
    private static final int HOTBAR_SLOT_COUNT = 9;

    public TrinketFusionContainer(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, (TrinketFusionForgeTileEntity) inv.player.level.getBlockEntity(buf.readBlockPos()));
    }

    public TrinketFusionContainer(int id, Inventory inv, TrinketFusionForgeTileEntity be) {
        super(ModContainers.TRINKET_FUSION_CONTAINER, id);
        this.tile = be;

        addSlot(new SlotItemHandler(be.getItems(), 0, 44, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem().equals(ModItems.TRINKET) && TrinketItem.getTrinket(stack).isPresent();
            }
        });

        addSlot(new SlotItemHandler(be.getItems(), 1, 62, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem().equals(ModItems.TRINKET) && TrinketItem.getTrinket(stack).isPresent();
            }
        });

        addSlot(new SlotItemHandler(be.getItems(), 2, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventory(inv);
    }

    private void addPlayerInventory(Inventory playerInv) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(
                        playerInv,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(
                    playerInv,
                    col,
                    8 + col * 18,
                    142
            ));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack empty = ItemStack.EMPTY;
        Slot sourceSlot = this.slots.get(index);

        if (sourceSlot == null || !sourceSlot.hasItem()) {
            return empty;
        }

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copy = sourceStack.copy();

        if (index < TE_SLOT_COUNT) {

            if (!this.moveItemStackTo(
                    sourceStack,
                    INV_FIRST_SLOT,
                    HOTBAR_FIRST_SLOT + HOTBAR_SLOT_COUNT,
                    true
            )) {
                return empty;
            }

            sourceSlot.set(ItemStack.EMPTY);
        }

        else {

            if (sourceStack.getItem().equals(ModItems.TRINKET)) {
                if (!this.moveItemStackTo(sourceStack, 0, 2, false)) {
                    return empty;
                }
            }

            else if (index < HOTBAR_FIRST_SLOT) {
                if (!this.moveItemStackTo(
                        sourceStack,
                        HOTBAR_FIRST_SLOT,
                        HOTBAR_FIRST_SLOT + HOTBAR_SLOT_COUNT,
                        false
                )) {
                    return empty;
                }
            } else {
                if (!this.moveItemStackTo(
                        sourceStack,
                        INV_FIRST_SLOT,
                        INV_FIRST_SLOT + INV_SLOT_COUNT,
                        false
                )) {
                    return empty;
                }
            }
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        sourceSlot.onTake(player, sourceStack);

        return copy;
    }


    public int getProgressScaled(int pixels) {
        int progress = tile.getProgress();
        int max = tile.getMaxProgress();

        if (max == 0) return 0;

        return progress * pixels / max;
    }

    public int getFluidAmount() {
        return tile.getFluidAmount();
    }

    public int getMaxFluid() {
        return tile.getMaxFluid();
    }
    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }



    public int getFluidColor() {
        return 0xFF3AC6FF;
    }
}