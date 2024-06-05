package xyz.iwolfking.woldsvaults.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import shadows.hostilenetworks.data.CachedModel;

@Mixin(value = CachedModel.class, remap = false)
public class HostileCachedModelMixin {


    /**
     * @author iwolfking
     * @reason Disable Sim Chamber changing the data
     */
    @Overwrite
    public void setData(int data) {
    }
}
