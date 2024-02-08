package xyz.iwolfking.woldsvaults.mixins;


import iskallia.vault.event.AnvilEvents;
import net.minecraftforge.event.AnvilUpdateEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = AnvilEvents.class, remap = false)
public class MixinAnvilEvents {

    @Inject(method = "onApplySeal", at = @At("TAIL"))
    private static void lowerSealCost(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplySoulFlame", at = @At("TAIL"))
    private static void lowerSoulFlameCost(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyAugment", at = @At("TAIL"))
    private static void lowerAugmentCost(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyCatalyst", at = @At("TAIL"))
    private static void lowerCatalystCost(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyBanishedSoul", at = @At("TAIL"))
    private static void lowerBanishedSoul(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyChaosCatalyst", at = @At("TAIL"))
    private static void onApplyChaos(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyCharm", at = @At("TAIL"))
    private static void onApplyCharm(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyGodShard", at = @At("TAIL"))
    private static void onApplyGodShard(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyInscription", at = @At("TAIL"))
    private static void onApplyInscription(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyMote", at = @At("TAIL"))
    private static void onApplyMote(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyWardensSeal", at = @At("TAIL"))
    private static void onApplyWardensSeal(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyParadoxicalGem", at = @At("TAIL"))
    private static void onApplyParadoxical(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyPlundererSeal", at = @At("TAIL"))
    private static void onApplyPlunderer(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyPhoenix", at = @At("TAIL"))
    private static void onApplyPhoenix(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }

    @Inject(method = "onApplyLootersDream", at = @At("TAIL"))
    private static void onApplyLootersDream(AnvilUpdateEvent event, CallbackInfo ci) {
        event.setCost(0);
    }
}
