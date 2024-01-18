package xyz.iwolfking.woldsvaults.mixins;

import iskallia.vault.dynamodel.registry.ArmorPieceModelRegistry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "iskallia.vault.init.ModDynamicModels$Armor", remap = false)
public class MixinModDynamicModelsArmor {
    @Shadow @Final public static ArmorPieceModelRegistry PIECE_REGISTRY;

//    @Unique
//    private static final ArmorModel INTEGRATOR = PIECE_REGISTRY.registerAll(((ArmorModel)(new ArmorModel(VaultMod.id("gear/armor/integrator"), "Integrator"))
///*  626 */       .properties((new DynamicModelProperties()).allowTransmogrification().discoverOnRoll()))
///*  627 */       .usingLayers((ArmorLayers)new IntegratorLayers())
///*  628 */       .addSlot(EquipmentSlot.HEAD)
///*  629 */       .addSlot(EquipmentSlot.CHEST)
///*  630 */       .addSlot(EquipmentSlot.LEGS)
///*  631 */       .addSlot(EquipmentSlot.FEET));



}
