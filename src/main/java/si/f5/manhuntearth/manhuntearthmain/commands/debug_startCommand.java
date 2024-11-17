package si.f5.manhuntearth.manhuntearthmain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import si.f5.manhuntearth.manhuntearthmain.Main;

public class debug_startCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("debug_start"))) return false;
        switch(args.length) {
            case 1:
                Main.AllPlayersIntoHunterTeamFlag();
            case 0:
                Main.StartFlag();
                return true;
            default:
                return false;
        }
    }
}
