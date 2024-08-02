package xyz.iwolfking.woldsvaults.mixins.vaulthunters.objectives;


import iskallia.vault.bounty.task.properties.CompletionProperties;
import iskallia.vault.bounty.task.properties.TaskProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.iwolfking.woldsvaults.api.registry.CustomVaultObjectiveRegistry;
import xyz.iwolfking.woldsvaults.api.registry.record.CustomVaultObjectiveEntry;

import java.util.List;

@Mixin(value = CompletionProperties.class, remap = false)
public class MixinCompletionProperties extends TaskProperties {
    @Shadow private String id;

    protected MixinCompletionProperties(ResourceLocation taskType, List<ResourceLocation> validDimensions, boolean vaultOnly, double amount) {
        super(taskType, validDimensions, vaultOnly, amount);
    }


    @Inject(method = "deserializeNBT(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"))
    private void addCustomVaultObjectives(CompoundTag tag, CallbackInfo ci) {
        for(CustomVaultObjectiveEntry entry : CustomVaultObjectiveRegistry.getCustomVaultObjectiveEntries()) {
            if(this.id.equals("the_vault:" + entry.id())) {
                this.id = entry.id();
            }
        }
    }

//    /**
//     * @author iwolfking
//     * @reason Add new objectives to list of completion bounties.
//     */
//    @Overwrite
//    public void deserializeNBT(CompoundTag tag) {
//        super.deserializeNBT(tag);
//        this.id = tag.getString("id");
//        String var10001;
//        switch (this.id) {
//            case "the_vault:vault":
//                var10001 = "vault";
//                break;
//            case "the_vault:boss":
//                var10001 = "boss";
//                break;
//            case "the_vault:cake":
//                var10001 = "cake";
//                break;
//            case "the_vault:scavenger":
//                var10001 = "scavenger";
//                break;
//            case "the_vault:unhinged_scavenger":
//                var10001 = "unhinged_scavenger";
//                break;
//            case "the_vault:enchanted_elixir":
//                var10001 = "enchanted_elixir";
//                break;
//            case "the_vault:haunted_braziers":
//                var10001 = "haunted_braziers";
//                break;
//            case "the_vault:brutal_bosses":
//                var10001 = "brutal_bosses";
//                break;
//            case "the_vault:monolith":
//                var10001 = "monolith";
//                break;
//            default:
//                var10001 = this.id;
//        }
//
//        this.id = var10001;
//    }
}
