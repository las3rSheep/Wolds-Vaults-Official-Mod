package xyz.iwolfking.woldsvaults.init;

import com.mojang.datafixers.types.Type;
import iskallia.vault.VaultMod;
import iskallia.vault.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.p3pp3rf1y.sophisticatedstorage.SophisticatedStorage;
import xyz.iwolfking.woldsvaults.blocks.SophisticatedVaultChestBase;
import xyz.iwolfking.woldsvaults.blocks.VaultSalvagerBlock;
import xyz.iwolfking.woldsvaults.blocks.tiles.SophisticatedVaultChestEntity;
import xyz.iwolfking.woldsvaults.blocks.tiles.VaultSalvagerTileEntity;
import xyz.iwolfking.woldsvaults.client.renderers.SophisticatedVaultChestRenderer;
import xyz.iwolfking.woldsvaults.items.sophisticated.SophisticatedVaultChestItem;
import xyz.iwolfking.woldsvaults.items.sophisticated.SophisticatedVaultStorageBlockItem;

import java.util.function.Consumer;

public class ModBlocks {

    public static final VaultSalvagerBlock VAULT_SALVAGER_BLOCK;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_TREASURE_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_WOODEN_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_ORNATE_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_GILDED_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_LIVING_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_ALTAR_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_HARDENED_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_ENIGMA_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_FLESH_CHEST;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_ORNATE_STRONGBOX;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_LIVING_STRONGBOX;
    public static final SophisticatedVaultChestBase SOPHISTICATED_VAULT_GILDED_STRONGBOX;


    public static final BlockEntityType<VaultSalvagerTileEntity> VAULT_SALVAGER_ENTITY;
    public static final BlockEntityType<SophisticatedVaultChestEntity> SOPHISTICATED_VAULT_CHEST_ENTITY_BLOCK_ENTITY_TYPE;



    static {
        VAULT_SALVAGER_BLOCK = new VaultSalvagerBlock();
        SOPHISTICATED_VAULT_TREASURE_CHEST = new SophisticatedVaultChestBase(() -> 180, () -> 8);
        SOPHISTICATED_VAULT_WOODEN_CHEST = new SophisticatedVaultChestBase(() -> 81, () -> 3);
        SOPHISTICATED_VAULT_ORNATE_CHEST = new SophisticatedVaultChestBase(() -> 120, () -> 4);
        SOPHISTICATED_VAULT_GILDED_CHEST = new SophisticatedVaultChestBase(() -> 120, () -> 4);
        SOPHISTICATED_VAULT_LIVING_CHEST = new SophisticatedVaultChestBase(() -> 120, () -> 4);
        SOPHISTICATED_VAULT_ALTAR_CHEST = new SophisticatedVaultChestBase(() -> 156, () -> 5);
        SOPHISTICATED_VAULT_HARDENED_CHEST = new SophisticatedVaultChestBase(() -> 81, () -> 3);
        SOPHISTICATED_VAULT_ENIGMA_CHEST = new SophisticatedVaultChestBase(() -> 156, () -> 5);
        SOPHISTICATED_VAULT_FLESH_CHEST = new SophisticatedVaultChestBase(() -> 81, () -> 4);
        SOPHISTICATED_VAULT_GILDED_STRONGBOX = new SophisticatedVaultChestBase(() -> 156, () -> 6);
        SOPHISTICATED_VAULT_LIVING_STRONGBOX = new SophisticatedVaultChestBase(() -> 156, () -> 6);
        SOPHISTICATED_VAULT_ORNATE_STRONGBOX = new SophisticatedVaultChestBase(() -> 156, () -> 6);
        VAULT_SALVAGER_ENTITY = BlockEntityType.Builder.of(VaultSalvagerTileEntity::new, new Block[]{VAULT_SALVAGER_BLOCK}).build((Type)null);
        SOPHISTICATED_VAULT_CHEST_ENTITY_BLOCK_ENTITY_TYPE = BlockEntityType.Builder.of(SophisticatedVaultChestEntity::new, new Block[]{SOPHISTICATED_VAULT_TREASURE_CHEST, SOPHISTICATED_VAULT_WOODEN_CHEST, SOPHISTICATED_VAULT_GILDED_CHEST, SOPHISTICATED_VAULT_FLESH_CHEST, SOPHISTICATED_VAULT_ENIGMA_CHEST, SOPHISTICATED_VAULT_LIVING_CHEST, SOPHISTICATED_VAULT_ORNATE_CHEST, SOPHISTICATED_VAULT_HARDENED_CHEST, SOPHISTICATED_VAULT_ALTAR_CHEST, SOPHISTICATED_VAULT_ORNATE_STRONGBOX, SOPHISTICATED_VAULT_LIVING_STRONGBOX, SOPHISTICATED_VAULT_GILDED_STRONGBOX}).build((Type)null);

    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        registerBlock(event, VAULT_SALVAGER_BLOCK, VaultMod.id("vault_salvager"));
        registerBlock(event, SOPHISTICATED_VAULT_TREASURE_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "treasure_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_WOODEN_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "wooden_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_ORNATE_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "ornate_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_GILDED_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "gilded_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_LIVING_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "living_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_ALTAR_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "altar_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_HARDENED_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "hardened_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_ENIGMA_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "enigma_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_FLESH_CHEST, new ResourceLocation(SophisticatedStorage.MOD_ID, "flesh_chest"));
        registerBlock(event, SOPHISTICATED_VAULT_GILDED_STRONGBOX, new ResourceLocation(SophisticatedStorage.MOD_ID, "gilded_strongbox"));
        registerBlock(event, SOPHISTICATED_VAULT_LIVING_STRONGBOX, new ResourceLocation(SophisticatedStorage.MOD_ID, "living_strongbox"));
        registerBlock(event, SOPHISTICATED_VAULT_ORNATE_STRONGBOX, new ResourceLocation(SophisticatedStorage.MOD_ID, "ornate_strongbox"));

    }
    public static void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> event) {
        registerTileEntity(event, VAULT_SALVAGER_ENTITY, VaultMod.id("vault_salvager_tile_entity"));
        registerTileEntity(event, SOPHISTICATED_VAULT_CHEST_ENTITY_BLOCK_ENTITY_TYPE, new ResourceLocation(SophisticatedStorage.MOD_ID, "sophisticated_vault_chest_entity"));
    }

    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        registerBlockItem(event, VAULT_SALVAGER_BLOCK);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_TREASURE_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_WOODEN_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_ORNATE_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_GILDED_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_LIVING_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_ALTAR_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_HARDENED_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_ENIGMA_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_FLESH_CHEST);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_ORNATE_STRONGBOX);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_GILDED_STRONGBOX);
        registerSophisticatedBlockItem(event, SOPHISTICATED_VAULT_LIVING_STRONGBOX);
    }

    public static void registerTileEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(SOPHISTICATED_VAULT_CHEST_ENTITY_BLOCK_ENTITY_TYPE, SophisticatedVaultChestRenderer::new);
    }



    private static void registerBlock(RegistryEvent.Register<Block> event, Block block, ResourceLocation id) {
        block.setRegistryName(id);
        event.getRegistry().register(block);
    }



    private static <T extends BlockEntity> void registerTileEntity(RegistryEvent.Register<BlockEntityType<?>> event, BlockEntityType<?> type, ResourceLocation id) {
        type.setRegistryName(id);
        event.getRegistry().register(type);
    }

    private static void registerBlockItemWithEffect(RegistryEvent.Register<Item> event, Block block, int maxStackSize, Consumer<Item.Properties> adjustProperties) {
        Item.Properties properties = (new Item.Properties()).tab(ModItems.VAULT_MOD_GROUP).stacksTo(maxStackSize);
        adjustProperties.accept(properties);
        BlockItem blockItem = new BlockItem(block, properties) {
            public boolean isFoil(ItemStack stack) {
                return true;
            }
        };
        registerBlockItem(event, block, blockItem);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block) {
        registerBlockItem(event, block, 64);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, int maxStackSize) {
        registerBlockItem(event, block, maxStackSize, (properties) -> {
        });
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, int maxStackSize, Consumer<Item.Properties> adjustProperties) {
        Item.Properties properties = (new Item.Properties()).tab(ModItems.VAULT_MOD_GROUP).stacksTo(maxStackSize);
        adjustProperties.accept(properties);
        registerBlockItem(event, block, new BlockItem(block, properties));
    }

    private static void registerSophisticatedBlockItem(RegistryEvent.Register<Item> event, Block block) {
        SophisticatedVaultStorageBlockItem item = new SophisticatedVaultChestItem(block);
        item.setRegistryName(block.getRegistryName());
        event.getRegistry().register(item);
    }

    private static void registerBlockItem(RegistryEvent.Register<Item> event, Block block, BlockItem blockItem) {
        blockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(blockItem);
    }

    private static void registerTallBlockItem(RegistryEvent.Register<Item> event, Block block) {
        DoubleHighBlockItem tallBlockItem = new DoubleHighBlockItem(block, (new Item.Properties()).tab(ModItems.VAULT_MOD_GROUP).stacksTo(64));
        tallBlockItem.setRegistryName(block.getRegistryName());
        event.getRegistry().register(tallBlockItem);
    }
}
