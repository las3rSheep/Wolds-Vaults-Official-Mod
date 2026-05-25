package xyz.iwolfking.woldsvaults.api.util;

import iskallia.vault.gear.attribute.VaultGearAttribute;
import iskallia.vault.gear.data.GearDataCache;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class WoldEtchingHelper {
    public static <T> boolean hasEtching(LivingEntity entity, VaultGearAttribute<T> attribute) {
        for(EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (!stack.isEmpty() && VaultGearData.hasData(stack)) {
                if(stack.getItem() instanceof VaultGearItem gear) {
                    if(!gear.isIntendedForSlot(stack, slot)) {
                        return false;
                    }
                }

                GearDataCache cache = GearDataCache.of(stack);
                if(cache.hasAttribute(attribute)) {
                    return true;
                }
            }
        }

        return false;
    }
}
