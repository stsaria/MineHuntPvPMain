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
            case 2:
                Main.AllPlayersIntoHunterTeamFlag();
            case 1: // Processing common to 1, and 2
                int customTimeLimit;
                try {
                    customTimeLimit=Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    commandSender.sendMessage("数値を入力してください");
                    return false;
                }
                Main.CustomTimeLimit(customTimeLimit);
            case 0: // Processing common to 0, 1, and 2
                Main.StartFlag();
                return true;
            default:
                return false;
        }
    }
}
