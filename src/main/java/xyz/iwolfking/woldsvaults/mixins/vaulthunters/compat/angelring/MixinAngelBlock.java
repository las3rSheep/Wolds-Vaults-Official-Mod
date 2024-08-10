package xyz.iwolfking.woldsvaults.mixins.vaulthunters.compat.angelring;

import iskallia.vault.block.AngelBlock;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.items.rings.AngelRingItem;

@Mixin(value = AngelBlock.class, remap = false)
public abstract class MixinAngelBlock {


    @Inject(method = "isInRange", at = @At("HEAD"), cancellable = true)
    public void isInRange(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (AngelRingItem.isRingInCurioSlot(player)) {
            cir.setReturnValue(true);
        }
    }
}
