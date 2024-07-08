package xyz.iwolfking.woldsvaults.mixins.immersiveengineering;

import blusunrize.immersiveengineering.common.EventHandler;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

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
