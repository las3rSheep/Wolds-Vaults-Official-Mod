package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.VaultMod;
import iskallia.vault.item.ItemVaultCrystalSeal;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.items.gear.VaultBattleStaffItem;
import xyz.iwolfking.woldsvaults.items.gear.VaultTridentItem;

import static iskallia.vault.init.ModItems.GEAR_GROUP;

@Mod.EventBusSubscriber(modid = WoldsVaults.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static ItemVaultCrystalSeal CRYSTAL_SEAL_MONOLITH;
    public static VaultBattleStaffItem BATTLESTAFF;

    public static VaultTridentItem TRIDENT;
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(CRYSTAL_SEAL_MONOLITH);
        registry.register(BATTLESTAFF);
        registry.register(TRIDENT);
    }

    static {
        CRYSTAL_SEAL_MONOLITH= new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_monolith"));
        BATTLESTAFF = new VaultBattleStaffItem(VaultMod.id("battlestaff"), (new Item.Properties()).tab(GEAR_GROUP).stacksTo(1));
        TRIDENT = new VaultTridentItem(VaultMod.id("trident"), (new Item.Properties()).tab(GEAR_GROUP).stacksTo(1));
    }
}
