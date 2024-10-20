package xyz.iwolfking.woldsvaults.entities.ghosts.lib;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vazkii.quark.content.mobs.entity.Wraith;

public class GenericEffectWraith extends Wraith {

    private final MobEffectInstance effectInstance;

    public GenericEffectWraith(EntityType<? extends Wraith> type, Level worldIn, MobEffect effect) {
        super(type, worldIn);
        this.effectInstance = new MobEffectInstance(effect);
    }

    public GenericEffectWraith(EntityType<? extends Wraith> type, Level worldIn, MobEffect effect, int duration) {
        super(type, worldIn);
        this.effectInstance = new MobEffectInstance(effect, duration);
    }

    public GenericEffectWraith(EntityType<? extends Wraith> type, Level worldIn, MobEffect effect, int duration, int amplifier) {
        super(type, worldIn);
        this.effectInstance = new MobEffectInstance(effect, duration, amplifier);
    }


    public GenericEffectWraith(EntityType<? extends Wraith> type, Level worldIn, MobEffect effect, int duration, int amplifier, boolean ambient, boolean visibleInventory, boolean visibleCorner) {
        super(type, worldIn);
        this.effectInstance = new MobEffectInstance(effect, duration, amplifier, ambient, visibleInventory, visibleCorner);
    }

    public GenericEffectWraith(EntityType<? extends Wraith> type, Level worldIn, MobEffectInstance effect) {
        super(type, worldIn);
        this.effectInstance = new MobEffectInstance(effect);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entityIn) {
        boolean did = super.doHurtTarget(entityIn);
        if (did) {
            if (entityIn instanceof LivingEntity living) {
                living.addEffect(effectInstance);
            }

            double dx = this.getX() - entityIn.getX();
            double dz = this.getZ() - entityIn.getZ();
            Vec3 vec = (new Vec3(dx, 0.0, dz)).normalize().add(0.0, 0.5, 0.0).normalize().scale(0.85);
            this.setDeltaMovement(vec);
        }

        return did;
    }
}
