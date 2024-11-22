package si.f5.manhuntearth.manhuntearthmain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class lobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("lobby"))) return false;
        if(args.length!=0) return false;
        if(!(commandSender instanceof Player)) return false;
        Player player = (Player) commandSender;
        player.kickPlayer("退出しました。");
        return true;
    }
}
