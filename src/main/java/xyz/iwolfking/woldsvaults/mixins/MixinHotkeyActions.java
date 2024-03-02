package xyz.iwolfking.woldsvaults.mixins;


import appeng.api.features.HotkeyAction;
import appeng.core.definitions.AEItems;
import appeng.hotkeys.HotkeyActions;
import appeng.items.tools.powered.WirelessTerminalItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.lib.curios.CuriosHotkeyAction;

@Mixin(value = HotkeyActions.class, remap = false)
public abstract class MixinHotkeyActions {
    static {
        register(new CuriosHotkeyAction(AEItems.WIRELESS_TERMINAL.asItem(), (player, i) -> {
            return ((WirelessTerminalItem)AEItems.WIRELESS_TERMINAL.asItem()).openFromInventory(player, i);
        }), "wireless_terminal");
        register(new CuriosHotkeyAction(AEItems.WIRELESS_CRAFTING_TERMINAL.asItem(), (player, i) -> {
            return ((WirelessTerminalItem)AEItems.WIRELESS_CRAFTING_TERMINAL.asItem()).openFromInventory(player, i);
        }), "wireless_terminal");
    }

    @Shadow
    public static void register(HotkeyAction hotkeyAction, String id) {
    }
}
