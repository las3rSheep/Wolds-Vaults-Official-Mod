package xyz.iwolfking.woldsvaults.mixins.immersiveengineering;

import blusunrize.immersiveengineering.common.EventHandler;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "immersiveengineering")
        }
)
@Mixin(value = EventHandler.class, remap = false)
public class MixinEventHandler {

    /**
     * @author iwolfking
     * @reason Disable dropping shader bags in vaults
     */
    @Overwrite
    public void onLivingDrops(LivingDropsEvent event) {

    }
}
