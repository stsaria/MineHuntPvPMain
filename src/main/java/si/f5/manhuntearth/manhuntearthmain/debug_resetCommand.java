package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class debug_resetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("debug_reset"))) return false;
        if(args.length!=0) return false;
        Main.ResetFlag();
        return true;
    }
}
