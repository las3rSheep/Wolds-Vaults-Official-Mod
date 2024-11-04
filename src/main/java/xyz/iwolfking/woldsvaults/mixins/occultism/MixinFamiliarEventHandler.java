package xyz.iwolfking.woldsvaults.mixins.occultism;

import com.github.klikli_dev.occultism.handlers.FamiliarEventHandler;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "occultism")
        }
)
@Mixin(value = FamiliarEventHandler.class, remap = false)
public class MixinFamiliarEventHandler {
    @Inject(method = "livingDeathEvent", at = @At("HEAD"), cancellable = true)
    private static void cancelDeathEventsInVault(LivingDeathEvent event, CallbackInfo ci) {
        if(event.getEntity().level.dimension().location().getNamespace().equals("the_vault")) {
            ci.cancel();
        }
    }

    @Inject(method = "livingDamageEvent", at = @At("HEAD"), cancellable = true)
    private static void cancelDamageEventInVault(LivingDamageEvent event, CallbackInfo ci) {
        if(event.getEntity().level.dimension().location().getNamespace().equals("the_vault")) {
            ci.cancel();
        }
    }
}
