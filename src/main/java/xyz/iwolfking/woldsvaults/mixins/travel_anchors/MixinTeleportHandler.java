package xyz.iwolfking.woldsvaults.mixins.travel_anchors;

import de.castcrafter.travel_anchors.TeleportHandler;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TeleportHandler.class, remap = false)
public class MixinTeleportHandler {

    @Inject(method = "canItemTeleport", at = @At("HEAD"), cancellable = true)
    private static void blockTeleportInVaults(Player player, InteractionHand hand, CallbackInfoReturnable<Boolean> cir) {
        if(player.getLevel().dimension().location().getNamespace().equals("the_vault")) {
            cir.setReturnValue(false);
        }
    }
}
