package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import iskallia.vault.core.world.loot.generator.TieredLootTableGenerator;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = TieredLootTableGenerator.class, remap = false)
public class MixinTieredLootTableGenerator {

    @ModifyExpressionValue(
            method = "generate",
            at = @At(value = "FIELD", target = "Liskallia/vault/core/world/loot/generator/TieredLootTableGenerator;itemQuantity:F", opcode = Opcodes.GETFIELD)
    )
    private float alterItemQuantityScaling(float originalValue) {
        if (originalValue <= 1) return originalValue;
        return 1.1f * (float) Math.log(originalValue + 1.0f);
    }

}