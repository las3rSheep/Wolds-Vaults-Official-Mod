package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.item.render.CatalystItemRenderer;
import iskallia.vault.item.render.core.SpecialItemRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = CatalystItemRenderer.class, remap = false)
public class MixinCatalystItemRenderer extends SpecialItemRenderer {

}
