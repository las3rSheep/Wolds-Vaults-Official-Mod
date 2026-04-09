package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;


import iskallia.vault.block.entity.challenge.ChallengeControllerBlockEntity;
import net.minecraft.world.level.block.Rotation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


@Mixin(value = ChallengeControllerBlockEntity.class, remap = false)
public class MixinChallengeControllerBlockEntity {

    /**
     * @author iwolfking
     * @reason why
     */
    @Overwrite
    protected Rotation getRotation() {
        return Rotation.NONE;
    }
}
