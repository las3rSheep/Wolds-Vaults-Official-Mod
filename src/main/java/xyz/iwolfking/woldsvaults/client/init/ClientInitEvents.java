package xyz.iwolfking.woldsvaults.client.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.iwolfking.woldsvaults.WoldsVaults;


@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WoldsVaults.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientInitEvents {
   // private static final ResourceLocation CHEST_RL = new ResourceLocation(SophisticatedStorage.MOD_ID, "chest");
    //public static final ModelLayerLocation CHEST_LAYER = new ModelLayerLocation(CHEST_RL, "main");
   @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        //event.registerLayerDefinition(CHEST_LAYER, () -> SophisticatedVaultChestRenderer.createSingleBodyLayer(true));
    }
}
