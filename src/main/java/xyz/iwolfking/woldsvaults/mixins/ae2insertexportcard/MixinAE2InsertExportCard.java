package xyz.iwolfking.woldsvaults.mixins.ae2insertexportcard;

import appeng.api.config.FuzzyMode;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.GenericStack;
import appeng.api.stacks.KeyCounter;
import appeng.util.ConfigInventory;
import com.simibubi.create.content.logistics.filter.FilterItem;
import com.ultramega.ae2insertexportcard.AE2InsertExportCard;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.joseph.vaultfilters.VFTests;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Restriction(
        require = {
                @Condition(type = Condition.Type.MOD, value = "ae2insertexportcard")
        }
)
@Mixin(value = AE2InsertExportCard.class, remap = false)
public class MixinAE2InsertExportCard {
    @Redirect(
            method = "lambda$tickWireless$2",
            at = @At(
                    value = "INVOKE",
                    target = "Lappeng/api/stacks/AEKey;fuzzyEquals(Lappeng/api/stacks/AEKey;Lappeng/api/config/FuzzyMode;)Z"
            )
    )
    private static boolean redirectAEKeyFuzzy(AEKey instance, AEKey other, FuzzyMode fuzzyMode) {
        if (other instanceof AEItemKey filter && instance instanceof AEItemKey instanceKey) {
            if (filter.getItem() instanceof FilterItem) {
                return VFTests.checkFilter(instanceKey.toStack(), filter.toStack(), true, null);
            }
        }

        return instance.fuzzyEquals(other, fuzzyMode);
    }

    @Redirect(
            method = "lambda$tickWireless$2",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Object;equals(Ljava/lang/Object;)Z"
            )
    )
    private static boolean redirectAEKeyEquals(Object instance, Object o) {
        if (o instanceof AEItemKey filter && instance instanceof AEItemKey instanceKey) {
            if (filter.getItem() instanceof FilterItem) {
                return VFTests.checkFilter(instanceKey.toStack(), filter.toStack(), true, null);
            }
        }

        return instance.equals(o);
    }

    @Redirect(
            method = "tickWireless*",
            at = @At(
                    value = "INVOKE",
                    target = "Lappeng/api/stacks/GenericStack;what()Lappeng/api/stacks/AEKey;"
            )
    )
    private static AEKey redirectWhat(GenericStack stack,
                                      net.minecraft.world.item.ItemStack wirelessStack,
                                      appeng.api.networking.IGrid grid,
                                      appeng.helpers.WirelessTerminalMenuHost host,
                                      net.minecraft.server.level.ServerPlayer player,
                                      java.util.function.Supplier<?> jobGetter,
                                      java.util.function.Consumer<?> jobSetter) {

        AEKey key = stack.what();

        if (key instanceof AEItemKey item && item.getItem() instanceof FilterItem) {
            AEKey found = woldsVaults$findMatchingKey(item, grid);
            return found != null ? found : key;
        }

        return key;
    }

    @Redirect(method = "tickWireless", at = @At(value = "INVOKE", target = "Lappeng/api/stacks/KeyCounter;findFuzzy(Lappeng/api/stacks/AEKey;Lappeng/api/config/FuzzyMode;)Ljava/util/Collection;", ordinal = 0))
    private static Collection<Object2LongMap.Entry<AEKey>> seeOutputOfThisTwo(KeyCounter instance, AEKey key, FuzzyMode fuzzy) {
        return StreamSupport.stream(instance.spliterator(), false).toList();
    }

    @Unique
    private static AEKey woldsVaults$findMatchingKey(AEItemKey filterKey, appeng.api.networking.IGrid grid) {
        if (grid == null || grid.getStorageService() == null) {
            return null;
        }

        var cached = grid.getStorageService().getCachedInventory();

        for (var entry : cached) {
            AEKey key = entry.getKey();

            if (!(key instanceof AEItemKey itemKey)) {
                continue;
            }

            if (VFTests.checkFilter(itemKey, filterKey.toStack(), true, null)) {
                return itemKey;
            }
        }

        return null;
    }
}
