package xyz.iwolfking.woldsvaults.mixins.tomsstorage;

import com.tom.storagemod.util.FilteredInventoryHandler;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "tomsstorage")
        }
)
@Mixin(value = FilteredInventoryHandler.class, remap = false)
public class MixinFilteredInventoryHandler {

    @Shadow private Container filter;

}
