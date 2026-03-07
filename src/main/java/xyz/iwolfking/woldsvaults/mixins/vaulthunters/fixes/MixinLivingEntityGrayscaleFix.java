package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.mixin.MixinLivingEntityGrayscale;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LivingEntity.class, priority = 1500)
public abstract class MixinLivingEntityGrayscaleFix extends Entity {
    public MixinLivingEntityGrayscaleFix(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Dynamic(mixin = MixinLivingEntityGrayscale.class)
    @Inject(method = "setGrayscale", at = @At("HEAD"), remap = false)
    private void disableNametagRendering(boolean grayscale, CallbackInfo ci) {
        this.setCustomNameVisible(false);
    }
}
