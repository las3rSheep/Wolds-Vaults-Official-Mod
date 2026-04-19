package xyz.iwolfking.woldsvaults.mixins.vaulthunters.datagen;

import iskallia.vault.config.gear.VaultGearEnchantmentConfig;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.iwolfking.woldsvaults.datagen.DatagenTester;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Condition(type = Condition.Type.TESTER, tester = DatagenTester.class)
@Mixin(value = VaultGearEnchantmentConfig.class, remap = false)
public class MixinVaultGearEnchantmentConfig {
    @Redirect(method = "<init>", at = @At(value = "NEW", target = "()Ljava/util/HashMap;"))
    private HashMap useLHM() {return new LinkedHashMap<>();}
}
