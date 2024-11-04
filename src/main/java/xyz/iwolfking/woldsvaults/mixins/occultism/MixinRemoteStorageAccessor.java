package xyz.iwolfking.woldsvaults.mixins.occultism;

import com.github.klikli_dev.occultism.util.CuriosUtil;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "occultism")
        }
)
@Mixin(value = CuriosUtil.class, remap = false)
public abstract class MixinRemoteStorageAccessor {
    @Inject(method = "getStorageRemote", at = @At("HEAD"), cancellable = true)
    private static void cancelRemoteInVault(Player player, CallbackInfoReturnable<ItemStack> cir) {
        if(player.level.dimension().location().getNamespace().equals("the_vault")) {
            cir.setReturnValue(null);
        }
    }
}
