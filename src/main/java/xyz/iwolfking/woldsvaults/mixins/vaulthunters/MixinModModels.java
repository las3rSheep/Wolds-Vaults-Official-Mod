package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import iskallia.vault.init.ModModels;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.level.ItemLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;

@Mixin(value = ModModels.class)
public class MixinModModels {


    @Redirect(method = "registerItemColors", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/color/item/ItemColors;register(Lnet/minecraft/client/color/item/ItemColor;[Lnet/minecraft/world/level/ItemLike;)V", ordinal = 0))
    private static void redirectCall(ItemColors instance, ItemColor itemColor, ItemLike[] p_92690_) {
        instance.register(itemColor, CustomVaultGearRegistry.getItemLikes());
    }


//    /**
//     * @author iwolfking
//     * @reason Add Battlestaff and Tridents to color registry
//     */
//    @Overwrite
//    public static void registerItemColors(ItemColors colors) {
//        colors.register((stack, tintLayer) -> {
//            if (tintLayer > 0) {
//                GearDataCache clientCache = GearDataCache.of(stack);
//                if (clientCache.getState() == VaultGearState.UNIDENTIFIED) {
//                    Optional var10000 = Optional.ofNullable(clientCache.getGearRollType());
//                    VaultGearTypeConfig var10001 = ModConfigs.VAULT_GEAR_TYPE_CONFIG;
//                    Objects.requireNonNull(var10001);
//                    VaultGearTypeConfig.RollType type = (VaultGearTypeConfig.RollType)var10000.flatMap(x -> var10001.getRollPool(x.toString())).orElse((Object)null);
//                    if (type != null) {
//                        return ColorUtil.blendColors(type.getColor(), -1, 0.8F);
//                    }
//                }
//
//                return -1;
//            } else {
//                return VaultGearHelper.getGearColor(stack);
//            }
//        }, CustomVaultGearRegistry.getItemLikes());
//        colors.register((stack, tintIndex) -> {
//            return tintIndex == 0 ? JewelItem.getColor(stack) : -1;
//        }, new ItemLike[]{ModItems.JEWEL});
//        colors.register((stack, tintIndex) -> {
//            return tintIndex == 1 ? AugmentItem.getColor(stack) : -1;
//        }, new ItemLike[]{ModItems.AUGMENT});
//        colors.register((stack, tintIndex) -> {
//            if (tintIndex == 1) {
//                return BottleItem.getColor(stack);
//            } else {
//                return tintIndex == 2 ? BottleItem.getEffectColor(stack) : -1;
//            }
//        }, new ItemLike[]{ModItems.BOTTLE});
//    }
}
