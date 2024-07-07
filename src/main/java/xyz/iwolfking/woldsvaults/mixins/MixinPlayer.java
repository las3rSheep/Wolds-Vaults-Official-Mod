package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.block.VaultOreBlock;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.item.tool.ToolItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(value = Player.class)
public class MixinPlayer
{

    //Patches the bug that causes mining chests to be really slow if you started mining them in the air.
    //Credits to B0nne from the Vault Hunters discord.


    @Unique
    private final List<Block> AIR_SPEED_IGNORED_BLOCKS = List.of(ModBlocks.WOODEN_CHEST, ModBlocks.ORNATE_CHEST, ModBlocks.GILDED_CHEST, ModBlocks.ENIGMA_CHEST, ModBlocks.LIVING_CHEST, ModBlocks.HARDENED_CHEST, ModBlocks.FLESH_CHEST);

    @Redirect(method = "getDigSpeed", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/player/Player;onGround:Z"))
    private boolean redirectOnGround(Player player, BlockState state, BlockPos pos)
    {
        if (player.getLevel().dimension().location().getNamespace().equals("the_vault") && player.getMainHandItem().getItem() instanceof ToolItem && (state.getBlock() instanceof VaultOreBlock || AIR_SPEED_IGNORED_BLOCKS.contains(state.getBlock())))
        {
            System.out.println("FIXED AIR SPEED");
            return true;
        }

        return player.isOnGround();
    }
}