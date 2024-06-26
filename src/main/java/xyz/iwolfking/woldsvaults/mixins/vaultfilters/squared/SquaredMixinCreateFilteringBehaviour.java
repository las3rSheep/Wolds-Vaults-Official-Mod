package xyz.iwolfking.woldsvaults.mixins.vaultfilters.squared;

import com.simibubi.create.content.logistics.filter.FilterItemStack;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import net.joseph.vaultfilters.VaultFilters;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

@Mixin(value = FilteringBehaviour.class, remap = false)
public abstract class SquaredMixinCreateFilteringBehaviour extends BlockEntityBehaviour {
    @Shadow private FilterItemStack filter;
    @Shadow private Supplier<Boolean> isActive;

    public SquaredMixinCreateFilteringBehaviour(SmartBlockEntity be) {
        super(be);
    }

    @Inject(method = "test(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    public void checkFilter(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(isActive.get() || this.filter.isEmpty() || VaultFilters.checkFilter(stack, this.filter.item(),true,this.blockEntity.getLevel()));
    }

}
