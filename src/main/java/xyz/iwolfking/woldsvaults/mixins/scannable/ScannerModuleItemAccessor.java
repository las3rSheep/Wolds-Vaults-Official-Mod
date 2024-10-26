package xyz.iwolfking.woldsvaults.mixins.scannable;

import li.cil.scannable.api.scanning.ScannerModule;
import li.cil.scannable.common.item.ScannerModuleItem;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "scannable")
        }
)
@Mixin(ScannerModuleItem.class)
public interface ScannerModuleItemAccessor {
    @Invoker("<init>")
    static ScannerModuleItem createScannerModuleItem(ScannerModule module) {
        throw new UnsupportedOperationException();
    }
}
