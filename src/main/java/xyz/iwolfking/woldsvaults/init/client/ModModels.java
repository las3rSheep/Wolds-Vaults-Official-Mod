package xyz.iwolfking.woldsvaults.init.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import xyz.iwolfking.woldsvaults.init.ModBlocks;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModModels {
    public static void setupRenderLayers() {
        ItemBlockRenderTypes.setRenderLayer((Block) ModBlocks.SURVIVAL_MOB_BARRIER, RenderType.translucent());
    }
}
