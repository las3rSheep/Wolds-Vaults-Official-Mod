package xyz.iwolfking.woldsvaults.mixins.vaultfilters;

import com.simibubi.create.content.logistics.filter.FilterItemStack;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.joseph.vaultfilters.VaultFilters;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.DistExecutor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.ref.WeakReference;
@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "vaultfilters")
        }
)
@Mixin(value = VaultFilters.class, remap = false)
public abstract class MixinVaultFilters {


    @Shadow @Final public static ThreadLocal<WeakReference<Level>> LEVEL_REF;

    /**
     * @author iwolfking
     * @reason Call Create 0.5.1f method
     */
    @Overwrite
    public static boolean filterTest(ItemStack stack, ItemStack filterStack, Level level) {
        if (level == null) {
            level = (Level) DistExecutor.safeRunForDist(() -> {
                return VaultFilters::getClientLevel;
            }, () -> {
                return () -> {
                    if(LEVEL_REF.get() != null) {
                        return (Level) ((WeakReference) LEVEL_REF.get()).get();
                    }
                return null;
                };
            });
        }

        return FilterItemStack.of(filterStack).test(level, stack);
    }
}
