package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes;

import iskallia.vault.task.Task;
import iskallia.vault.task.TaskContext;
import iskallia.vault.world.data.DeckRecipeTaskData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import xyz.iwolfking.woldsvaults.WoldsVaults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(value = DeckRecipeTaskData.PlayerEntry.class, remap = false)
public abstract class MixinDeckRecipeTaskDataPlayerEntry {
    @Final
    @Shadow
    private Map<ResourceLocation, Task> activeTasks;

    @Shadow
    abstract void attachTask(ResourceLocation recipeId, Task task, ServerPlayer player);

    @Shadow
    private boolean progressDirty;

    @Shadow
    @Final
    private Map<ResourceLocation, TaskContext> contexts;
    @Shadow
    @Final
    private Map<ResourceLocation, String> taskIds;

    /**
     * @author iwolfking
     * @reason Fix ConcurrentModificationException by iterating over a snapshot
     */
    @Overwrite
    public void attachTo(ServerPlayer player) {
        for (Map.Entry<ResourceLocation, Task> entry : new HashMap<>(this.activeTasks).entrySet()) {
            this.attachTask(entry.getKey(), entry.getValue(), player);
        }
    }
}
