package xyz.iwolfking.woldsvaults.events;

import net.minecraftforge.event.RegisterCommandsEvent;
import xyz.iwolfking.woldsvaults.commands.ClaimPurchaseCommand;
import xyz.iwolfking.woldsvaults.commands.PlayerResearchCommand;

public class RegisterCommandEventHandler {
    public static void woldsvaults_registerCommandsEvent(RegisterCommandsEvent event) {
        //PlayerResearchCommand.register(event.getDispatcher());
        //ClaimPurchaseCommand.register(event.getDispatcher());
    }
}