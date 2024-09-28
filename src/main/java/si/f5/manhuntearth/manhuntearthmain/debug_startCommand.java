package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class debug_startCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("debug_start"))) return false;
        if(args.length==0) {
            Main.StartFlag();
            return true;
        }
        if(args.length==1) {
            int customTimeLimit;
            try {
                customTimeLimit=Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                commandSender.sendMessage("数値を入力してください");
                return false;
            }
            Main.CustomTimeLimit(customTimeLimit);
            Main.StartFlag();
            return true;
        }
        return false;
    }
}
