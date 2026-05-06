package xyz.iwolfking.woldsvaults.mixins.jevh;

import dev.attackeight.just_enough_vh.ClientEvents;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;

@Mixin(value = ClientEvents.class, remap = false)
public class MixinClientEvents {
    @Inject(method = "tooltipEvent", at = @At("HEAD"), cancellable = true)
    private static void hideLootInfo(ItemTooltipEvent event, CallbackInfo ci) {
        if (WoldsVaultsConfig.CLIENT.hideVaultLootInfoTooltip.get()) {
            ci.cancel();
        }
    }
}
