package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.block.ShopPedestalBlock;
import iskallia.vault.block.entity.ShopPedestalBlockTile;
import iskallia.vault.container.oversized.OverSizedItemStack;
import iskallia.vault.event.event.ShopPedestalPriceEvent;
import iskallia.vault.init.ModBlocks;
import iskallia.vault.util.InventoryUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.GameMasterBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.*;
import xyz.iwolfking.woldsvaults.data.CoinDefinitions;

import javax.annotation.Nullable;
import java.util.*;

@Mixin(value = ShopPedestalBlock.class, remap = false)
public class MixinShopPedestalBlock extends Block implements EntityBlock, GameMasterBlock {


    @Shadow @Final public static VoxelShape SHAPE;
    @Shadow @Final public static BooleanProperty ACTIVE;
    @Unique
    private static Map<Item, CoinDefinitions.CoinDefinition> WOLD_COIN_DEFINITIONS;

    public MixinShopPedestalBlock(Properties p_49795_) {
        super(p_49795_);
    }



    /**
     * @author iwolfking
     * @reason Handle shop pedestal to extend coin definitions
     */
    @Overwrite
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        BlockEntity var8 = worldIn.getBlockEntity(pos);
        ItemStack c;
        if (var8 instanceof ShopPedestalBlockTile tile) {
            if (tile.isInitialized() && handIn == InteractionHand.MAIN_HAND) {
                c = tile.getOfferStack();
                if (!c.isEmpty()) {
                    ShopPedestalPriceEvent event = new ShopPedestalPriceEvent(player, c, tile.getCurrencyStack());
                    MinecraftForge.EVENT_BUS.post(event);
                    ItemStack currency = event.getCost();
                    return (InteractionResult) player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (Direction) null).map((itemHandler) -> {
                        List<InventoryUtil.ItemAccess> allItems = List.of();
                        if (!player.isCreative()) {
                            allItems = InventoryUtil.findAllItems(player);
                            if (!woldsVaults$hasEnoughCurrency(allItems, currency)) {
                                if (worldIn.isClientSide) {
                                    player.displayClientMessage(new TranslatableComponent("message.the_vault.shop_pedestal.fail", new Object[]{currency.getHoverName()}), true);
                                }

                                return InteractionResult.sidedSuccess(worldIn.isClientSide);
                            }
                        }

                        if (!worldIn.isClientSide) {
                            if (!player.isCreative()) {
                                woldsVaults$extractCurrency(player, allItems, currency);
                                BlockState inactiveState = (BlockState) state.setValue(ACTIVE, false);
                                tile.setRemoved();
                                worldIn.setBlockAndUpdate(pos, inactiveState);
                            }

                            ItemHandlerHelper.giveItemToPlayer(player, c.copy());
                            worldIn.playSound((Player) null, pos, SoundEvents.AMETHYST_BLOCK_STEP, SoundSource.BLOCKS, 1.0F, 1.0F);
                        } else {
                            if (!player.getAbilities().instabuild) {
                                tile.setRemoved();
                            }

                            player.displayClientMessage(new TranslatableComponent("message.the_vault.shop_pedestal.purchase", new Object[]{c.getCount(), c.getHoverName(), currency.getCount(), currency.getHoverName()}), true);
                        }

                        return InteractionResult.sidedSuccess(worldIn.isClientSide);
                    }).orElse(InteractionResult.PASS);
                }

                return InteractionResult.PASS;
            }
        }

        if (player.getAbilities().instabuild) {
            ItemStack o = player.getItemInHand(InteractionHand.MAIN_HAND);
            c = player.getItemInHand(InteractionHand.OFF_HAND);
            if (!c.isEmpty() && !o.isEmpty()) {
                worldIn.setBlockAndUpdate(pos, (BlockState) state.setValue(ACTIVE, true));
                BlockEntity var10 = worldIn.getBlockEntity(pos);
                if (var10 instanceof ShopPedestalBlockTile) {
                    ShopPedestalBlockTile tile = (ShopPedestalBlockTile) var10;
                    tile.setOffer(o.copy(), OverSizedItemStack.of(c.copy()));
                    tile.setChanged();
                    return InteractionResult.sidedSuccess(worldIn.isClientSide);
                }

                worldIn.setBlockAndUpdate(pos, (BlockState) state.setValue(ACTIVE, false));
            }
        }

        return InteractionResult.PASS;
    }

    @Unique
    private boolean woldsVaults$hasEnoughCurrency(List<InventoryUtil.ItemAccess> allItems, ItemStack currency) {
        return (Boolean) woldsVaults$getCoinDefinition(currency.getItem()).map((priceCoinDefinition) -> {
            int priceValue = priceCoinDefinition.coinValue() * currency.getCount();
            Iterator var4 = allItems.iterator();

            do {
                if (!var4.hasNext()) {
                    return false;
                }


                InventoryUtil.ItemAccess itemAccess = (InventoryUtil.ItemAccess) var4.next();
                priceValue -= (Integer) woldsVaults$getCoinDefinition(itemAccess.getStack().getItem()).map((coinDefinition) -> {
                    return coinDefinition.coinValue() * itemAccess.getStack().getCount();
                }).orElse(0);
            } while (priceValue > 0);

            return true;
        }).orElse(false);
    }

    @Unique
    public boolean woldsVaults$extractCurrency(Player player, List<InventoryUtil.ItemAccess> allItems, ItemStack price) {
        woldsVaults$getCoinDefinition(price.getItem()).ifPresent((priceCoinDefinition) -> {
            int priceValue = priceCoinDefinition.coinValue() * price.getCount();
            priceValue = woldsVaults$deductCoins(allItems, priceValue, priceCoinDefinition);
            if (priceValue > 0) {
                priceValue = this.woldsVaults$payUsingLowerDenominations(allItems, priceValue, priceCoinDefinition);
                priceValue = this.woldsVaults$payUsingHigherDenominations(allItems, priceValue, priceCoinDefinition);
            }

            if (priceValue < 0) {
                int change = -priceValue;
                returnChangeToPlayer(player, change);
            }

        });
        return true;
    }

    /**
     * @author iwolfking
     * @reason Replace with new CoinDefinition object
     */
    @Overwrite
    private static void returnChangeToPlayer(Player player, int change) {
        label21:
        while (true) {
            if (change > 0) {
                Iterator var2 = WOLD_COIN_DEFINITIONS.values().iterator();

                while (true) {
                    if (!var2.hasNext()) {
                        continue label21;
                    }

                    CoinDefinitions.CoinDefinition definition = (CoinDefinitions.CoinDefinition) var2.next();
                    System.out.println(change);
                    System.out.println(definition.coinValue());
                    if (definition.coinValue() <= change && change / definition.coinValue() < 9) {
                        ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(definition.coinItem(), change / definition.coinValue()));
                        change -= definition.coinValue() * (change / definition.coinValue());
                    }
                }
            }

            return;
        }
    }

    @Unique
    private int woldsVaults$payUsingHigherDenominations(List<InventoryUtil.ItemAccess> allItems, int priceValue, CoinDefinitions.CoinDefinition coinDefinition) {
        while (priceValue > 0 && coinDefinition.previousHigherDenomination() != null) {
            Optional<CoinDefinitions.CoinDefinition> higherCoinDefinition = woldsVaults$getCoinDefinition(coinDefinition.previousHigherDenomination());
            if (higherCoinDefinition.isPresent()) {
                coinDefinition = (CoinDefinitions.CoinDefinition) higherCoinDefinition.get();
                priceValue = this.woldsVaults$deductCoins(allItems, priceValue, coinDefinition);
            }
        }

        return priceValue;
    }

    @Unique
    private int woldsVaults$payUsingLowerDenominations(List<InventoryUtil.ItemAccess> allItems, int priceValue, CoinDefinitions.CoinDefinition coinDefinition) {
        while (priceValue > 0 && coinDefinition.nextLowerDenomination() != null) {
            Optional<CoinDefinitions.CoinDefinition> lowerCoinDefinition = woldsVaults$getCoinDefinition(coinDefinition.nextLowerDenomination());
            if (lowerCoinDefinition.isPresent()) {
                coinDefinition = (CoinDefinitions.CoinDefinition) lowerCoinDefinition.get();
                priceValue = this.woldsVaults$deductCoins(allItems, priceValue, coinDefinition);
            }
        }

        return priceValue;
    }

    @Unique
    private int woldsVaults$deductCoins(List<InventoryUtil.ItemAccess> allItems, int priceValue, CoinDefinitions.CoinDefinition coinDefinition) {
        Iterator var4 = allItems.iterator();

        while (var4.hasNext()) {
            InventoryUtil.ItemAccess itemAccess = (InventoryUtil.ItemAccess) var4.next();
            ItemStack stack = itemAccess.getStack();
            if (stack.getItem() == coinDefinition.coinItem()) {
                int countToRemove = (int) Math.ceil((double) Math.min(priceValue, stack.getCount() * coinDefinition.coinValue()) / (double) coinDefinition.coinValue());
                if (countToRemove > 0) {
                    itemAccess.setStack(ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - countToRemove));
                    priceValue -= countToRemove * coinDefinition.coinValue();
                    if (priceValue <= 0) {
                        break;
                    }
                }
            }
        }

        return priceValue;
    }

    @Unique
    private static Optional<CoinDefinitions.CoinDefinition> woldsVaults$getCoinDefinition(Item coin) {
        if (WOLD_COIN_DEFINITIONS == null) {
            WOLD_COIN_DEFINITIONS = new LinkedHashMap();
            Item vaultPalladium = ForgeRegistries.ITEMS.getValue(new ResourceLocation("the_vault:vault_palladium"));
            Item vaultIridium = ForgeRegistries.ITEMS.getValue(new ResourceLocation("the_vault:vault_iridium"));
            WOLD_COIN_DEFINITIONS.put(ModBlocks.VAULT_BRONZE, new CoinDefinitions.CoinDefinition(ModBlocks.VAULT_BRONZE, ModBlocks.VAULT_SILVER, (Item) null, 1));
            WOLD_COIN_DEFINITIONS.put(ModBlocks.VAULT_SILVER, new CoinDefinitions.CoinDefinition(ModBlocks.VAULT_SILVER, ModBlocks.VAULT_GOLD, ModBlocks.VAULT_BRONZE, 9));
            WOLD_COIN_DEFINITIONS.put(ModBlocks.VAULT_GOLD, new CoinDefinitions.CoinDefinition(ModBlocks.VAULT_GOLD, ModBlocks.VAULT_PLATINUM, ModBlocks.VAULT_SILVER, 81));
            WOLD_COIN_DEFINITIONS.put(ModBlocks.VAULT_PLATINUM, new CoinDefinitions.CoinDefinition(ModBlocks.VAULT_PLATINUM, vaultPalladium, ModBlocks.VAULT_GOLD, 729));
            WOLD_COIN_DEFINITIONS.put(vaultPalladium, new CoinDefinitions.CoinDefinition(vaultPalladium, vaultIridium, ModBlocks.VAULT_PLATINUM, 6561));
            WOLD_COIN_DEFINITIONS.put(vaultIridium, new CoinDefinitions.CoinDefinition(vaultIridium, (Item) null, vaultPalladium, 59049));
        }

        return Optional.ofNullable((CoinDefinitions.CoinDefinition) WOLD_COIN_DEFINITIONS.get(coin));
    }


    @Overwrite
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return (Boolean) pState.getValue(ACTIVE) ? new ShopPedestalBlockTile(pPos, pState) : null;
    }
}
