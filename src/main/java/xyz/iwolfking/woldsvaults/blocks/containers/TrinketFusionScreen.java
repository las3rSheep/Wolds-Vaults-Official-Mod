package xyz.iwolfking.woldsvaults.blocks.containers;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.blocks.tiles.TrinketFusionForgeTileEntity;

public class TrinketFusionScreen extends AbstractContainerScreen<TrinketFusionContainer> {

    private static final ResourceLocation TEXTURE = WoldsVaults.id("textures/gui/trinket_fuser.png");

    public TrinketFusionScreen(TrinketFusionContainer menu, Inventory inv, Component title) {
        super(menu, inv, title);
    }

    @Override
    public void render(PoseStack pose, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(pose);
        super.render(pose, mouseX, mouseY, partialTick);
        this.renderTooltip(pose, mouseX, mouseY);
    }

    @Override
    protected void renderBg(PoseStack pose, float partial, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(pose, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        int progress = menu.getProgressScaled(24);
        //Progress Bar
        this.blit(pose, this.leftPos + 79, this.topPos + 34, 176, 0, progress + 1, 16);

        //Fluid Display
        int fluid = menu.getFluidAmount();

        int barHeight = 52;
        int scaled = (int)((fluid / (float) TrinketFusionForgeTileEntity.MAX_FLUID) * barHeight);

        int x = leftPos + 16;
        int y = topPos + 20;

        int color = menu.getFluidColor();

        fill(
                pose,
                x,
                y + (barHeight - scaled),
                x + 8,
                y + barHeight,
                color
        );

        this.blit(pose, leftPos + 16, topPos + 20, 176, 17, 8, 52);
    }
}