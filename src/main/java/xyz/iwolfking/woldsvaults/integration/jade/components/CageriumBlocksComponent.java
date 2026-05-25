package xyz.iwolfking.woldsvaults.integration.jade.components;

import mcp.mobius.waila.api.BlockAccessor;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.ITooltip;
import mcp.mobius.waila.api.config.IPluginConfig;
import net.mehvahdjukaar.cagerium.common.CageriumBlockTile;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import xyz.iwolfking.woldsvaults.integration.jade.WoldsJadePlugin;

public class CageriumBlocksComponent implements IComponentProvider {
    public static final CageriumBlocksComponent INSTANCE = new CageriumBlocksComponent();

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (!iPluginConfig.get(WoldsJadePlugin.INSTANCE)) {
            return;
        }

        Player player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }

        ItemStack mainHandStack = player.getItemInHand(InteractionHand.MAIN_HAND);

        if(!(mainHandStack.getItem() instanceof SpawnEggItem)) {
            return;
        }

        if(blockAccessor.getBlockEntity() instanceof CageriumBlockTile tile && mainHandStack.getItem() instanceof SpawnEggItem spawnEggItem) {
            EntityType<?> eggType = spawnEggItem.getType(mainHandStack.getTag());
            boolean isEggSupported = tile.getTier().acceptsEntityType(eggType);
            iTooltip.add(new TranslatableComponent(isEggSupported ? "tooltip.woldsvaults.egg_supported" : "tooltip.woldsvaults.egg_unsupported"));
        }

    }



}