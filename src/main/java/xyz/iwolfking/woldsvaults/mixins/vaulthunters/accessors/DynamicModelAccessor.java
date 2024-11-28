package xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors;

import iskallia.vault.dynamodel.DynamicModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = DynamicModel.class, remap = false)
public interface DynamicModelAccessor {
    @Accessor("displayName")
    void setName(String name);
}
