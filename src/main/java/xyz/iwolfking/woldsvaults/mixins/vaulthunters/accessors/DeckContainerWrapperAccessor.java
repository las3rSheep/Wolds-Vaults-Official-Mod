package xyz.iwolfking.woldsvaults.mixins.vaulthunters.accessors;

import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "iskallia.vault.container.CardBinderContainer$DeckContainerWrapper", remap = false)
public interface DeckContainerWrapperAccessor {
    @Accessor("delegate")
    Container getDelegate();
}
