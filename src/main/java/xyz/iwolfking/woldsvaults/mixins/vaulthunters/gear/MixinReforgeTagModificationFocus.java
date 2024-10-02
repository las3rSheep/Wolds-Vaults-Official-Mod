//package xyz.iwolfking.woldsvaults.mixins.vaulthunters.gear;
//
//import iskallia.vault.item.modification.ReforgeTagModificationFocus;
//import net.minecraft.world.item.Item;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import xyz.iwolfking.woldsvaults.api.registry.CustomVaultGearRegistry;
//import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultGearEntry;
//
//import java.util.Map;
//
//@Mixin(value = ReforgeTagModificationFocus.class, remap = false)
//public class MixinReforgeTagModificationFocus {
//    @Shadow @Final private static Map<Item, String> ITEM_TO_NAME;
//
//    static {
//        for(CustomVaultGearEntry entry : CustomVaultGearRegistry.getCustomGearEntries()) {
//            ITEM_TO_NAME.put(entry.registryItem(), entry.name());
//        }
//    }
//}
