package xyz.iwolfking.woldsvaults.mixins.hostilenetworks;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import shadows.hostilenetworks.HostileEvents;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "hostilenetworks")
        }
)
@Mixin(value = HostileEvents.class, remap = false)
public class HostileEventsMixin {
    /**
     * @author iwolfking
     * @reason Disable model updates from kills.
     */
    @Overwrite
    public static void updateModels(ItemStack learner, EntityType<?> type, int bonus) {
    }
}
