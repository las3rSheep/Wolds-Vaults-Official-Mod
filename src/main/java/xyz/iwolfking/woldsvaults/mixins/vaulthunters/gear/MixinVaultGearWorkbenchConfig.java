//package xyz.iwolfking.woldsvaults.mixins.vaulthunters.gear;
//
//
//import com.llamalad7.mixinextras.sugar.Local;
//import com.llamalad7.mixinextras.sugar.ref.LocalRef;
//import iskallia.vault.config.gear.VaultGearWorkbenchConfig;
//import net.minecraft.world.item.Item;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;
//import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultGearEntry;
//
//import java.util.*;
//
//@Mixin(VaultGearWorkbenchConfig.class)
//public class MixinVaultGearWorkbenchConfig {
//    @Inject(method = "registerConfigs", at = @At("TAIL"), remap = false)
//    private static void extendRegisterConfigs(CallbackInfoReturnable<Map<Item, VaultGearWorkbenchConfig>> cir,  @Local LocalRef<Map<Item, VaultGearWorkbenchConfig>> gearConfig) {
//        List<Item> newGearItems = new ArrayList<>();
//        for(CustomVaultGearEntry entry : CustomVaultGearRegistry.getCustomGearEntries()) {
//            newGearItems.add(entry.registryItem());
//        }
//
//        Iterator var2 = newGearItems.iterator();
//
//        while(var2.hasNext()) {
//            Item item = (Item) var2.next();
//            gearConfig.get().put(item, (VaultGearWorkbenchConfig) (new VaultGearWorkbenchConfig(item).readConfig()));
//        }
//    }
//}
