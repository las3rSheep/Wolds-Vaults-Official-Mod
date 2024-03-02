package xyz.iwolfking.woldsvaults.mixins;

import appeng.hotkeys.InventoryHotkeyAction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.function.Predicate;

@Mixin(value = InventoryHotkeyAction.class, remap = false)
public abstract class MixinInventoryHotkeyAction {
    @Shadow @Final private Predicate<ItemStack> locatable;
    @Shadow @Final private InventoryHotkeyAction.Opener opener;

    /**
     * @author iwolfking
     * @reason  Add Curio support to Wireless AE Terminal
     */
    @Overwrite
    public boolean run(Player player) {
        NonNullList<ItemStack> items = player.getInventory().items;

        for (int i = 0; i < items.size(); ++i) {
            if (this.locatable.test((ItemStack) items.get(i)) && this.opener.open(player, i)) {
                return true;
            }
        }

        if(CuriosApi.getCuriosHelper().findFirstCurio(player, locatable).isPresent()) {
            SlotResult curioSlot = CuriosApi.getCuriosHelper().findFirstCurio(player, locatable).get();
            if(this.opener.open(player, curioSlot.slotContext().index())) {
                System.out.println("successfully found curio");
                return true;
            }
        }
        CuriosApi.getCuriosHelper().findFirstCurio(player, locatable).ifPresent(slotResult -> {
            this.opener.open(player, slotResult.slotContext().index());
        });

        return false;
    }
}
