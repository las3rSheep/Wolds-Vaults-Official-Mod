package xyz.iwolfking.woldsvaults.data;

import iskallia.vault.init.ModBlocks;
import net.minecraft.data.BlockFamily;

public class VaultBlockFamilies {
    public static final BlockFamily VAULT_PLANKS = new BlockFamily.Builder(ModBlocks.WOODEN_PLANKS).slab(ModBlocks.WOODEN_SLAB).stairs(ModBlocks.WOODEN_STAIRS).getFamily();
    public static final BlockFamily VELARA = new BlockFamily.Builder(ModBlocks.VELARA_PLANKS).slab(ModBlocks.VELARA_PLANKS_SLAB).stairs(ModBlocks.VELARA_PLANKS_STAIRS).getFamily();
    public static final BlockFamily TENOS = new BlockFamily.Builder(ModBlocks.TENOS_PLANKS).getFamily();
    public static final BlockFamily OVERGROWN = new BlockFamily.Builder(ModBlocks.OVERGROWN_WOODEN_PLANKS).getFamily();
    public static final BlockFamily CHROMATIC = new BlockFamily.Builder(ModBlocks.CHROMATIC_PLANKS).getFamily();
    public static final BlockFamily DRIFTWOOD = new BlockFamily.Builder(ModBlocks.DRIFTWOOD_PLANKS).getFamily();
}
