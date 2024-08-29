package xyz.iwolfking.woldsvaults.mixins.vaulthunters.fixes.performance;

import iskallia.vault.config.entry.DescriptionData;
import iskallia.vault.quest.base.Quest;
import iskallia.vault.quest.type.CollectionQuest;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = CollectionQuest.class, remap = false)
public abstract class MixinCollectionQuest extends Quest {

    protected MixinCollectionQuest(String type, String id, String name, DescriptionData descriptionData, ResourceLocation icon, ResourceLocation targetId, float targetProgress, String unlockedBy, QuestReward reward) {
        super(type, id, name, descriptionData, icon, targetId, targetProgress, unlockedBy, reward);
    }

    /**
     * @author iwolfking
     * @reason Check less often
     */
    @Overwrite
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        Player var3 = event.player;
        if (var3 instanceof ServerPlayer player) {
            if (event.phase == TickEvent.Phase.START) {
                if (player.tickCount % 200 == 0) {
                    this.queryCollection(player);
                }

            }
        }
    }

    @Shadow public abstract void queryCollection(ServerPlayer player);
}
