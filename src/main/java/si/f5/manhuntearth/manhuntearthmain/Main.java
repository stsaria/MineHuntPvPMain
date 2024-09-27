package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main extends BukkitRunnable{
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    GamePlayersList gamePlayersList;
    Timer timer;
    Plugin plugin;
    private int time;
    GameState gameState;
    StartButton startButton;
    private static AtomicBoolean startFlag=new AtomicBoolean(false);

    public Main(Plugin plugin) {
        this.plugin=plugin;
        Role.RemoveAllTeams();
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        startButton= new StartButton();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayersListUpdater(gamePlayersList),this.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(startButton,this.plugin);
        gameState=GameState.BEFORE_THE_GAME;
        time=0;
        runTaskTimer(plugin,0,0);
    }
    public static void StartFlag() {
        startFlag.set(true);
    }

    @Override
    public void run() {
        gamePlayersList.SetItemToAllPlayersInventory(startButton,4);
        if(startFlag.get()) {
            startFlag.set(false);
            Bukkit.broadcastMessage("スタート");
            gamePlayersList.TeamDivide(hunterTeam,runnerTeam);
        }
        time++;
    }
}