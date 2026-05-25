package xyz.iwolfking.woldsvaults.mixins.vaultintegrations;

import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.MEStorage;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.refinedmods.refinedstorage.api.network.INetwork;
import com.refinedmods.refinedstorage.api.util.Action;
import iskallia.vaultintegrations.altar.AltarConduitNode;
import iskallia.vaultintegrations.block.entity.AltarConduitBlockEntity;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "vaultintegrations")
        }
)
@Mixin(value = AltarConduitNode.class, remap = false)
public class MixinAltarConduitNode {
    @Shadow private AltarConduitBlockEntity altarConduit;

    @WrapOperation(
            method = "extractItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/refinedmods/refinedstorage/api/network/INetwork;extractItem(Lnet/minecraft/world/item/ItemStack;IILcom/refinedmods/refinedstorage/api/util/Action;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    private ItemStack woldsvaults$checkRSAvailability(
            INetwork instance, ItemStack stack, int size, int flags, Action action, Operation<ItemStack> original
    ) {

        if(altarConduit.getLevel() != null) {
            if(!altarConduit.getLevel().hasNeighborSignal(altarConduit.getBlockPos())) {
                return original.call(instance, stack, size, flags, action);
            }
        }

        ItemStack availableStack = instance.getItemStorageCache().getList().get(stack);

        if(availableStack != null) {
            if (availableStack.getCount() < size + 1) {
                return ItemStack.EMPTY;
            }
        }

        return original.call(instance, stack, size, flags, action);
    }

    @WrapOperation(
            method = "lambda$extractItems$2",
            at = @At(
                    value = "INVOKE",
                    target = "Lappeng/api/storage/MEStorage;extract(Lappeng/api/stacks/AEKey;JLappeng/api/config/Actionable;Lappeng/api/networking/security/IActionSource;)J"
            )
    )
    private long woldsvaults$checkAEAvailability(
            MEStorage instance, AEKey what, long amount, Actionable mode, IActionSource source, Operation<Long> original
    ) {
        if(altarConduit.getLevel() != null) {
            if(!altarConduit.getLevel().hasNeighborSignal(altarConduit.getBlockPos())) {
                return original.call(instance, what, amount, mode, source);
            }
        }

        KeyCounter counter = instance.getAvailableStacks();
        long available = counter.get(what);

        if (available < amount + 1) {
            return 0;
        }

        return original.call(instance, what, amount, mode, source);
    }

}
