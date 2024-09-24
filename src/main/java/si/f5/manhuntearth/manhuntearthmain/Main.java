package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main {
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    GamePlayersList gamePlayersList;
    Timer timer;
    Plugin plugin;

    public Main(Plugin plugin) {
        this.plugin=plugin;
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayersListUpdater(gamePlayersList),this.plugin);
        gamePlayersList.Refresh();
        Sequence();
    }
    public void Sequence() {
        new Sequence(gamePlayersList).runTaskTimer(plugin,0,0);
    }
}
class Sequence extends BukkitRunnable {
    private int time;
    GameState gameState;
    GamePlayersList gamePlayersList;
    public Sequence(GamePlayersList gamePlayersList) {
        this.gameState=GameState.BEFORE_THE_GAME;
        this.gamePlayersList=gamePlayersList;
        time=0;
    }
    @Override
    public void run() {
        if(time%100==0) {
            Bukkit.broadcastMessage(gamePlayersList.toString());
        }
        time++;
    }
}
