package xyz.iwolfking.woldsvaults.mixins.vaultarhud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.iridium.vaultarhud.VaultarHUDOverlay;
import io.iridium.vaultarhud.VaultarItem;
import io.iridium.vaultarhud.renderers.HUDInventoryRenderer;
import io.iridium.vaultarhud.util.Point;
import io.iridium.vaultarhud.util.SharedFunctions;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.lib.vaultarhud.WoldScreenValidator;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "vaultarhud")
        }
)
@Mixin(value = HUDInventoryRenderer.class, remap = false)
public abstract class MixinInventoryHudRender {
    @Shadow private static Minecraft minecraft;
    @Shadow public static boolean ShowHoverHUD;
    @Shadow private static ResourceLocation hudSmallInventoryTexture;

    @Shadow
    public static void RenderAltar(PoseStack poseStack, int x, int y, float scale) {
    }

    @Shadow
    public static void RenderCrystal(PoseStack poseStack, int x, int y, float scale, boolean isFloating) {
    }

    @Shadow private static ResourceLocation hudInventoryTexture;

    @Shadow
    protected static void RenderInventoryHUDCompositeElement(PoseStack poseStack, int x, int y, ItemStack itemStack, int countCompleted, int countTotal, float scale) {
    }

    /**
     * @author iwolfking
     * @reason Hacky vaultarhud fix by overwriting entire render method
     */
    @Overwrite
    public static void render(PoseStack poseStack, Point renderOrigin) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        float scale = 1.0F;
        int elementSpacing = Math.round(18.0F * scale);
        double GUISCALE = minecraft.getWindow().getGuiScale();
        Point offset = WoldScreenValidator.getScreenHUDCoordinates(minecraft.screen, new Point(28.0, 28.0));
        int x = (int) offset.getX();
        int y = (int) offset.getY();
        if (!ShowHoverHUD && SharedFunctions.isMouseOverItem(minecraft.mouseHandler.xpos(), minecraft.mouseHandler.ypos(), (double) x * GUISCALE, (double) y * GUISCALE, 28.0, 28.0, (float) GUISCALE)) {
            ShowHoverHUD = true;
        } else if (!ShowHoverHUD) {
            SharedFunctions.renderBackground(poseStack, x, y, 28, 28, hudSmallInventoryTexture);
            RenderAltar(poseStack, x + 6, y + 8, 1.0F);
            RenderCrystal(poseStack, x + 10, y + 3, 0.5F, false);
        }

        if (ShowHoverHUD) {
            y -= 59;
            if (!SharedFunctions.isMouseOverItem(minecraft.mouseHandler.xpos(), minecraft.mouseHandler.ypos(), (double) x * GUISCALE, (double) y * GUISCALE, 28.0, 87.0, (float) GUISCALE)) {
                ShowHoverHUD = false;
            } else {
                SharedFunctions.renderBackground(poseStack, x, y, 28, 87, hudInventoryTexture);
                RenderCrystal(poseStack, x + 6, y - 19, scale, true);

                for (int i = 0; i < VaultarHUDOverlay.vaultarItems.size(); ++i) {
                    VaultarItem item = (VaultarItem) VaultarHUDOverlay.vaultarItems.get(i);
                    RenderInventoryHUDCompositeElement(poseStack, x + 6, y + 10 + elementSpacing * i, item.getCurrentItem(VaultarHUDOverlay.TICKER), item.getCountCompleted(), item.getCountTotal(), scale);
                }
            }
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.disableBlend();
    }


    //Revisit this instead of the hacky method later
//    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lio/iridium/vaultarhud/util/ScreenValidator;getScreenHUDCoordinates(Lnet/minecraft/client/gui/screens/Screen;Lio/iridium/vaultarhud/util/Point;)Lio/iridium/vaultarhud/util/Point;"))
//    private static Point callWoldScreenValidator(Screen screen, Point point) {
//        return WoldScreenValidator.getScreenHUDCoordinates(screen, point);
//    }
}
