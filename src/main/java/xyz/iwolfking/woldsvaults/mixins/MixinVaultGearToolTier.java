package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.config.gear.VaultGearTierConfig;
import iskallia.vault.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Mixin(value = VaultGearTierConfig.class, remap = false)
public class MixinVaultGearToolTier {
    @Shadow @Final public static ResourceLocation UNIQUE_ITEM;

    /**
     * @author iwolfking
     * @reason Add Battlestaff and Trident
     *
     */
    @Overwrite
    public static Map<ResourceLocation, VaultGearTierConfig> registerConfigs() {
        Map<ResourceLocation, VaultGearTierConfig> gearConfig = new HashMap();
        Stream.of(ModItems.HELMET, ModItems.CHESTPLATE, ModItems.LEGGINGS, ModItems.BOOTS, ModItems.SWORD, ModItems.AXE, ModItems.SHIELD, ModItems.IDOL_BENEVOLENT, ModItems.IDOL_MALEVOLENCE, ModItems.IDOL_OMNISCIENT, ModItems.IDOL_TIMEKEEPER, ModItems.JEWEL, ModItems.MAGNET, ModItems.WAND, ModItems.FOCUS, xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF, xyz.iwolfking.woldsvaults.init.ModItems.TRIDENT).map((rec$) -> {
            return ((ForgeRegistryEntry) rec$).getRegistryName();
        }).forEach((key) -> {
            gearConfig.put(key, (VaultGearTierConfig) (new VaultGearTierConfig(key)).readConfig());
        });
        gearConfig.put(UNIQUE_ITEM, (VaultGearTierConfig) (new VaultGearTierConfig(UNIQUE_ITEM)).readConfig());
        return gearConfig;
    }

    //Add new gear to gear config
//    @Inject(method = "registerConfigs",  at = @At("TAIL"))
//    private static void registerConfigs(CallbackInfoReturnable<Map<Item, VaultGearTierConfig>> cir, @Local LocalRef<Map<Item, VaultGearTierConfig>> gearConfig) {
//        List<Item> newGearItems = Arrays.asList(xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF, xyz.iwolfking.woldsvaults.init.ModItems.TRIDENT);
//
//        Iterator var2 = newGearItems.iterator();
//
//        while(var2.hasNext()) {
//            Item item = (Item) var2.next();
//            gearConfig.get().put(item, (VaultGearTierConfig) (new VaultGearTierConfig(item.getRegistryName()).readConfig()));
//            System.out.println(gearConfig.get());
//        }
//
//
//    }

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
