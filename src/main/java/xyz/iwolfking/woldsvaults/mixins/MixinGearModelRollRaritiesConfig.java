package xyz.iwolfking.woldsvaults.mixins;

import com.google.gson.annotations.Expose;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import iskallia.vault.config.Config;
import iskallia.vault.config.GearModelRollRaritiesConfig;
import iskallia.vault.dynamodel.DynamicModel;
import iskallia.vault.dynamodel.model.armor.ArmorPieceModel;
import iskallia.vault.gear.VaultGearRarity;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.init.ModDynamicModels;
import iskallia.vault.item.gear.VaultArmorItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.extensions.IForgeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.items.gear.VaultBattleStaffItem;
import xyz.iwolfking.woldsvaults.items.gear.VaultTridentItem;
import xyz.iwolfking.woldsvaults.models.Battlestaffs;
import xyz.iwolfking.woldsvaults.models.Tridents;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(value = GearModelRollRaritiesConfig.class, remap = false)
public abstract class MixinGearModelRollRaritiesConfig extends Config {

    @Shadow public abstract Map<VaultGearRarity, List<String>> getRolls(VaultGearItem gear);

    @Shadow @Nullable protected abstract VaultGearRarity getForcedTierRarity(IForgeItem item, ResourceLocation modelId);

    @Expose
    private static Map<VaultGearRarity, List<String>> BATTLESTAFF_MODEL_ROLLS;

    @Expose
    private static Map<VaultGearRarity, List<String>> TRIDENT_MODEL_ROLLS;


    @Inject(method = "reset", at = @At("HEAD"))
    private void resetHook(CallbackInfo ci) {
        BATTLESTAFF_MODEL_ROLLS = new HashMap<>();
        /* 160 */     BATTLESTAFF_MODEL_ROLLS.put(VaultGearRarity.SCRAPPY, (List<String>) Battlestaffs.REGISTRY
/* 161 */         .getIds().stream()
/* 162 */         .map(ResourceLocation::toString)
/* 163 */         .collect(Collectors.toList()));

        TRIDENT_MODEL_ROLLS = new HashMap<>();
        /* 160 */     TRIDENT_MODEL_ROLLS.put(VaultGearRarity.SCRAPPY, (List<String>) Tridents.REGISTRY
/* 161 */         .getIds().stream()
/* 162 */         .map(ResourceLocation::toString)
/* 163 */         .collect(Collectors.toList()));

    }

    @Inject(method = "getRolls", at = @At("HEAD"), cancellable = true)
    private void getRollsHook(CallbackInfoReturnable<Map<VaultGearRarity, List<String>>> cir, @Local LocalRef<VaultGearItem> gear) {
        if (gear instanceof VaultBattleStaffItem)
            /*  49 */       cir.setReturnValue(BATTLESTAFF_MODEL_ROLLS);

        if(gear instanceof VaultTridentItem) {
                    cir.setReturnValue(TRIDENT_MODEL_ROLLS);
            }
    }

    @Overwrite
    public VaultGearRarity getRarityOf(VaultGearItem gearItem, ResourceLocation modelId) {
        Map<VaultGearRarity, List<String>> rolls = this.getRolls(gearItem);
        rolls.forEach((key, value) -> System.out.println(key + ":" + value));
        if (rolls == null) {
            return VaultGearRarity.SCRAPPY;
        } else {
            VaultGearRarity predefined = this.getForcedTierRarity(gearItem, modelId);
            if (predefined != null) {
                return predefined;
            } else {
                if (gearItem instanceof VaultArmorItem) {
                    modelId = (ResourceLocation) ModDynamicModels.Armor.PIECE_REGISTRY.get(modelId).map(ArmorPieceModel::getArmorModel).map(DynamicModel::getId).orElse(modelId);
                }

                for (int i = VaultGearRarity.values().length - 1; i >= 0; --i) {
                    VaultGearRarity rarity = VaultGearRarity.values()[i];
                    List<String> modelIds = (List) rolls.get(rarity);
                    if (modelIds != null && modelIds.contains(modelId.toString())) {
                        return rarity;
                    }
                }

                return VaultGearRarity.SCRAPPY;
            }
        }
    }
}
