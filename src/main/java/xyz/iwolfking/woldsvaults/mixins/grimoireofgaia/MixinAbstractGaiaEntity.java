package xyz.iwolfking.woldsvaults.mixins.grimoireofgaia;

import gaia.entity.AbstractGaiaEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = AbstractGaiaEntity.class, remap = false)
public abstract class MixinAbstractGaiaEntity extends Monster {
    protected MixinAbstractGaiaEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    @Shadow public abstract float getBaseDefense();

    /**
     * @author iwolfking
     * @reason Remove Gaia Damage Cap
     */
    @Overwrite
    protected float getBaseDamage(DamageSource source, float damage) {
        return damage * 0.75F;
    }
}