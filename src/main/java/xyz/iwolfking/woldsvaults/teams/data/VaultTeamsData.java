package xyz.iwolfking.woldsvaults.teams.data;

import iskallia.vault.util.nbt.NBTHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;
import xyz.iwolfking.woldsvaults.teams.lib.VaultTeam;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class VaultTeamsData extends SavedData {

    protected static final String DATA_NAME = "the_vault_VaultTeamsData";
    protected Map<VaultTeam, Set<UUID>> playerTeamData = new HashMap<>();


    private VaultTeamsData() {
    }

    private VaultTeamsData(CompoundTag tag) {
        this.load(tag);
    }

    @Nonnull
    public CompoundTag save(@Nonnull CompoundTag tag)  {
        ListTag teams = new ListTag();
        for(VaultTeam team : playerTeamData.keySet()) {
            CompoundTag teamTag = new CompoundTag();
            teamTag.putString("team", team.name());
            NBTHelper.writeCollection(teamTag, "players", playerTeamData.get(team), StringTag.class, key ->  StringTag.valueOf(key.toString()));
            teams.add(teamTag);
        }
        tag.put("teams", teams);
        return tag;
    }


    public void load(CompoundTag tag) {
        this.playerTeamData.clear();
        ListTag teamData = tag.getList("teams", 10);

        for (int i = 0; i < teamData.size(); i++) {
            CompoundTag teamTag = teamData.getCompound(i);
            VaultTeam teamName = VaultTeam.valueOf(teamTag.getString("team"));
            Set<UUID> players = NBTHelper.readSet(teamTag, "players", StringTag.class, strTag -> UUID.fromString(strTag.getAsString()));
            this.playerTeamData.put(teamName, players);
        }
    }

    public  void addTeamMember(Player player, VaultTeam team) {
        if(this.playerTeamData.containsKey(team)) {
            playerTeamData.get(team).add(player.getUUID());
        }
        else {
            playerTeamData.put(team, Set.of(player.getUUID()));
        }
    }

    public static VaultTeamsData get(ServerLevel level) {
        return get(level.getServer());
    }

    public static VaultTeamsData get(MinecraftServer server) {
        return (VaultTeamsData)server.overworld()
                .getDataStorage()
                .computeIfAbsent(VaultTeamsData::new, VaultTeamsData::new, DATA_NAME);
    }
}
