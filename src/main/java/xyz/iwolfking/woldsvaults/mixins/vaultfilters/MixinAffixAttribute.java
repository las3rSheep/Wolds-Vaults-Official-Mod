package xyz.iwolfking.woldsvaults.mixins.vaultfilters;

import iskallia.vault.gear.attribute.VaultGearModifier;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.joseph.vaultfilters.attributes.abstracts.AffixAttribute;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.modifiers.vault.lib.StringValueGenerator;

@Restriction(
    require = {
        @Condition(type = Condition.Type.MOD, value = "vaultfilters"),
    }
)
@Mixin(value = AffixAttribute.class, remap = false)
public class MixinAffixAttribute {
    @Inject(method = "getName", at = @At("HEAD"), cancellable = true)
    private static <T> void stringAttr(VaultGearModifier<T> modifier, CallbackInfoReturnable<String> cir){
        if (modifier.getAttribute().getGenerator() instanceof StringValueGenerator && modifier.getValue() instanceof String stVal) {
            var fmtVal = modifier.getAttribute().getReader().getValueDisplay(modifier.getValue());
            if (fmtVal == null) {
                cir.setReturnValue(stVal);
                return;
            }
            var fmtStr = fmtVal.getString();
            if (fmtStr == null || fmtStr.isEmpty()) {
                cir.setReturnValue(stVal);
                return;
            }

            cir.setReturnValue(fmtStr);
        }
    }
}
