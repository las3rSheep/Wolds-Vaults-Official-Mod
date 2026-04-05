package xyz.iwolfking.woldsvaults.mixins.vaulthunters.custom;

import iskallia.vault.init.ModConfigs;
import iskallia.vault.research.ResearchTree;
import iskallia.vault.research.type.Research;
import iskallia.vault.world.data.PlayerResearchesData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerResearchesData.class, remap = false)
public abstract class MixinPlayerResearchesData {
    @Shadow
    public abstract ResearchTree getResearches(Player player);

    @Shadow
    public abstract PlayerResearchesData research(ServerPlayer player, Research research, boolean sendMessage);

    @Inject(method = "sync", at = @At("HEAD"))
    private void unlockModBoxTinkeringOnSync(ServerPlayer player, CallbackInfo ci) {
        ResearchTree researchTree = getResearches(player);
        if(!researchTree.isResearched("Mod Box Tinkering")) {
            if(researchTree.getResearchesDone().size() >= 10) {
                if(ModConfigs.RESEARCHES.getByName("Mod Box Tinkering") != null) {
                    research(player, ModConfigs.RESEARCHES.getByName("Mod Box Tinkering"), true);
                }
            }
        }
    }
}
