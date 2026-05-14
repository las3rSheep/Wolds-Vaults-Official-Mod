package xyz.iwolfking.woldsvaults.entities.projectiles;

import iskallia.vault.entity.entity.PetEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import xyz.iwolfking.woldsvaults.init.ModEntities;
import xyz.iwolfking.woldsvaults.mixins.accessors.EvokerFangsAccessor;

import java.util.List;

public class CustomFangEntity extends EvokerFangs {
    private float customDamage = 6.0F;
    private float healAmount = 0.0F;
    private boolean isMaw = false;



    public CustomFangEntity(Level level, double x, double y, double z, float yRot, int warmup, LivingEntity owner, float damage, float heal, boolean isMaw) {
        this(ModEntities.CUSTOM_FANGS, level);
        this.setPos(x, y, z);
        this.setYRot(yRot);
        this.customDamage = damage;
        this.healAmount = heal;
        this.isMaw = isMaw;
        this.setOwner(owner);
    }

    public CustomFangEntity(EntityType<?> entityEntityType, Level level) {
        super((EntityType<? extends EvokerFangs>) entityEntityType, level);
    }

    public void tick() {
        if (this.level.isClientSide) {
            if (((EvokerFangsAccessor)this).getClientSideAttackStarted()) {
                ((EvokerFangsAccessor)this).setLifeTicks(((EvokerFangsAccessor)this).getLifeTicks() - 1);
                if (((EvokerFangsAccessor)this).getLifeTicks() == 14) {
                    for(int i = 0; i < 12; ++i) {
                        double d0 = this.getX() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
                        double d1 = this.getY() + 0.05D + this.random.nextDouble();
                        double d2 = this.getZ() + (this.random.nextDouble() * 2.0D - 1.0D) * (double)this.getBbWidth() * 0.5D;
                        double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
                        double d4 = 0.3D + this.random.nextDouble() * 0.3D;
                        double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
                        this.level.addParticle(ParticleTypes.CRIT, d0, d1 + 1.0D, d2, d3, d4, d5);
                    }
                }
            }
        }
        else if (((EvokerFangsAccessor)this).getWarmupDelayTicks() - 1 < 0) {
            if (((EvokerFangsAccessor)this).getWarmupDelayTicks() == -8) {
                for(LivingEntity livingentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2D, 0.0D, 0.2D))) {
                    this.dealDamageTo(livingentity);
                }
            }

            if (!((EvokerFangsAccessor)this).getSentSpikeEvent()) {
                this.level.broadcastEntityEvent(this, (byte)4);
                ((EvokerFangsAccessor)this).setSentSpikeEvent(true);
            }

            if (((EvokerFangsAccessor)this).getLifeTicks() - 1 < 0) {
                this.discard();
            }
        }
        ((EvokerFangsAccessor)this).setWarmupDelayTicks(((EvokerFangsAccessor) this).getWarmupDelayTicks() - 1);
    }

    private void dealDamageTo(LivingEntity pTarget) {
        LivingEntity livingentity = this.getOwner();
        if (this.isMaw && this.getOwner() != null) {
            Vec3 pullDir = this.getOwner().position().subtract(pTarget.position());

            double distance = pullDir.horizontalDistance();

            if (distance > 1.0) {
                Vec3 motion = pullDir.normalize().scale(0.65);

                pTarget.setDeltaMovement(new Vec3(motion.x, 0.2, motion.z));
                pTarget.hurtMarked = true;
            }
        }
        if (pTarget.isAlive() && !pTarget.isInvulnerable() && pTarget != livingentity && livingentity instanceof Player owner) {
            pTarget.hurt(DamageSource.playerAttack(owner), customDamage);
        }

    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putFloat("CustomDamage", this.customDamage);
        nbt.putFloat("HealAmount", this.healAmount);
        nbt.putBoolean("IsMaw", this.isMaw);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.customDamage = nbt.getFloat("CustomDamage");
        this.healAmount = nbt.getFloat("HealAmount");
        this.isMaw = nbt.getBoolean("IsMaw");
    }
}