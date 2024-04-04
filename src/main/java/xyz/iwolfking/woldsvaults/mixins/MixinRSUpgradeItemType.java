//package xyz.iwolfking.woldsvaults.mixins;
//
//import com.refinedmods.refinedstorage.item.UpgradeItem;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.gen.Invoker;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Arrays;
//
//@Mixin(targets = "com/refinedmods/refinedstorage/item/UpgradeItem$Type", remap = false)
//public abstract class MixinRSUpgradeItemType {
//    @Shadow
//    @Final
//    @Mutable
//    private static UpgradeItem.Type[] $VALUES;
//
//
//    @Invoker("<init>")
//    UpgradeItem.Type create(String name) {
//        return null;
//    }
//
//    @Inject(method = "<clinit>", at = @At("TAIL"))
//    private static void classInit(CallbackInfo cb)
//    {
//        var entry = create("FORTUNE_4", 8);
//
//        $VALUES = Arrays.copyOf($VALUES, $VALUES.length + 1);
//        $VALUES[$VALUES.length-1] = entry;
//    }
//
//
//}