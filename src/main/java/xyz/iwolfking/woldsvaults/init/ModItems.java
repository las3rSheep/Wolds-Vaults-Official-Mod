package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.VaultMod;
import iskallia.vault.item.ItemVaultCrystalSeal;
import iskallia.vault.item.LootableItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.items.gear.VaultBattleStaffItem;
import xyz.iwolfking.woldsvaults.items.gear.VaultTridentItem;

import java.util.Random;

import static iskallia.vault.init.ModItems.GEAR_GROUP;
import static iskallia.vault.init.ModItems.VAULT_MOD_GROUP;

@Mod.EventBusSubscriber(modid = WoldsVaults.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_MONOLITH;
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_UNHINGED;

    public static ItemVaultCrystalSeal CRYSTAL_SEAL_SPIRITS;
    public static VaultBattleStaffItem BATTLESTAFF;

    public static VaultTridentItem TRIDENT;

    public static LootableItem GEM_BOX;
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(CRYSTAL_SEAL_MONOLITH);
        registry.register(CRYSTAL_SEAL_UNHINGED);
        registry.register(CRYSTAL_SEAL_SPIRITS);
        registry.register(BATTLESTAFF);
        registry.register(TRIDENT);
        registry.register(GEM_BOX);

    }

    static {
        CRYSTAL_SEAL_MONOLITH = new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_monolith"));
        CRYSTAL_SEAL_UNHINGED = new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_unhinged"));
        CRYSTAL_SEAL_SPIRITS = new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_spirits"));
        BATTLESTAFF = new VaultBattleStaffItem(VaultMod.id("battlestaff"), (new Item.Properties()).tab(GEAR_GROUP).stacksTo(1));
        TRIDENT = new VaultTridentItem(VaultMod.id("trident"), (new Item.Properties()).tab(GEAR_GROUP).stacksTo(1));
        GEM_BOX = new LootableItem(VaultMod.id("gem_box"), (new Item.Properties()).tab(VAULT_MOD_GROUP), () -> (ModConfigs.GEM_BOX.POOL.getRandom(new Random())).get().generateItemStack());

    }
}
