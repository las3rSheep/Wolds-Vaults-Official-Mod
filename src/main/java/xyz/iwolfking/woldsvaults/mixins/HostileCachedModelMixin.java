package xyz.iwolfking.woldsvaults.mixins;

import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import shadows.hostilenetworks.data.CachedModel;
import shadows.hostilenetworks.data.ModelTier;
import shadows.hostilenetworks.item.DataModelItem;

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
