package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import io.github.lightman314.lightmanscurrency.util.InventoryUtil;
import iskallia.vault.client.gui.framework.ScreenTextures;
import iskallia.vault.client.gui.framework.element.ItemStackDisplayElement;
import iskallia.vault.client.gui.framework.element.TextureAtlasElement;
import iskallia.vault.client.gui.framework.render.Tooltips;
import iskallia.vault.client.gui.framework.render.spi.IElementRenderer;
import iskallia.vault.client.gui.framework.render.spi.ITooltipRendererFactory;
import iskallia.vault.client.gui.framework.screen.AbstractElementContainerScreen;
import iskallia.vault.client.gui.framework.spatial.Spatials;
import iskallia.vault.client.gui.framework.spatial.spi.IMutableSpatial;
import iskallia.vault.client.gui.framework.text.LabelTextStyle;
import iskallia.vault.client.gui.framework.text.TextBorder;
import iskallia.vault.client.gui.screen.CatalystInfusionTableScreen;
import iskallia.vault.client.gui.screen.GreedTraderScreen;
import iskallia.vault.client.gui.screen.block.ToolStationScreen;
import iskallia.vault.client.gui.screen.block.VaultArtisanStationScreen;
import iskallia.vault.client.gui.screen.block.base.ForgeRecipeContainerScreen;
import iskallia.vault.container.GreedTraderContainer;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.init.ModItems;
import iskallia.vault.item.CoinPouchItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Objects;

@Mixin(value = GreedTraderScreen.class, remap = false)
public class MixinGreedTraderScreen  extends AbstractElementContainerScreen<GreedTraderContainer> {
    public MixinGreedTraderScreen(GreedTraderContainer container, Inventory inventory, Component title, IElementRenderer elementRenderer, ITooltipRendererFactory<AbstractElementContainerScreen<GreedTraderContainer>> tooltipRendererFactory) {
        super(container, inventory, title, elementRenderer, tooltipRendererFactory);
    }

    @Inject(method = "init", at = @At("TAIL"), remap = true)
    private void addGreedCoinDisplay(CallbackInfo ci) {
            IMutableSpatial var10003 = Spatials.positionXYZ(20, -10, 200);
            Objects.requireNonNull(TextBorder.DEFAULT_FONT.get());
            this.addElement((new VaultArtisanStationScreen.CoinCountElement(var10003, Spatials.size(100, 9), () -> Minecraft.getInstance().player != null ? InventoryUtil.GetItemCount(Minecraft.getInstance().player.getInventory(), ModItems.GREED_COIN) : 0, LabelTextStyle.shadowFromTextColor())).layout((screen, gui, parent, world) -> world.translateXY(gui.x(), gui.y())));
            this.addElement(((new TextureAtlasElement<>(Spatials.positionXY(0, -ScreenTextures.TAB_SOULSHARD_BACKGROUND.height()), ScreenTextures.TAB_SOULSHARD_BACKGROUND)).layout((screen, gui, parent, world) -> world.translateXY(gui))).tooltip(Tooltips.multi(() -> List.of(new TextComponent("Greed Coins")))));
            ItemStackDisplayElement<?> itemStackDisplayElement = (new ItemStackDisplayElement<>(Spatials.positionXY(5, -ScreenTextures.TAB_COUNTDOWN_BACKGROUND.height() + 3), new ItemStack(ModItems.GREED_COIN, 64))).layout((screen, gui, parent, world) -> world.translateXY(gui));
            itemStackDisplayElement.setScale(0.72F);
            this.addElement(itemStackDisplayElement);

    }
}
