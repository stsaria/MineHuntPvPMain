package si.f5.manhuntearth.manhuntearthmain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import si.f5.manhuntearth.manhuntearthmain.Main;

public class debug_stopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("debug_stop"))) return false;
        if(args.length!=0) return false;
        Main.StopFlag();
        return true;
    }
}
