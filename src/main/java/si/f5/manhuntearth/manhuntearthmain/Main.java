package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main {
    GameState gameState;
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    GamePlayersList gamePlayersList;
    Timer timer;
    Plugin plugin;

    public Main(Plugin plugin) {
        this.plugin=plugin;
        gameState=GameState.BEFORE_THE_GAME;
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        Bukkit.getServer().getPluginManager().registerEvents(new AddNewPlayerToPlayersList(gamePlayersList),this.plugin);
        BeforeTheGame();
    }
    public void BeforeTheGame() {
        gameState=GameState.BEFORE_THE_GAME;
        new BeforeTheGame().runTaskTimer(plugin,0,0);
    }
    public void OnTheStart() {
        gameState=GameState.IN_THE_GAME;
        InTheGame();
    }
    public void InTheGame() {
        gamePlayersList.TeamDivide(hunterTeam,runnerTeam);
    }
    public void AfterTheGame() {
        gameState=GameState.AFTER_THE_GAME;
    }
}
class BeforeTheGame extends BukkitRunnable {
    private int time;
    public BeforeTheGame() {
        time=0;
    }
    @Override
    public void run() {
        Bukkit.broadcastMessage(String.valueOf(time));
        time++;
    }
}
