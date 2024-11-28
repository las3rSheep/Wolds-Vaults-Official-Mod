package xyz.iwolfking.woldsvaults.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import xyz.iwolfking.woldsvaults.teams.data.VaultTeamsData;

public class VaultTeamCommand {

    public VaultTeamCommand() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("team_admin")
                .then(Commands.literal("add")
                        .then(Commands.argument("target", StringArgumentType.string())
                                .then(((Commands.argument("amount", IntegerArgumentType.integer())
                                        .executes((p_137341_) -> {
                                            addPoints(p_137341_.getSource(), StringArgumentType.getString(p_137341_, "target"), IntegerArgumentType.getInteger(p_137341_, "amount"));
                                            return 0;
                                        }))))))
                .then(Commands.literal("set").then(Commands.argument("target", StringArgumentType.string())
                        .then((Commands.argument("amount", IntegerArgumentType.integer())
                                .executes((p_137341_) -> {
                                    setPoints(p_137341_.getSource(), StringArgumentType.getString(p_137341_, "target"), IntegerArgumentType.getInteger(p_137341_, "amount"));
                                    return 0;
                                })))))
                .then(Commands.literal("remove").then(Commands.argument("target", StringArgumentType.string())
                        .then((Commands.argument("amount", IntegerArgumentType.integer())
                                .executes((p_137341_) -> {
                                    removePoints(p_137341_.getSource(), StringArgumentType.getString(p_137341_, "target"), IntegerArgumentType.getInteger(p_137341_, "amount"));
                                    return 0;
                                })))))
                .then(Commands.literal("get").then(Commands.argument("target", StringArgumentType.string())
                                .executes((p_137341_) -> {
                                    getPoints(p_137341_.getSource(), StringArgumentType.getString(p_137341_, "target"));
                                    return 0;
                                }))));
    }


    private static void addPoints(CommandSourceStack context, String team, int points) {
        VaultTeamsData.get(context.getServer()).addPointsToTeam(team, points);
    }

    private static void setPoints(CommandSourceStack context, String team, int points) {
        VaultTeamsData.get(context.getServer()).setPointsForTeam(team, points);
    }

    private static void removePoints(CommandSourceStack context, String team, int points) {
        VaultTeamsData.get(context.getServer()).removePointsFromTeam(team, points);
    }

    private static void getPoints(CommandSourceStack context, String team) throws CommandSyntaxException {
        int points = VaultTeamsData.get(context.getServer()).getPointsForTeam(team);
        context.getPlayerOrException().displayClientMessage(new TextComponent(team + " has " + points + " points."), false);
    }
}
