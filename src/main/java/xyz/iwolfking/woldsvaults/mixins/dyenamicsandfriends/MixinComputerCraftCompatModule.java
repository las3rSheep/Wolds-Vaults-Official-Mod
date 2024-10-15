package xyz.iwolfking.woldsvaults.mixins.dyenamicsandfriends;

import blusunrize.immersiveengineering.common.util.compat.computers.cctweaked.ComputerCraftCompatModule;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "immersiveengineering")
        }
)
@Mixin(value = ComputerCraftCompatModule.class, remap = false)
public class MixinComputerCraftCompatModule {

    @Inject(method = "attachPeripheral", at = @At("HEAD"), cancellable = true)
    private void attachPeripheral(AttachCapabilitiesEvent<BlockEntity> ev, CallbackInfo ci) {
        ci.cancel();
    }
}
