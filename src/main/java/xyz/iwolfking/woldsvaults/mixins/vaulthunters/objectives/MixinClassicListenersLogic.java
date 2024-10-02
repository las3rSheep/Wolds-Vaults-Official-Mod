//package xyz.iwolfking.woldsvaults.mixins.vaulthunters.objectives;
//
//import iskallia.vault.core.vault.player.ClassicListenersLogic;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//import xyz.iwolfking.woldsvaults.api.registry.CustomVaultObjectiveRegistry;
//import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultObjectiveEntry;
//
//@Mixin(value = ClassicListenersLogic.class, remap = false)
//public class MixinClassicListenersLogic  {
//
//    @Inject(method = "getVaultObjective", at = @At("HEAD"), cancellable = true)
//    private void addCustomObjectiveNames(String key, CallbackInfoReturnable<String> cir) {
//        for(CustomVaultObjectiveEntry entry : CustomVaultObjectiveRegistry.getCustomVaultObjectiveEntries()) {
//            if(entry.id().equals(key)) {
//                cir.setReturnValue(entry.name());
//            }
//        }
//    }
//    ///**
////     * @author iwolfking
////     * @reason Add Unhinged Scavenger as special case
////     */
////    @Overwrite
////    public String getVaultObjective(String key) {
////        String var10000;
////        switch (key == null ? "" : key.toLowerCase()) {
////            case "boss":
////                var10000 = "Hunt the Guardians";
////                break;
////            case "unhinged_scavenger":
////                var10000 = "Unhinged Scavenger Hunt";
////                break;
////            case "scavenger":
////                var10000 = "Scavenger Hunt";
////                break;
////            case "haunted_braziers":
////                var10000 = "Haunted Brazier";
////                break;
////            case "enchanted_elixir":
////                var10000 = "Enchanted Elixir";
////                break;
////            case "brutal_bosses":
////                var10000 = "Brutal Bosses";
////                break;
////            case "monolith":
////                var10000 = "Brazier";
////                break;
////            case "empty":
////                var10000 = "Architect";
////                break;
////            case "":
////                var10000 = "";
////                break;
////            default:
////                var10000 = key.substring(0, 1).toUpperCase() + key.substring(1);
////        }
////
////        return var10000;
////    }
//}
