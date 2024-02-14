package xyz.iwolfking.woldsvaults.events.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
/*    */ public class ClientSetupEvents {
    /*    */   @SubscribeEvent(priority = EventPriority.LOW)
    /*    */   public static void setupClient(FMLClientSetupEvent event) {
        /* 18 */
        xyz.iwolfking.woldsvaults.client.init.ModScreens.register();
        /*    */   }
    /*    */ }