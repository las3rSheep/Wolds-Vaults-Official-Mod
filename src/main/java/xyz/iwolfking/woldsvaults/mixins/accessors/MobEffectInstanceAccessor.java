package xyz.iwolfking.woldsvaults.mixins.accessors;

import net.minecraft.world.effect.MobEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobEffectInstance.class)
public interface MobEffectInstanceAccessor {
    @Accessor
    MobEffectInstance getHiddenEffect();

    @Accessor
    void setHiddenEffect(MobEffectInstance hiddenEffect);

    @Accessor
    void setDuration(int duration);
}
