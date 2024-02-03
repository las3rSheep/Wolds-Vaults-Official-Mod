package xyz.iwolfking.woldsvaults.mixins;

import li.cil.scannable.api.scanning.ScannerModule;
import li.cil.scannable.common.item.ScannerModuleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ScannerModuleItem.class)
public interface ScannerModuleItemAccessor {
    @Invoker("<init>")
    static ScannerModuleItem createScannerModuleItem(ScannerModule module) {
        throw new UnsupportedOperationException();
    }
}
