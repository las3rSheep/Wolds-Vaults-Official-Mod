package xyz.iwolfking.woldsvaults.init.client;


import iskallia.vault.entity.model.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import xyz.iwolfking.woldsvaults.WoldsVaults;
import xyz.iwolfking.woldsvaults.entities.ghosts.lib.client.GenericEffectWraithRenderer;
import xyz.iwolfking.woldsvaults.init.ModEntities;
import xyz.iwolfking.woldsvaults.objectives.data.bosses.client.WoldBossRenderer;

@OnlyIn(Dist.CLIENT)
public class ModEntityRenderers {
    public ModEntityRenderers() {
    }

    public static void register(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.WOLD, ctx -> new WoldBossRenderer(ctx, ModModelLayers.FIGHTER));
        EntityRenderers.register(ModEntities.GREEN_GHOST, ctx -> new GenericEffectWraithRenderer(ctx, WoldsVaults.id("textures/entity/green_ghost.png")));
        EntityRenderers.register(ModEntities.BROWN_GHOST, ctx -> new GenericEffectWraithRenderer(ctx, WoldsVaults.id("textures/entity/brown_ghost.png")));
        EntityRenderers.register(ModEntities.BLACK_GHOST, ctx -> new GenericEffectWraithRenderer(ctx, WoldsVaults.id("textures/entity/black_ghost.png")));
        EntityRenderers.register(ModEntities.BLUE_GHOST, ctx -> new GenericEffectWraithRenderer(ctx, WoldsVaults.id("textures/entity/blue_ghost.png")));
    }
}
