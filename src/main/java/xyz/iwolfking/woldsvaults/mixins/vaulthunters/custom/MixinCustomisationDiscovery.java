package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.config.customisation.CustomisationDiscovery;
import iskallia.vault.core.data.adapter.basic.TypeSupplierAdapter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.api.core.discovery.customisation.ThemeCustomisationDiscovery;

@Mixin(value = CustomisationDiscovery.class, remap = false)
public class MixinCustomisationDiscovery {
    @Shadow
    public static TypeSupplierAdapter<CustomisationDiscovery> ADAPTER;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void registerNewTypes(CallbackInfo ci) {
        ADAPTER.register("theme", ThemeCustomisationDiscovery.class, ThemeCustomisationDiscovery::new);
    }
}
