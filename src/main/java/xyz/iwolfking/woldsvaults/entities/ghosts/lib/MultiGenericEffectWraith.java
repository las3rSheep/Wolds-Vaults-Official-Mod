package xyz.iwolfking.woldsvaults.entities.ghosts.lib;

import iskallia.vault.util.data.WeightedList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import vazkii.quark.content.mobs.entity.Wraith;

public class MultiGenericEffectWraith extends Wraith {

    private final WeightedList<MobEffectInstance> effectList;
    public MultiGenericEffectWraith(EntityType<? extends Wraith> type, Level worldIn, WeightedList<MobEffectInstance> effectList) {
        super(type, worldIn);
        this.effectList = effectList;
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity entityIn) {
        boolean did = super.doHurtTarget(entityIn);
        if (did) {
            if (entityIn instanceof LivingEntity living) {
                if(effectList != null) {
                    if(!effectList.isEmpty()) {
                        living.addEffect(effectList.getRandom(random));
                    }
                }

            }

            double dx = this.getX() - entityIn.getX();
            double dz = this.getZ() - entityIn.getZ();
            Vec3 vec = (new Vec3(dx, 0.0, dz)).normalize().add(0.0, 0.5, 0.0).normalize().scale(0.85);
            this.setDeltaMovement(vec);
        }

        return did;
    }
}
