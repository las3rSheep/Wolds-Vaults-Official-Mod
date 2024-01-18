package xyz.iwolfking.woldsvaults.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import iskallia.vault.config.gear.VaultGearTierConfig;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(value = VaultGearTierConfig.class, remap = false)
public class MixinVaultGearToolTier {

    //Add new gear to gear config
    @Inject(method = "registerConfigs",  at = @At("TAIL"))
    private static void registerConfigs(CallbackInfoReturnable<Map<Item, VaultGearTierConfig>> cir, @Local LocalRef<List<Item>> gearItems, @Local LocalRef<Map<Item, VaultGearTierConfig>> gearConfig) {
        List<Item> newGearItems = Arrays.asList(xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF, xyz.iwolfking.woldsvaults.init.ModItems.TRIDENT);

        Iterator var2 = newGearItems.iterator();

        while(var2.hasNext()) {
            Item item = (Item) var2.next();
            gearConfig.get().put(item, (VaultGearTierConfig) (new VaultGearTierConfig(item).readConfig()));
        }


    }

//    @Overwrite
//    public static Map<Item, VaultGearTierConfig> registerConfigs() {
//        Map<Item, VaultGearTierConfig> gearConfig = new HashMap();
//        List<Item> gearItems = Arrays.asList(ModItems.HELMET, ModItems.CHESTPLATE, ModItems.LEGGINGS, ModItems.BOOTS, ModItems.SWORD, ModItems.AXE, ModItems.SHIELD, ModItems.IDOL_BENEVOLENT, ModItems.IDOL_MALEVOLENCE, ModItems.IDOL_OMNISCIENT, ModItems.IDOL_TIMEKEEPER, ModItems.JEWEL, ModItems.MAGNET, ModItems.WAND, ModItems.FOCUS, xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF);
//        Iterator var2 = gearItems.iterator();
//
//        while (var2.hasNext()) {
//            Item item = (Item) var2.next();
//            gearConfig.put(item, (VaultGearTierConfig) (new VaultGearTierConfig(item)).readConfig());
//        }
//
//        return gearConfig;
//    }
}
