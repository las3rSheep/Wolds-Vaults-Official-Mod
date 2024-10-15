package xyz.iwolfking.woldsvaults.mixins.infernalmobs;


import atomicstryker.infernalmobs.common.InfernalMobsCore;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "infernalmobs")
        }
)
@Mixin(value = InfernalMobsCore.class, remap = false)
public abstract class MixinInfernalMobsCore {


    /**
     * @author iwolfking
     * @reason Disable Infernal Mob spawns
     */
    @Overwrite
    public void processEntitySpawn(LivingEntity entity) {
        return;
    }
}
