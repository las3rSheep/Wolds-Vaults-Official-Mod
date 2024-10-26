package xyz.iwolfking.woldsvaults.mixins.ensorcellation;


import cofh.ensorcellation.init.EnsorcEnchantments;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "ensorcellation")
        }
)
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
