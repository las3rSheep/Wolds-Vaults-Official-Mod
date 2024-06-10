package xyz.iwolfking.woldsvaults.mixins.ensorcellation;


import cofh.ensorcellation.init.EnsorcEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = EnsorcEnchantments.class, remap = false)
public class MixinEnsorcEnchantments {
    /**
     * @author iwolfking
     * @reason Remove vanilla enchantment overrides
     */
    @Overwrite
    public static void register() {

    }
}
