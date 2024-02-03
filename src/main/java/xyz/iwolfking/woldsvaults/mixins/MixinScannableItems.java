package xyz.iwolfking.woldsvaults.mixins;

import li.cil.scannable.common.item.Items;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.scannable.scanning.VaultOresBlockScannerModule;

@Mixin(Items.class)
public class MixinScannableItems {
    @Shadow @Final private static DeferredRegister<Item> ITEMS;
    private static final RegistryObject<Item> VAULT_ORES_MODULE = ITEMS.register("vault_ores_module", () -> ScannerModuleItemAccessor.createScannerModuleItem(VaultOresBlockScannerModule.INSTANCE));
}
