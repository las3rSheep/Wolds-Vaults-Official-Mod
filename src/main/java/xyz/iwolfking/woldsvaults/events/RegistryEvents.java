package xyz.iwolfking.woldsvaults.events;

import iskallia.vault.gear.trinket.TrinketEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import xyz.iwolfking.woldsvaults.init.ModBlocks;
import xyz.iwolfking.woldsvaults.init.ModContainers;
import xyz.iwolfking.woldsvaults.init.ModEffects;
import xyz.iwolfking.woldsvaults.init.ModTrinkets;
import xyz.iwolfking.woldsvaults.items.gear.amulet.VaultAmuletEffectRegistry;
import xyz.iwolfking.woldsvaults.lib.CustomScaleTypes;

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

    @SubscribeEvent
    /*     */   public static void ohRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        /*  74 */     ModBlocks.registerTileEntityRenderers(event);
        /*     */   }

    @SubscribeEvent
    /*     */   public static void onNewRegistryRegister(NewRegistryEvent event) {
                        VaultAmuletEffectRegistry.buildRegistry(event);
        /*     */   }

    @SubscribeEvent
    /*     */   public static void onEffectRegister(RegistryEvent.Register<MobEffect> event) {
                      CustomScaleTypes.init();
        /* 118 */     ModEffects.register(event);

        /*     */   }
}
