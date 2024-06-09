package xyz.iwolfking.woldsvaults.mixins.vaultarhud;

import io.iridium.vaultarhud.VaultarHUDOverlay;
import io.iridium.vaultarhud.VaultarItem;
import io.iridium.vaultarhud.renderers.HUDInGameRenderer;
import io.iridium.vaultarhud.renderers.HUDInventoryRenderer;
import io.iridium.vaultarhud.util.Point;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.lib.vaultarhud.WoldScreenValidator;

import java.util.List;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "vaultarhud")
        }
)
@Mixin(value = VaultarHUDOverlay.class, remap = false)
public abstract class MixinScreenValidator {

    @Shadow public static int visibilityMode;

    @Shadow
    private static void updateVaultarItems() {
    }

    @Shadow public static List<VaultarItem> vaultarItems;

    /**
     * @author iwolfking
     * @reason Add vaultarhud compat hack
     */
    @Overwrite
    @SubscribeEvent
    public static void onGUIScreenDraw(ScreenEvent.DrawScreenEvent.Post event) {
        if (visibilityMode != 0 && WoldScreenValidator.isValidScreen(event.getScreen())) {
            updateVaultarItems();
            if (!vaultarItems.isEmpty()) {
                if (visibilityMode == 2) {
                    HUDInventoryRenderer.render(event.getPoseStack(), (Point) null);
                } else {
                    HUDInGameRenderer.render(event.getPoseStack(), new Point(2.0, 0.0));
                }

            }
        }
    }
}
