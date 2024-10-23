package si.f5.manhuntearth.manhuntearthmain.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import si.f5.manhuntearth.manhuntearthmain.GameState;

public class debug_gamestateCommand implements CommandExecutor {
    final GameState gameState;
    public debug_gamestateCommand(GameState gameState) {
        this.gameState=gameState;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(command.getName().equalsIgnoreCase("debug_gamestate"))) return false;
        commandSender.sendMessage(gameState.toString());
        return true;
    }
}
