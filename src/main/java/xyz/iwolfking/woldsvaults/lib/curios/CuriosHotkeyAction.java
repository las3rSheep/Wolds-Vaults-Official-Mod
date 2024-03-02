package xyz.iwolfking.woldsvaults.lib.curios;

import appeng.api.features.HotkeyAction;
import appeng.hotkeys.InventoryHotkeyAction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Predicate;

public record CuriosHotkeyAction(Predicate<ItemStack> locatable,
                                 InventoryHotkeyAction.Opener opener) implements HotkeyAction {

    public CuriosHotkeyAction(Item item, InventoryHotkeyAction.Opener opener) {
        this((stack) -> stack.getItem() == item, opener);
    }

    @Override
    public boolean run(Player player) {
       IItemHandlerModifiable handler = CuriosApi.getCuriosHelper().getEquippedCurios(player).resolve().get();

       for(int i =0; i < handler.getSlots(); i++) {
           if(locatable.test(handler.getStackInSlot(i))) {
               if(opener.open(player, i)) {
                   System.out.println("opened curio");
                   return true;
               }
           }
       }
        return false;
    }
}