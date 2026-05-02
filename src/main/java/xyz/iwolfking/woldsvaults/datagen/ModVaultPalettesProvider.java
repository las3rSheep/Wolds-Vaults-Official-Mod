package xyz.iwolfking.woldsvaults.datagen;

import com.cursedcauldron.wildbackport.common.registry.WBBlocks;
import iskallia.auxiliaryblocks.AuxiliaryBlocks;
import iskallia.auxiliaryblocks.init.ModBlocks;
import iskallia.vault.VaultMod;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.regions_unexplored.block.RegionsUnexploredBlocks;
import xyz.iwolfking.vhapi.api.datagen.gen.AbstractPaletteProvider;
import xyz.iwolfking.vhapi.api.datagen.lib.gen.palette.PaletteBuilder;
import xyz.iwolfking.woldsvaults.WoldsVaults;

import java.util.*;
import java.util.function.Consumer;

public class ModVaultPalettesProvider extends AbstractPaletteProvider {
    public ModVaultPalettesProvider(DataGenerator generator) {
        super(generator, WoldsVaults.MOD_ID);
    }

    @Override
    protected void registerPalettes() {
        add(WoldsVaults.id("universal_sculk"), new ThemePaletteBuilder(), tb -> {
            tb.placeholder(ThemePaletteBuilder.Placeholder.ORE_PLACEHOLDER_VOID)
                    .placeholder(ThemePaletteBuilder.Placeholder.TREASURE_DOOR)
                    .placeholder(ThemePaletteBuilder.Placeholder.ROOM_BASE)
                    .placeholder(ThemePaletteBuilder.Placeholder.COMMON_ELITE_SPAWNERS)
                    .placeholder(VaultMod.id("generic/spawners/void_mobs"))
                    .replace(ThemePaletteBuilder.ThemeBlockType.WALL_MAIN, replacementBlocks -> {
                        replacementBlocks.put(RegionsUnexploredBlocks.SCULKWOOD_PLANKS.getId(), 3);
                        replacementBlocks.put(RegionsUnexploredBlocks.SCULKWOOD_LOG_DARK.getId(), 1);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.WALL_SECONDARY, RegionsUnexploredBlocks.SCULKWOOD_LEAVES.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.WALL_TERTIARY, RegionsUnexploredBlocks.SCULKWOOD_LOG.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.WALL_FLOURISH, WBBlocks.SCULK.get().getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR, resourceLocationIntegerMap -> {
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULK_GRASS_BLOCK.getId(), 1);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_CARPET, resourceLocationIntegerMap -> {
                        resourceLocationIntegerMap.put(Blocks.AIR.getRegistryName(), 1);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_SLAB, resourceLocationIntegerMap -> {
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULKWOOD_SLAB.getId(), 1);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_SECONDARY, resourceLocationIntegerMap -> {
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULK_GRASS_BLOCK.getId(), 1);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_TERTIRARY, resourceLocationIntegerMap -> {
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULK_GRASS_BLOCK.getId(), 1);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_DECORATION, resourceLocationIntegerMap -> {
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULK_TENDRIL.getId(), 4);
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULK_SPROUT.getId(), 4);
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULKWOOD_SAPLING.getId(), 2);
                        resourceLocationIntegerMap.put(Blocks.WITHER_ROSE.getRegistryName(), 1);
                        resourceLocationIntegerMap.put(Blocks.AIR.getRegistryName(), 12);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_DECORATION_SECONDARY, resourceLocationIntegerMap -> {
                        resourceLocationIntegerMap.put(RegionsUnexploredBlocks.SCULK_TENDRIL.getId(), 3);
                        resourceLocationIntegerMap.put(Blocks.AIR.getRegistryName(), 3);
                    })
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_VARIANT, WBBlocks.SCULK.get().getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.TUNNEL_PILLAR, RegionsUnexploredBlocks.SCULKWOOD_LOG.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.TUNNEL_PILLAR_SECONDARY, WBBlocks.SCULK.get().getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.TUNNEL_PILLAR_ACCENT, RegionsUnexploredBlocks.SCULKWOOD_FENCE.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.TUNNEL_PILLAR_STAIRS, RegionsUnexploredBlocks.SCULKWOOD_STAIRS.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.TUNNEL_VARIANT_PILLAR, RegionsUnexploredBlocks.SCULKWOOD_LOG_DARK.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.TUNNEL_VARIANT_PILLAR_ACCENT, WBBlocks.SCULK.get().getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POST_BLOCK, RegionsUnexploredBlocks.SCULKWOOD_LEAVES.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POST_FENCE, RegionsUnexploredBlocks.SCULKWOOD_LOG.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POST_LIGHT, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POST_VARIANT, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.TUNNEL_SLAB, RegionsUnexploredBlocks.SCULKWOOD_SLAB.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_TALL_DECORATION_LOWER, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_TALL_DECORATION_UPPER, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_ACCENT, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_ACCENT_SECONDARY, WBBlocks.SCULK.get().getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_ACCENT_TERTIARY, Blocks.POLISHED_BLACKSTONE_BRICKS.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_DECORATION, RegionsUnexploredBlocks.SCULKWOOD_LEAVES.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_PLANT, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_VINES, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_HANGING_ACCENT, RegionsUnexploredBlocks.SCULKWOOD_FENCE.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CEILING_SOLID_ACCENT, RegionsUnexploredBlocks.SCULKWOOD_LEAVES.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_VINES, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.FLOOR_PLANT, Blocks.AIR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.FENCE_WOOD_SECONDARY, RegionsUnexploredBlocks.SCULKWOOD_FENCE.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_MAIN, ResourceLocation.parse("chipped:warped_planks_23"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_MAIN_ALT, ResourceLocation.parse("chipped:warped_planks_14"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_MAIN_ALT_SECONDARY, ResourceLocation.parse("chipped:warped_planks_37"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_ACCENT, ResourceLocation.parse("chipped:warped_planks_6"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_PILLAR, ResourceLocation.parse("rechiseled:warped_planks_bricks_connecting"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_STAIRS, ResourceLocation.parse("architects_palette:warped_board_stairs"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_FENCE, ResourceLocation.parse("minecraft:warped_fence"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_FENCE_GATE, ResourceLocation.parse("minecraft:warped_fence_gate"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_LEAVES, RegionsUnexploredBlocks.SCULKWOOD_LEAVES.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_SLAB, ResourceLocation.parse("architects_palette:warped_board_slab"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_SLAB_TERTIARY, ResourceLocation.parse("architects_palette:warped_board_slab"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_VERTICAL_SLAB, ResourceLocation.parse("architects_palette:warped_board_vertical_slab"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_VERTICAL_SLAB_BRICK, ResourceLocation.parse("architects_palette:warped_board_vertical_slab"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_WALL, ResourceLocation.parse("architects_palette:warped_board_wall"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_WALL_SECONDARY, ResourceLocation.parse("architects_palette:warped_board_wall"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_WALL_TERTIARY, ResourceLocation.parse("architects_palette:warped_board_wall"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_LOG, ResourceLocation.parse("minecraft:stripped_warped_stem"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_PLANKS, ResourceLocation.parse("minecraft:warped_planks"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_STAIRS_WOOD, ResourceLocation.parse("minecraft:warped_stairs"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_STONE_STAIRS, ResourceLocation.parse("minecraft:warped_stairs"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_STAIRS_SECONDARY, ResourceLocation.parse("minecraft:warped_stairs"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_TRAPDOOR, ResourceLocation.parse("minecraft:warped_trapdoor"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_WOOD, ResourceLocation.parse("minecraft:warped_hyphae"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_SUPPORT, ResourceLocation.parse("decorative_blocks:warped_support"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_DOOR, ResourceLocation.parse("chipped:warped_door_17"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_CAMPFIRE, ResourceLocation.parse("minecraft:soul_campfire"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_BOOKSHELF, ResourceLocation.parse("quark:warped_bookshelf"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.POI_CONCRETE, ResourceLocation.parse("minecraft:stripped_warped_stem"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.CHAIN, ResourceLocation.parse("buildscape:large_ancient_steel_chain"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.BRIDGE_SLAB, ResourceLocation.parse("minecraft:warped_slab"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.GOD_ALTAR_MAIN, ResourceLocation.parse("rechiseled:warped_planks_small_bricks"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.GOD_ALTAR_ACCENT, ResourceLocation.parse("rechiseled:warped_planks_small_tiles"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.GOD_ALTAR_SLAB, ResourceLocation.parse("architects_palette:warped_board_slab"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.GOD_ALTAR_STAIRS, ResourceLocation.parse("architects_palette:warped_board_stairs"), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.DECORATION_BRAZIER, Blocks.SCULK_SENSOR.getRegistryName(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.STARTING_ROOM_POOL_BOTTOM_LAYER, ModBlocks.CYAN_WATER.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.STARTING_ROOM_POOL_TOP_LAYER, ModBlocks.CYAN_WATER.getId(), 1)
                    .replace(ThemePaletteBuilder.ThemeBlockType.WATER, ModBlocks.CYAN_WATER.getId(), 1);
        });

        add(WoldsVaults.id("spawners_group_standard"), new PaletteBuilder(), p -> {

            p.leveled(leveledBuilder -> {
                leveledBuilder.list(0, "weighted_target", "ispawner:spawner", entries -> {
                    entries.put("ispawner:spawner{group: horde}", 50);
                    entries.put("ispawner:spawner{group: assassin}", 33);
                    entries.put("ispawner:spawner{group: tank}", 12);
                    entries.put("ispawner:spawner{group: dwellers}", 5);
                });
            });

            p.leveled(leveledBuilder -> {
                leveledBuilder.weighted(0, "spawner", "ispawner:spawner{group:dwellers}", 1, entries -> {
                    entries.put("the_vault:vault_fighter", 15);
                });
                leveledBuilder.weighted(20, "spawner", "ispawner:spawner{group:dwellers}", 1, entries -> {
                    entries.put("the_vault:vault_fighter", 15);
                    entries.put("the_vault:vault_fighter_2", 15);
                });
                leveledBuilder.weighted(40, "spawner", "ispawner:spawner{group:dwellers}", 1, entries -> {
                    entries.put("the_vault:vault_fighter_1", 15);
                    entries.put("the_vault:vault_fighter_2", 15);
                });
                leveledBuilder.weighted(60, "spawner", "ispawner:spawner{group:dwellers}", 1, entries -> {
                    entries.put("the_vault:vault_fighter_2", 15);
                    entries.put("the_vault:vault_fighter_3", 15);
                });
                leveledBuilder.weighted(80, "spawner", "ispawner:spawner{group:dwellers}", 1, entries -> {
                    entries.put("the_vault:vault_fighter_3", 15);
                    entries.put("the_vault:vault_fighter_4", 15);
                });
                leveledBuilder.weighted(100, "spawner", "ispawner:spawner{group:dwellers}", 1, entries -> {
                    entries.put("the_vault:vault_fighter_4", 15);
                });
            });

            p.reference("the_vault:generic/spawners/group_settings");
        });

    }
}
