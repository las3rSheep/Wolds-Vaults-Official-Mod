package xyz.iwolfking.woldsvaults.mixins.vaulthunters;

import iskallia.vault.block.render.VaultChestRenderer;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(value = VaultChestRenderer.class, remap = false)
public interface VaultChestRendererAccessor<T extends ChestBlockEntity> {
    @Accessor("NORMAL_MODEL_MAP")
    public static Map getMapOfModels() {
        throw new AssertionError();
    }


}
