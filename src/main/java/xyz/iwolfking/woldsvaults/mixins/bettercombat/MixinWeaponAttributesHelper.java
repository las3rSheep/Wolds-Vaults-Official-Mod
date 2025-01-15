package xyz.iwolfking.woldsvaults.mixins.bettercombat;

import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.gear.item.VaultGearItem;
import net.bettercombat.api.AttributesContainer;
import net.bettercombat.api.WeaponAttributes;
import net.bettercombat.api.WeaponAttributesHelper;
import net.bettercombat.logic.ItemStackNBTWeaponAttributes;
import net.bettercombat.logic.WeaponRegistry;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.init.ModConfigs;
import xyz.iwolfking.woldsvaults.init.ModGearAttributes;

import java.io.Reader;
import java.io.StringReader;

@Mixin(value = WeaponAttributesHelper.class, remap = false)
public abstract class MixinWeaponAttributesHelper {
    @Shadow
    public static AttributesContainer decode(Reader reader) {
        return null;
    }


    @Inject(method = "readFromNBT", at = @At("HEAD"), cancellable = true)
    private static void setWeaponTypeFromModifier(ItemStack itemStack, CallbackInfoReturnable<WeaponAttributes> cir) {
        ItemStackNBTWeaponAttributes attributedItemStack = (ItemStackNBTWeaponAttributes)((Object) itemStack);

        WeaponAttributes cachedAttributes = attributedItemStack.getWeaponAttributes();
            if (cachedAttributes != null) {
                cir.setReturnValue(cachedAttributes);
                return;
            }

            if(itemStack.getItem() instanceof VaultGearItem) {
                VaultGearData data = VaultGearData.read(itemStack);
                String weaponTypeKey = data.getFirstValue(ModGearAttributes.WEAPON_TYPE).orElse(null);
                if(weaponTypeKey != null) {
                    ResourceLocation itemId = Registry.ITEM.getKey(itemStack.getItem());

                    try {
                        String attributeData = ModConfigs.WEAPON_TYPES.WEAPON_TYPES_MAP.get(weaponTypeKey).NBT;
                        StringReader json = new StringReader(attributeData);
                        AttributesContainer container = decode((Reader) json);
                        WeaponAttributes attributes = WeaponRegistry.resolveAttributes(itemId, container);
                        if (attributes == null) {
                            attributedItemStack.setInvalidAttributes(true);
                        }

                        attributedItemStack.setWeaponAttributes(attributes);
                        cir.setReturnValue(attributes);
                    } catch (Exception e) {
                        System.err.println("Failed to resolve weapon attributes from ItemStack of item: " + itemId);
                        System.err.println(e.getMessage());
                        attributedItemStack.setInvalidAttributes(true);
                    }
                }

            }
    }
}
