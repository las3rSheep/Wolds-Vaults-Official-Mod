package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom.etching;

import iskallia.vault.gear.data.AttributeGearData;
import iskallia.vault.gear.data.VaultGearData;
import iskallia.vault.skill.talent.type.PuristTalent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.woldsvaults.api.util.WoldEtchingHelper;
import xyz.iwolfking.woldsvaults.init.ModEtchingGearAttributes;

import java.util.Set;

@Mixin(value = PuristTalent.class, remap = false)
public abstract class MixinPuristTalent {
    @Shadow
    public abstract Set<EquipmentSlot> getSlots();

    @Inject(method = "getCount", at = @At("HEAD"), cancellable = true)
    private void puristEtching(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
        if(WoldEtchingHelper.hasEtching(entity, ModEtchingGearAttributes.PURIST_COMMON)) {
            int count = 0;

            for(EquipmentSlot slot : this.getSlots()) {
                ItemStack stack = entity.getItemBySlot(slot);
                if (VaultGearData.hasData(stack)) {
                    AttributeGearData data = AttributeGearData.read(stack);
                    if (data instanceof VaultGearData gearData) {
                        if (gearData.getRarity().ordinal() <= 1) {
                            ++count;
                        }
                    }
                }
            }

            cir.setReturnValue(count);
        }
    }
}
