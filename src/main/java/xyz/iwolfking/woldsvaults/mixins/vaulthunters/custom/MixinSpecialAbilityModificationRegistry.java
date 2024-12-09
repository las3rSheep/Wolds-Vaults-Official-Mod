package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.gear.attribute.ability.special.base.SpecialAbilityModification;
import iskallia.vault.gear.attribute.ability.special.base.SpecialAbilityModificationRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SpecialAbilityModificationRegistry.class, remap = false)
public abstract class MixinSpecialAbilityModificationRegistry {

    @Shadow
    protected static void register(SpecialAbilityModification<?, ?> modification) {
    }

    @Inject(method = "init", at = @At("TAIL"))
    private static void init(CallbackInfo ci) {

    }
}
