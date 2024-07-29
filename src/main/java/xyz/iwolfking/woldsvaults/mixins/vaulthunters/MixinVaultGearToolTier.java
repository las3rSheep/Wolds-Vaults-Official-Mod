package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import iskallia.vault.config.gear.VaultGearTierConfig;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;

import java.util.Map;

@Mixin(value = VaultGearTierConfig.class, remap = false)
public class MixinVaultGearToolTier {
    @Shadow @Final public static ResourceLocation UNIQUE_ITEM;

//    /**
//     * @author iwolfking
//     * @reason Add Battlestaff and Trident
//     *
//     */
//    @Overwrite
//    public static Map<ResourceLocation, VaultGearTierConfig> registerConfigs() {
//        Map<ResourceLocation, VaultGearTierConfig> gearConfig = new HashMap();
//        Stream.of(ModItems.HELMET, ModItems.CHESTPLATE, ModItems.LEGGINGS, ModItems.BOOTS, ModItems.SWORD, ModItems.AXE, ModItems.SHIELD, ModItems.IDOL_BENEVOLENT, ModItems.IDOL_MALEVOLENCE, ModItems.IDOL_OMNISCIENT, ModItems.IDOL_TIMEKEEPER, ModItems.JEWEL, ModItems.MAGNET, ModItems.WAND, ModItems.FOCUS, xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF, xyz.iwolfking.woldsvaults.init.ModItems.TRIDENT).map((rec$) -> {
//            return ((ForgeRegistryEntry) rec$).getRegistryName();
//        }).forEach((key) -> {
//            gearConfig.put(key, (VaultGearTierConfig) (new VaultGearTierConfig(key)).readConfig());
//        });
//        gearConfig.put(UNIQUE_ITEM, (VaultGearTierConfig) (new VaultGearTierConfig(UNIQUE_ITEM)).readConfig());
//        return gearConfig;
//    }

    //TODO: Fix CustomVaultGearRegistry not working
    @Inject(method = "registerConfigs", at = @At("TAIL"), cancellable = true)
    private static void registerCustomGearConfigs(CallbackInfoReturnable<Map<ResourceLocation, VaultGearTierConfig>> cir) {
        Map<ResourceLocation, VaultGearTierConfig> gearTierConfigMap = cir.getReturnValue();
        CustomVaultGearRegistry.getCustomGearEntries().forEach(customVaultGearEntry -> {
            gearTierConfigMap.put(customVaultGearEntry.registryItem().getRegistryName(), (new VaultGearTierConfig(customVaultGearEntry.registryItem().getRegistryName()).readConfig()));
        });
//        gearTierConfigMap.put(xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF.getRegistryName(), (new VaultGearTierConfig(xyz.iwolfking.woldsvaults.init.ModItems.BATTLESTAFF.getRegistryName()).readConfig()));
//        gearTierConfigMap.put(xyz.iwolfking.woldsvaults.init.ModItems.TRIDENT.getRegistryName(), (new VaultGearTierConfig(xyz.iwolfking.woldsvaults.init.ModItems.TRIDENT.getRegistryName()).readConfig()));
        cir.setReturnValue(gearTierConfigMap);
    }
}
