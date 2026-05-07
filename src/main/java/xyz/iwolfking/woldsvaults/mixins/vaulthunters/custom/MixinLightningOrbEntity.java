package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.entity.entity.LightningOrbEntity;
import iskallia.vault.init.ModParticles;
import iskallia.vault.init.ModSounds;
import iskallia.vault.util.calc.AbilityPowerHelper;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.mixins.accessors.ThrowableItemProjectileAccessor;

import java.util.List;

@Mixin(value = LightningOrbEntity.class, remap = false)
public abstract class MixinLightningOrbEntity extends ThrowableItemProjectile {

    @Shadow
    @Final
    private static EntityDataAccessor<Float> RADIUS;
    @Shadow @Final private static EntityDataAccessor<Float> DAMAGE_MIN;
    @Shadow @Final private static EntityDataAccessor<Float> DAMAGE_MAX;

    public MixinLightningOrbEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Shadow
    protected abstract void applyLightningDamage(LivingEntity target, float damage, Player player);

    @Shadow
    protected abstract Player getShooter();

    @Unique
    private int woldsVaults$chainCooldown = 0;

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/ThrowableItemProjectile;tick()V", shift = At.Shift.AFTER), remap = true)
    private void tickChains(CallbackInfo ci) {
        if (this.level.isClientSide) return;

        if (woldsVaults$chainCooldown > 0) {
            woldsVaults$chainCooldown--;
            return;
        }

        float searchRadius = 10.0f;
        List<LightningOrbEntity> nearbyOrbs = this.level.getEntitiesOfClass(
            LightningOrbEntity.class, 
            this.getBoundingBox().inflate(searchRadius),
            orb -> orb != (Object)this && orb.tickCount > this.tickCount
        );

        for (LightningOrbEntity otherOrb : nearbyOrbs) {
            woldsVaults$createChainEffect(otherOrb);
            woldsVaults$damageEntitiesBetween(otherOrb);
        }

        this.woldsVaults$chainCooldown = 5;
    }

    @Unique
    private void woldsVaults$damageEntitiesBetween(LightningOrbEntity other) {
        Vec3 start = this.position();
        Vec3 end = other.position();

        AABB chainBox = new AABB(start, end).inflate(1.0);
        List<LivingEntity> targets = this.level.getEntitiesOfClass(LivingEntity.class, chainBox,
            e -> e.isAlive() && !(e instanceof Player));

        for (LivingEntity target : targets) {
            if (target.getBoundingBox().clip(start, end).isPresent()) {
                float damage = woldsVaults$getChainDamage();
                applyLightningDamage(target, damage, getShooter());
            }
        }
    }

    @Unique
    private float woldsVaults$getChainDamage() {
        float avgDamage = (this.entityData.get(DAMAGE_MIN) + this.entityData.get(DAMAGE_MAX)) / 2.0f;
        return AbilityPowerHelper.getAbilityPower(getShooter()) * avgDamage * 1.25f;
    }

    @Unique
    private void woldsVaults$createChainEffect(LightningOrbEntity other) {
        Vec3 start = this.position().add(0, 0.25, 0);
        Vec3 end = other.position().add(0, 0.25, 0);
        Vec3 direction = end.subtract(start);
        double distance = direction.length();

        int particleCount = (int) (distance * 5);

        for (int i = 0; i < particleCount; i++) {
            double ratio = (double) i / particleCount;

            double posX = start.x + (direction.x * ratio);
            double posY = start.y + (direction.y * ratio);
            double posZ = start.z + (direction.z * ratio);

            double jitter = 0.15;
            posX += (this.random.nextDouble() - 0.5) * jitter;
            posY += (this.random.nextDouble() - 0.5) * jitter;
            posZ += (this.random.nextDouble() - 0.5) * jitter;

            if (this.level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        (ParticleOptions) ModParticles.LIGHTNING_ORB.get(),
                        posX, posY, posZ,
                        1,
                        0, 0, 0,
                        0.0
                );
            }
        }

        if (this.random.nextFloat() < 0.2f) {
            this.level.playSound(null, start.x + direction.x/2, start.y + direction.y/2, start.z + direction.z/2,
                    ModSounds.LIGHTING_BOLT, SoundSource.PLAYERS, 0.2F, 2.0F);
        }
    }
    @Inject(method = "defineSynchedData", at = @At("HEAD"), remap = true)
    private void fixMissingItemData(CallbackInfo ci) {
        this.entityData.define(ThrowableItemProjectileAccessor.getDataItemStack(), ItemStack.EMPTY);
    }
}