package xyz.iwolfking.woldsvaults.teams.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class VaultTeamsData extends SavedData {

    protected static final String DATA_NAME = "the_vault_VaultTeamsPointsData";
    protected Map<String, Integer> playerTeamData = new HashMap<>();


    private VaultTeamsData() {
    }

    private VaultTeamsData(CompoundTag tag) {
        this.load(tag);
    }

    public void load(CompoundTag tag) {
        this.playerTeamData.clear();
        ListTag teamsData = tag.getList("teams", 10);

        for (int i = 0; i < teamsData.size(); i++) {
            CompoundTag teamTag = teamsData.getCompound(i);
            String teamName = teamTag.getString("team");
            Integer points = teamTag.getInt("points");
            this.playerTeamData.put(teamName, points);
        }
    }

    @Nonnull
    public CompoundTag save(@Nonnull CompoundTag tag) {
        ListTag teamPoints = new ListTag();
        this.playerTeamData.forEach((teamName, points) -> {
            CompoundTag teamTag = new CompoundTag();
            teamTag.putString("team", teamName);
            teamTag.putInt("points", points);
            teamPoints.add(teamTag);
        });
        tag.put("teams", teamPoints);
        return tag;
    }


    public static VaultTeamsData get(ServerLevel level) {
        return get(level.getServer());
    }

    public static VaultTeamsData get(MinecraftServer server) {
        return server.overworld()
                .getDataStorage()
                .computeIfAbsent(VaultTeamsData::new, VaultTeamsData::new, "the_vault_VaultTeamsPointsData");
    }

    public void addPointsToTeam(String teamName, int points) {
        if(this.playerTeamData.containsKey(teamName)) {
            this.playerTeamData.put(teamName, this.playerTeamData.get(teamName) + points);
        }
        else {
            this.playerTeamData.put(teamName, points);
        }
        this.setDirty(true);
    }

    public void removePointsFromTeam(String teamName, int points) {
        if(this.playerTeamData.containsKey(teamName)) {
            this.playerTeamData.put(teamName, this.playerTeamData.get(teamName) - points);
        }
        this.setDirty(true);
    }


    public void setPointsForTeam(String teamName, int points) {
        this.playerTeamData.put(teamName, points);
        this.setDirty(true);
    }

    public int getPointsForTeam(String teamName) {
       return this.playerTeamData.getOrDefault(teamName, 0);
    }
}
