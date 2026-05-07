package xyz.iwolfking.woldsvaults.mixins;

import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = Attributes.class)
public class MixinAttributes {

    /**
     * @author aida
     * @reason overfruit - reduce floor of maxHP in order to preserve eHP penalties if a player fruits with high hp, then swaps armor
     */
    @ModifyConstant(method = "<clinit>",
                    constant = @Constant(doubleValue = 1.0,
                                         ordinal = 0))
    private static double tweakMinMaxHP(double constant) {
        return Float.MIN_NORMAL;
    }
}
