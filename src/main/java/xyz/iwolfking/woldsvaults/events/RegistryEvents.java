package xyz.iwolfking.woldsvaults.events;

import iskallia.vault.gear.trinket.TrinketEffect;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.iwolfking.woldsvaults.init.ModBlocks;
import xyz.iwolfking.woldsvaults.init.ModContainers;
import xyz.iwolfking.woldsvaults.init.ModTrinkets;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {
    @SubscribeEvent
    /*     */   public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        /*  53 */     ModBlocks.registerBlocks(event);
        /*     */   }

    @SubscribeEvent
    /*     */   public static void onItemRegister(RegistryEvent.Register<Item> event) {
        /*  61 */     ModBlocks.registerBlockItems(event);
        /*     */   }

    @SubscribeEvent
    /*     */   public static void onTileEntityRegister(RegistryEvent.Register<BlockEntityType<?>> event) {
        /* 108 */     ModBlocks.registerTileEntities(event);
        /*     */   }

    @SubscribeEvent
    /*     */   public static void onContainerRegister(RegistryEvent.Register<MenuType<?>> event) {
        /*  98 */     ModContainers.register(event);
        /*     */   }

    @SubscribeEvent
    /*     */   public static void onTrinketRegistry(RegistryEvent.Register<TrinketEffect<?>> event) {
        /* 146 */     ModTrinkets.init(event);
        /*     */   }
}
