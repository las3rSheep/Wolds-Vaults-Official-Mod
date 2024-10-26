package xyz.iwolfking.woldsvaults.mixins.hostilenetworks;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import shadows.hostilenetworks.data.CachedModel;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "hostilenetworks")
        }
)
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
