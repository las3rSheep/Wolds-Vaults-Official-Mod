package xyz.iwolfking.woldsvaults.mixins.occultism;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotContext;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "occultism")
        }
)
@Mixin(targets = "com.github.klikli_dev.occultism.common.item.tool.FamiliarRingItem$Curio", remap = false)
public abstract class MixinFamiliarRingItemCurio
{
    /**
     * @author iwolfking
     * @reason Disable Familiar Ring effects in vaults.
     */
    @Inject(method = "curioTick", at = @At("HEAD"), cancellable = true)
    public void curioTick(SlotContext slotContext, CallbackInfo ci) {
        if(slotContext.entity().level.dimension().location().getNamespace().equals("the_vault")) {
            ci.cancel();
        }
    }
}
