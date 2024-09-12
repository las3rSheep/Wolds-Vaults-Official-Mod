package xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors;

import iskallia.vault.item.InscriptionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import xyz.iwolfking.woldsvaults.api.registry.CustomInscriptionModelRegistry;

@Mixin(value = InscriptionItem.class, remap = false)
public class MixinInscriptionItem {
    /**
     * @return Returns the new number of inscription models after the custom registry.
     * @author iwolfking
     * @reason To allow new Inscription Models to be registered.
     */
    @ModifyConstant(method = "loadModels", constant = @Constant(intValue = 16))
    public int loadModels(int constant) {
        return CustomInscriptionModelRegistry.getSize();
    }
}
