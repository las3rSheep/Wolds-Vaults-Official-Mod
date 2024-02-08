package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.init.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.config.GemBoxConfig;
import xyz.iwolfking.woldsvaults.config.HauntedBraziersConfig;
import xyz.iwolfking.woldsvaults.config.UnhingedScavengerConfig;


@Mixin(ModConfigs.class)
public class MixinModConfigs {

    @Inject(method = "register", at = @At("TAIL"), remap = false)
    private static void onReloadConfigs(CallbackInfo ci) {
        xyz.iwolfking.woldsvaults.init.ModConfigs.GEM_BOX = (GemBoxConfig) (new GemBoxConfig()).readConfig();
        xyz.iwolfking.woldsvaults.init.ModConfigs.UNHINGED_SCAVENGER = (UnhingedScavengerConfig) (new UnhingedScavengerConfig().readConfig());
        xyz.iwolfking.woldsvaults.init.ModConfigs.HAUNTED_BRAZIERS = (HauntedBraziersConfig) (new HauntedBraziersConfig().readConfig());
    }
}
