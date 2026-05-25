package xyz.iwolfking.woldsvaults.api.util;

import iskallia.vault.skill.base.Skill;
import iskallia.vault.skill.prestige.core.PrestigePower;
import iskallia.vault.world.data.PlayerPrestigePowersData;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.List;
import java.util.UUID;

public class ServerPrestigePowerHelper {
    public static <T extends PrestigePower> List<T> getPrestigePowersOfType(UUID playerId, Class<T> powerType) {
        return PlayerPrestigePowersData.get(ServerLifecycleHooks.getCurrentServer()).getPowers(playerId).getAll(powerType, Skill::isUnlocked);
    }
}
