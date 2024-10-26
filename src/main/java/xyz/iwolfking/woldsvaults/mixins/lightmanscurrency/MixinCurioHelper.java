package xyz.iwolfking.woldsvaults.mixins.lightmanscurrency;

import io.github.lightman314.lightmanscurrency.integration.curios.LCCurios;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "lightmanscurrency")
        }
)
@Mixin(value = LCCurios.class, remap = false)
public class MixinCurioHelper {

    @Inject(method = "lazyGetCuriosHelper", at = @At("HEAD"), cancellable = true)
    private static void lazyGetCuriosHelper(LivingEntity entity, CallbackInfoReturnable<ICuriosItemHandler> cir) {
        if(entity == null) {
            cir.setReturnValue(null);
        }
    }
}
