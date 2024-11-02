package xyz.iwolfking.woldsvaults.blocks.containers;

import iskallia.vault.client.gui.framework.element.CraftingSelectorElement;
import iskallia.vault.client.gui.screen.block.base.ForgeRecipeContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import xyz.iwolfking.woldsvaults.blocks.tiles.AugmentCraftingTableTileEntity;

import javax.annotation.Nonnull;

public class AugmentCraftingTableScreen extends ForgeRecipeContainerScreen<AugmentCraftingTableTileEntity, AugmentCraftingTableContainer> {
    public AugmentCraftingTableScreen(AugmentCraftingTableContainer container, Inventory inventory, Component title) {
        super(container, inventory, title, 173);
    }

    @Nonnull
    protected CraftingSelectorElement<?> createCraftingSelector() {
        return this.makeCraftingSelector();
    }
}
