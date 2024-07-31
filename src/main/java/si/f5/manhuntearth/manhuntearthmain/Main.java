package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main(Plugin plugin) {
        HunterTeam hunterTeam = new HunterTeam();
        RunnerTeam runnerTeam = new RunnerTeam();
        SpectatorRole spectatorRole = new SpectatorRole();
        GamePlayersList gamePlayersList = new GamePlayersList();
        Bukkit.getServer().getPluginManager().registerEvents(new AddNewPlayerToPlayersList(gamePlayersList),plugin);
    }
}
