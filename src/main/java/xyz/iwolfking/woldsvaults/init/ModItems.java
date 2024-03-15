package xyz.iwolfking.woldsvaults.init;

import iskallia.vault.VaultMod;
import iskallia.vault.item.BasicScavengerItem;
import iskallia.vault.item.ItemVaultCrystalSeal;
import iskallia.vault.item.LootableItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.items.AltarResetItem;
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
    public static ItemVaultCrystalSeal CRYSTAL_SEAL_ENCHANTER;
    public static VaultBattleStaffItem BATTLESTAFF;

   // public static VaultBowItem BOW;

    public static VaultTridentItem TRIDENT;

    //public static VaultAmuletItem VAULT_AMULET;

    public static LootableItem GEM_BOX;
    public static LootableItem SUPPLY_BOX;
    public static LootableItem AUGMENT_BOX;

    public static BasicScavengerItem BENITOITE_GEMSTONE;
    public static BasicScavengerItem WUTODIC_GEMSTONE;
    public static BasicScavengerItem ECHOING_GEMSTONE;
    public static BasicScavengerItem POGGING_GEMSTONE;

    public static final AltarResetItem ALTAR_DECATALYZER;
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(CRYSTAL_SEAL_MONOLITH);
        registry.register(CRYSTAL_SEAL_UNHINGED);
        registry.register(CRYSTAL_SEAL_SPIRITS);
        registry.register(CRYSTAL_SEAL_ENCHANTER);
        registry.register(BATTLESTAFF);
        registry.register(TRIDENT);
        registry.register(GEM_BOX);
        registry.register(SUPPLY_BOX);
        registry.register(AUGMENT_BOX);
        registry.register(BENITOITE_GEMSTONE);
        registry.register(WUTODIC_GEMSTONE);
        registry.register(ECHOING_GEMSTONE);
        registry.register(POGGING_GEMSTONE);
        registry.register(ALTAR_DECATALYZER);
        //registry.register(VAULT_AMULET);
       // registry.register(BOW);

    }

    static {
        CRYSTAL_SEAL_MONOLITH = new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_monolith"));
        CRYSTAL_SEAL_UNHINGED = new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_unhinged"));
        CRYSTAL_SEAL_SPIRITS = new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_spirits"));
        CRYSTAL_SEAL_ENCHANTER = new ItemVaultCrystalSeal(VaultMod.id("crystal_seal_enchanter"));
        BATTLESTAFF = new VaultBattleStaffItem(VaultMod.id("battlestaff"), (new Item.Properties()).tab(GEAR_GROUP).stacksTo(1));
        TRIDENT = new VaultTridentItem(VaultMod.id("trident"), (new Item.Properties()).tab(GEAR_GROUP).stacksTo(1));
        GEM_BOX = new LootableItem(VaultMod.id("gem_box"), (new Item.Properties()).tab(VAULT_MOD_GROUP), () -> (ModConfigs.GEM_BOX.POOL.getRandom(new Random())).get().generateItemStack());
        SUPPLY_BOX = new LootableItem(VaultMod.id("supply_box"), (new Item.Properties()).tab(VAULT_MOD_GROUP), () -> (ModConfigs.SUPPLY_BOX.POOL.getRandom(new Random())).get().generateItemStack());
        AUGMENT_BOX = new LootableItem(VaultMod.id("augment_box"), (new Item.Properties()).tab(VAULT_MOD_GROUP), () -> (ModConfigs.AUGMENT_BOX.POOL.getRandom(new Random())).get().generateItemStack());
        BENITOITE_GEMSTONE = new BasicScavengerItem("benitoite_gemstone");
        WUTODIC_GEMSTONE = new BasicScavengerItem("wutodic_gemstone");
        ECHOING_GEMSTONE = new BasicScavengerItem("echoing_gemstone");
        POGGING_GEMSTONE = new BasicScavengerItem("pogging_gemstone");
        ALTAR_DECATALYZER = new AltarResetItem(VaultMod.id("altar_recatalyzer"), (new Item.Properties().tab(VAULT_MOD_GROUP).rarity(Rarity.RARE)));
        //VAULT_AMULET = new VaultAmuletItem(VaultMod.id("amulet"), VaultAmuletConfig.Size.SMALL);
        //BOW = new VaultBowItem(VaultMod.id("bow"), (new Item.Properties().tab(GEAR_GROUP).stacksTo(1)));
    }
}
