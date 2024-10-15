package xyz.iwolfking.woldsvaults.mixins.quark;

import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.tool.ToolItem;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraftforge.event.AnvilUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.content.tools.module.AncientTomesModule;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "quark")
        }
)
@Mixin(value = AncientTomesModule.class, remap = false)
public abstract class MixinAncientTomesModule {

    @Inject(method = "onAnvilUpdate", at = @At("HEAD"), cancellable = true)
    public void onAnvilUpdate(AnvilUpdateEvent event, CallbackInfo ci) {
        if(event.getLeft().getItem() instanceof VaultGearItem || event.getLeft().getItem() instanceof ToolItem) {
            ci.cancel();
        }
    }
}
