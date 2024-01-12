package xyz.iwolfking.woldsvaults.mixins;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import shadows.hostilenetworks.HostileEvents;
import shadows.hostilenetworks.data.DataModel;
import shadows.hostilenetworks.data.ModelTier;
import shadows.hostilenetworks.item.DataModelItem;
import shadows.hostilenetworks.item.DeepLearnerItem;

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
