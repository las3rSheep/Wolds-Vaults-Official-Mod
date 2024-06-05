package xyz.iwolfking.woldsvaults.mixins;


import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import iskallia.vault.config.gear.VaultGearWorkbenchConfig;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(VaultGearWorkbenchConfig.class)
public class MixinVaultGearWorkbenchConfig {
    @Inject(method = "registerConfigs", at = @At("TAIL"), remap = false)
    private static void extendRegisterConfigs(CallbackInfoReturnable<Map<Item, VaultGearWorkbenchConfig>> cir,  @Local LocalRef<Map<Item, VaultGearWorkbenchConfig>> gearConfig) {
        List<Item> newGearItems = Arrays.asList(xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF, xyz.iwolfking.woldsvaults.init.ModItems.TRIDENT);

        Iterator var2 = newGearItems.iterator();

        while(var2.hasNext()) {
            Item item = (Item) var2.next();
            gearConfig.get().put(item, (VaultGearWorkbenchConfig) (new VaultGearWorkbenchConfig(item).readConfig()));
        }
    }
}
