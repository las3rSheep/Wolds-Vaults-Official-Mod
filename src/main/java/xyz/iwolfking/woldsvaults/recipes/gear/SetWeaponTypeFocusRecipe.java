package xyz.iwolfking.woldsvaults.recipes.gear;

import iskallia.vault.gear.attribute.VaultGearModifier;
import iskallia.vault.gear.data.ToolGearData;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.item.crystal.recipe.AnvilContext;
import iskallia.vault.item.crystal.recipe.VanillaAnvilRecipe;
import iskallia.vault.item.tool.ToolItem;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import xyz.iwolfking.woldsvaults.config.forge.WoldsVaultsConfig;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;
import xyz.iwolfking.woldsvaults.init.ModItems;
import xyz.iwolfking.woldsvaults.items.WeaponTypeSettingItem;

public class SetWeaponTypeFocusRecipe  extends VanillaAnvilRecipe {
    @Override
    public boolean onSimpleCraft(AnvilContext context) {

        ItemStack primary = context.getInput()[0];
        ItemStack secondary = context.getInput()[1];
        if (primary.getItem() instanceof VaultGearItem gearItem && secondary.getItem() instanceof WeaponTypeSettingItem weaponTypeSettingItem) {
            ItemStack output = primary.copy();
            VaultGearData gear = VaultGearData.read(output);

            if(!gearItem.isIntendedForSlot(primary, EquipmentSlot.MAINHAND)) {
                return false;
            }

            if(!gear.isModifiable()) {
                return false;
            }

            gear.createOrReplaceAttributeValue(ModGearAttributes.WEAPON_TYPE, weaponTypeSettingItem.getModifierTagString(secondary));

            gear.write(output);
            context.setOutput(output);

            context.onTake(context.getTake().append(() -> {
                context.getInput()[0].shrink(1);
                context.getInput()[1].shrink(1);
            }));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRegisterJEI(IRecipeRegistration iRecipeRegistration) {

    }
}
