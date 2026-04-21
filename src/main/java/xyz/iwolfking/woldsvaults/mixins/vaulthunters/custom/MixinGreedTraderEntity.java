package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.block.GreedyAnchorBlock;
import iskallia.vault.block.entity.GreedyAnchorTileEntity;
import iskallia.vault.entity.entity.GreedTraderEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import software.bernie.geckolib3.core.IAnimatable;
import xyz.iwolfking.woldsvaults.WoldsVaults;

@Mixin(value = GreedTraderEntity.class, remap = false)
public abstract class MixinGreedTraderEntity extends PathfinderMob implements IAnimatable {
    @Shadow
    private BlockPos anchorPos;

    protected MixinGreedTraderEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Inject(method = "tick", at = @At(value = "TAIL"), remap = true)
    private void restrictMrGreedy(CallbackInfo ci) {
        if(!this.level.isClientSide() && this.anchorPos != null) {
            if(level.getGameTime() % 20 == 0) {
                if(this.level.hasNeighborSignal(anchorPos)) {
                    this.restrictTo(anchorPos, 0);
                }
                else {
                    this.restrictTo(anchorPos, 16);
                }
            }
        }
    }
}
