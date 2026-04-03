package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.entity.entity.EffectCloudEntity;
import iskallia.vault.entity.entity.VaultGrenade;
import iskallia.vault.gear.attribute.custom.effect.EffectCloudAttribute;
import iskallia.vault.gear.attribute.type.VaultGearAttributeTypeMerger;
import iskallia.vault.init.ModGearAttributes;
import iskallia.vault.util.calc.AreaOfEffectHelper;
import iskallia.vault.util.calc.EffectDurationHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.api.util.EffectCloudHelper;
import xyz.iwolfking.woldsvaults.api.util.WoldAttributeHelper;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = VaultGrenade.class, remap = false)
public abstract class MixinVaultGrenade extends ThrowableItemProjectile {
    public MixinVaultGrenade(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "explode", at = @At(value = "INVOKE", target = "Liskallia/vault/entity/entity/VaultGrenade;createSafeExplosion(Lnet/minecraft/server/level/ServerPlayer;DDDF)V", ordinal = 0))
    private void explodeEffectClouds(CallbackInfo ci) {
        if(this.getOwner() instanceof ServerPlayer player) {
            List<EffectCloudAttribute> cloudEffects = new ArrayList<>();
            WoldAttributeHelper.withSnapshot(
                    player,
                    true,
                    (playerEntity, snapshot) -> cloudEffects.addAll(snapshot.getAttributeValue(ModGearAttributes.EFFECT_CLOUD_WHEN_HIT, VaultGearAttributeTypeMerger.asList()))
            );

            WoldAttributeHelper.withSnapshot(
                    player,
                    true,
                    (playerEntity, snapshot) -> cloudEffects.addAll(snapshot.getAttributeValue(ModGearAttributes.EFFECT_CLOUD, VaultGearAttributeTypeMerger.asList()))
            );

            EffectCloudHelper.spawnRandomCloud(player, cloudEffects, this.getX(), this.getY(), this.getZ());

        }

    }


}
