package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.command.Command;
import iskallia.vault.command.give.GiveCommand;
import iskallia.vault.config.UniqueGearConfig;
import iskallia.vault.core.util.WeightedList;
import iskallia.vault.init.ModConfigs;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.iwolfking.vhapi.api.util.ResourceLocUtils;
import xyz.iwolfking.woldsvaults.WoldsVaults;

import java.util.Map;
import java.util.Optional;

@Mixin(value = GiveCommand.class, remap = false)
public abstract class MixinUniqueGearCommand extends Command {
    @Inject(method = "findPoolForUniqueItem", at = @At("HEAD"), cancellable = true)
    private void fixUniqueNameHandling(ResourceLocation uniqueId, CallbackInfoReturnable<ResourceLocation> cir) {
        Map<ResourceLocation, WeightedList<ResourceLocation>> pools = ModConfigs.UNIQUE_GEAR.getPools();
        ResourceLocation dedicatedPool = WoldsVaults.id(uniqueId.getPath());
        if (pools.containsKey(dedicatedPool)) {
            cir.setReturnValue(dedicatedPool);
        }
    }

    @Redirect(method = "giveUniqueItemByName", at = @At(value = "INVOKE", target = "Liskallia/vault/config/UniqueGearConfig;getEntry(Lnet/minecraft/resources/ResourceLocation;)Ljava/util/Optional;"))
    private Optional<UniqueGearConfig.Entry> testForWoldsUnique(UniqueGearConfig instance, ResourceLocation id) {
        if(ModConfigs.UNIQUE_GEAR.getEntry(id).isEmpty()) {
            ResourceLocation woldsId = ResourceLocUtils.swapNamespace(id, "woldsvaults");
            if(ModConfigs.UNIQUE_GEAR.getEntry(woldsId).isPresent()) {
                return ModConfigs.UNIQUE_GEAR.getEntry(woldsId);
            }
        }

        return ModConfigs.UNIQUE_GEAR.getEntry(id);
    }
}
