//package xyz.iwolfking.woldsvaults.mixins.vaulthunters.objectives;
//
//import iskallia.vault.core.data.key.registry.SupplierRegistry;
//import iskallia.vault.core.vault.VaultRegistry;
//import iskallia.vault.core.vault.objective.Objective;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import xyz.iwolfking.woldsvaults.api.registry.CustomVaultObjectiveRegistry;
//import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultObjectiveEntry;
//
//@Mixin(value = VaultRegistry.class, remap = false)
//public abstract class MixinVaultRegistry {
//    @Shadow @Final public static SupplierRegistry<Objective> OBJECTIVE;
////    private static final SupplierRegistry objectives = new SupplierRegistry()
////            /* 64 */     .add(BailObjective.KEY)
////    /* 65 */     .add(DeathObjective.KEY)
////    /* 66 */     .add(ObeliskObjective.KEY)
////    /* 67 */     .add(KillBossObjective.KEY)
////    /* 68 */     .add(VictoryObjective.KEY)
////    /* 69 */     .add(ScavengerObjective.KEY)
////    /* 70 */     .add(CakeObjective.KEY)
////    /* 71 */     .add(AwardCrateObjective.KEY)
////    /* 72 */     .add(FindExitObjective.KEY)
////    /* 73 */     .add(TrackSpeedrunObjective.KEY)
////    /* 74 */     .add(MonolithObjective.KEY)
////    /* 75 */     .add(ElixirObjective.KEY)
////    /* 76 */     .add(LodestoneObjective.KEY)
////    /* 77 */     .add(CrakePedestalObjective.KEY)
////    /* 78 */     .add(ParadoxObjective.KEY)
////    /* 79 */     .add(HeraldObjective.KEY)
////    /* 80 */     .add(AscensionObjective.KEY)
////                 .add(ArchitectObjective.KEY)
////                 .add(HauntedBraziersObjective.E_KEY)
////                 .add(UnhingedScavengerObjective.E_KEY)
////                 .add(EnchantedElixirObjective.E_KEY)
////                 .add(BrutalBossesObjective.E_KEY);
//
//    static {
//        for(CustomVaultObjectiveEntry entry : CustomVaultObjectiveRegistry.getCustomVaultObjectiveEntries()) {
//            OBJECTIVE.add(entry.key());
//        }
//    }
//
//
//
//}
