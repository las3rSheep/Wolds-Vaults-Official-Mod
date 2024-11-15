package xyz.iwolfking.woldsvaults.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import xyz.iwolfking.woldsvaults.teams.data.VaultTeamsData;
import xyz.iwolfking.woldsvaults.teams.lib.VaultTeam;
import xyz.iwolfking.woldsvaults.util.MessageFunctions;

public class VaultTeamCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("vault_team").then(Commands.argument("command", StringArgumentType.string()).then(Commands.argument("team", StringArgumentType.string()).then(Commands.argument("target", EntityArgument.player()).executes(AddCommand -> openResearchGUI(AddCommand.getSource(),
                StringArgumentType.getString(AddCommand, "command"),
                StringArgumentType.getString(AddCommand, "team"),
                EntityArgument.getEntity(AddCommand, "player")
        ))))));
    }

    private static int openResearchGUI(CommandSourceStack ctx, String command, String team, Entity target) throws CommandSyntaxException {
        if(command.equals("add")) {
            try {
                VaultTeam godTeam = VaultTeam.valueOf(team);
                if (target instanceof Player player) {
                    VaultTeamsData.get(player.getServer()).addTeamMember(player, godTeam);
                    MessageFunctions.sendMessage(ctx.getPlayerOrException(), new TextComponent("Added player to team."));
                } else {
                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {
                MessageFunctions.sendMessage(ctx.getPlayerOrException(), new TextComponent("Could not find a team/player with that name."));
                return 0;
            }
        }

        return 1;

    }
}
