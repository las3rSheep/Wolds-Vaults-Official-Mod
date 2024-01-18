package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.dynamodel.registry.DynamicModelRegistries;
import iskallia.vault.init.ModDynamicModels;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.init.ModItems;
import xyz.iwolfking.woldsvaults.models.Battlestaffs;
import xyz.iwolfking.woldsvaults.models.Tridents;

@Mixin(value = ModDynamicModels.class, remap = false)
public class MixinMainModDynamicModels {
    @Shadow @Final public static DynamicModelRegistries REGISTRIES;

    @Inject(method = "initItemAssociations", at = @At("RETURN"))
    private static void initItemAssociations(CallbackInfo ci) {
        REGISTRIES.associate((Item) ModItems.BATTLESTAFF, Battlestaffs.REGISTRY);
        REGISTRIES.associate((Item) ModItems.TRIDENT, Tridents.REGISTRY);
    }
}
