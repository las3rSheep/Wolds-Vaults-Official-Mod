package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import iskallia.vault.event.InputEvents;
import iskallia.vault.research.ResearchTree;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = InputEvents.class, remap = false)
public class MixinInputEvents {
    @WrapOperation(method = "onInput", at = @At(value = "INVOKE", target = "Liskallia/vault/research/ResearchTree;isResearched(Ljava/lang/String;)Z"))
    private static boolean cancelAllButCompassResearchChecks(ResearchTree instance, String researchName, Operation<Boolean> original) {
        if(researchName.equals("Vault Compass")) {
            return original.call(instance, researchName);
        }

        return true;
    }
}
