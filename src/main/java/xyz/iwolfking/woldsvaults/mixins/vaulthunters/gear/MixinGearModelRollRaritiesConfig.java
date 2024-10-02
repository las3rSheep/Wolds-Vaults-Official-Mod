//package xyz.iwolfking.woldsvaults.mixins.vaulthunters.gear;
//
//import com.google.gson.annotations.Expose;
//import com.llamalad7.mixinextras.sugar.Local;
//import com.llamalad7.mixinextras.sugar.ref.LocalRef;
//import iskallia.vault.config.Config;
//import iskallia.vault.config.GearModelRollRaritiesConfig;
//import iskallia.vault.gear.VaultGearRarity;
//import net.minecraft.world.item.ItemStack;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;
//import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultGearEntry;
//import xyz.iwolfking.woldsvaults.items.gear.VaultBattleStaffItem;
//import xyz.iwolfking.woldsvaults.items.gear.VaultTridentItem;
//
//import java.util.List;
//import java.util.Map;
//
//@Mixin(value = GearModelRollRaritiesConfig.class, remap = false)
//public abstract class MixinGearModelRollRaritiesConfig extends Config {
//
//    @Expose
//    private static Map<VaultGearRarity, List<String>> BATTLESTAFF_MODEL_ROLLS;
//
//    @Expose
//    private static Map<VaultGearRarity, List<String>> TRIDENT_MODEL_ROLLS;
//
//
//
//
//    @Inject(method = "getRolls", at = @At("HEAD"), cancellable = true)
//    private void getRollsHook(CallbackInfoReturnable<Map<VaultGearRarity, List<String>>> cir, @Local LocalRef<ItemStack> stack) {
//        for(CustomVaultGearEntry entry : CustomVaultGearRegistry.getCustomGearEntries()) {
//
//        }
//        if (stack instanceof VaultBattleStaffItem)
//            /*  49 */       cir.setReturnValue(BATTLESTAFF_MODEL_ROLLS);
//
//        if(stack instanceof VaultTridentItem) {
//                    cir.setReturnValue(TRIDENT_MODEL_ROLLS);
//            }
//    }
//
//
//}
