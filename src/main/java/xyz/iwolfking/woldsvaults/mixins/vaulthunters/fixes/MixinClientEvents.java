package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.event.ClientEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;

import java.util.List;

@Mixin(value = ClientEvents.class, remap = false)
public class MixinClientEvents {

    /**
     * @author iwolfking
     * @reason Remove supporters button.
     */
    @Overwrite
    public static void onGuiInit(ScreenEvent.InitScreenEvent event) {
    }

    @Inject(method = "addLootTableInfoToTooltip", at = @At("HEAD"), cancellable = true)
    private static void hideLootInfo(ItemStack itemStack, List<Component> toolTip, CallbackInfo ci){
        if (WoldsVaultsConfig.CLIENT.hideVaultLootInfoTooltip.get()) {
            ci.cancel();
        }
    }
}
