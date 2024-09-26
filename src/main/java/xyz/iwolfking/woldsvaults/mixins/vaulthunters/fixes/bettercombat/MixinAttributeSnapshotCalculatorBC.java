package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes.bettercombat;

import com.llamalad7.mixinextras.sugar.Local;
import iskallia.vault.gear.item.VaultGearItem;
import iskallia.vault.snapshot.AttributeSnapshot;
import iskallia.vault.snapshot.AttributeSnapshotCalculator;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "bettercombat")
        }
)
@Mixin(value = AttributeSnapshotCalculator.class, remap = false)
public abstract class MixinAttributeSnapshotCalculatorBC {




    /**
     * @author iwolfking
     * @reason Remove offhand stats when holding battlestaff, for compat when no Better Combat is installed.
     */
    @Inject(method = "computeGearSnapshot", at = @At(value = "INVOKE", target = "Ljava/util/function/Function;apply(Ljava/lang/Object;)Ljava/lang/Object;"))
    private static void computeGearSnapshot(Function<EquipmentSlot, ItemStack> equipmentFn, Predicate<Item> isItemOnCooldown, int playerLevel, AttributeSnapshot snapshot, CallbackInfo ci, @Local EquipmentSlot slot, @Local List<ItemStack> gear) {
        if(slot.equals(EquipmentSlot.OFFHAND)) {
            ItemStack mainhandStack = equipmentFn.apply(EquipmentSlot.MAINHAND);
            ItemStack offhandStack = equipmentFn.apply(EquipmentSlot.OFFHAND);
            if(offhandStack.getItem() instanceof VaultGearItem offhandGear && mainhandStack.getItem() instanceof VaultGearItem mainGear) {
                if(offhandGear.isIntendedForSlot(offhandStack, EquipmentSlot.MAINHAND) && mainGear.isIntendedForSlot(mainhandStack, EquipmentSlot.MAINHAND)) {
                    gear.add(offhandStack);
                }
            }
        }
    }

}
