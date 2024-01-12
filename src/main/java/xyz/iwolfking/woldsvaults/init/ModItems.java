package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.VaultMod;
import iskallia.vault.item.ItemVaultCrystalSeal;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.iwolfking.woldsvaults.WoldsVaults;

@Mod.EventBusSubscriber(modid = WoldsVaults.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static ItemVaultCrystalSeal CRYSTAL_SEAL_MONOLITH;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(CRYSTAL_SEAL_MONOLITH);
    }

    static {
        CRYSTAL_SEAL_MONOLITH= new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_monolith"));
    }
}
