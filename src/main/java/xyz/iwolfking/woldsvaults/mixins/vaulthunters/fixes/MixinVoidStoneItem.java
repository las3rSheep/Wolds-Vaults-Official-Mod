package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.item.gear.VoidStoneItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import javax.annotation.Nullable;
import java.util.List;

@Mixin(value = VoidStoneItem.class, remap = true)
public abstract class MixinVoidStoneItem {
    /**
     * @author iwolfking
     * @reason Why I gotta do this?
     */
    @Overwrite
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        //learn how to null check VH devs smh
    }
}
