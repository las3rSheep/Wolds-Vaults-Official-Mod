package xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors;

import iskallia.vault.network.message.ModifierWorkbenchCraftMessage;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ModifierWorkbenchCraftMessage.class, remap = false)
public interface MixinModifierWorkbenchCraftMessageAccessor {

    @Accessor
    BlockPos getPos();

    @Accessor
    ResourceLocation getCraftModifierIdentifier();





}
