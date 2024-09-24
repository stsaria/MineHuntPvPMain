package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends BukkitRunnable{
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    GamePlayersList gamePlayersList;
    Timer timer;
    Plugin plugin;
    private int time;
    GameState gameState;

    public Main(Plugin plugin) {
        this.plugin=plugin;
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayersListUpdater(gamePlayersList),this.plugin);
        gamePlayersList.Refresh();
        gameState=GameState.BEFORE_THE_GAME;
        time=0;
        runTaskTimer(plugin,0,0);
    }

    @Override
    public void run() {
        if(time%100==0) {
            Bukkit.broadcastMessage(gamePlayersList.toString());
        }
        time++;
    }
}