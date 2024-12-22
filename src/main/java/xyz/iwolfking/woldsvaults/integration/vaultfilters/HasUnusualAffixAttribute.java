package xyz.iwolfking.woldsvaults.integration.vaultfilters;

import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import net.joseph.vaultfilters.attributes.abstracts.BooleanAttribute;
import net.minecraft.world.item.ItemStack;

import java.util.Iterator;

public class HasUnusualAffixAttribute extends BooleanAttribute {
    public HasUnusualAffixAttribute(Boolean value) {
        super(true);
    }

    public Boolean getValue(ItemStack itemStack) {
        if (!(itemStack.getItem() instanceof VaultGearItem)) {
            return null;
        } else {
            VaultGearData data = VaultGearData.read(itemStack);
            Iterator var3 = data.getAllModifierAffixes().iterator();

            VaultGearModifier modifier;
            do {
                if (!var3.hasNext()) {
                    return false;
                }

                modifier = (VaultGearModifier)var3.next();
            } while(!modifier.hasCategory(VaultGearModifier.AffixCategory.valueOf("UNUSUAL")));

            return true;
        }
    }

    public String getTranslationKey() {
        return "has_unusual";
    }
}
