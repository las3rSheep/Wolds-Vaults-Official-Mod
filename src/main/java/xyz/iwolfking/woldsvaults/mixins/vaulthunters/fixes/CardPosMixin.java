package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.core.card.CardPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = CardPos.class, remap = false)
public class CardPosMixin {

    @Shadow
    public int x;

    @Shadow
    public int y;

    /**
     * @author iwolfking
     * @reason OMFG
     */
    @Overwrite
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof CardPos pos)) return false;
        return this.x == pos.x && this.y == pos.y;
    }
}