package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_gamestateCommand;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_resetCommand;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_startCommand;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_stopCommand;
import si.f5.manhuntearth.manhuntearthmain.items.StartButton;
import si.f5.manhuntearth.manhuntearthmain.roles.HunterTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.Role;
import si.f5.manhuntearth.manhuntearthmain.roles.RunnerTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.SpectatorRole;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends BukkitRunnable{
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    GamePlayersList gamePlayersList;
    JavaPlugin plugin;
    private int time;
    private int timeLimit;
    GameState gameState;
    StartButton startButton;
    BossBarTimer bossBarTimer;
    private static final AtomicBoolean startFlag=new AtomicBoolean(false);
    private static final AtomicBoolean stopFlag=new AtomicBoolean(false);
    private static final AtomicBoolean resetFlag=new AtomicBoolean(false);
    private static final AtomicInteger customTimeLimit=new AtomicInteger(0);
    public static final int SECOND=20;
    public static final int MINUTES=60*SECOND;
    private static final int DEFAULT_TIME_LIMIT =3*MINUTES;

    public Main(JavaPlugin plugin) {
        this.plugin=plugin;
        Role.RemoveAllTeams();
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        plugin.getCommand("debug_start").setExecutor(new debug_startCommand());
        plugin.getCommand("debug_stop").setExecutor(new debug_stopCommand());
        plugin.getCommand("debug_reset").setExecutor(new debug_resetCommand());
        plugin.getCommand("debug_gamestate").setExecutor(new debug_gamestateCommand(gameState));
        startButton= new StartButton();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayersListUpdater(gamePlayersList),this.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(startButton,this.plugin);
        gameState=GameState.BEFORE_THE_GAME;
        runTaskTimer(plugin,0,0);
    }
    public static void StartFlag() {
        startFlag.set(true);
    }
    public static void StopFlag() {
        stopFlag.set(true);
    }
    public static void ResetFlag() {
        resetFlag.set(true);
    }
    public static void CustomTimeLimit(int timeLimit) {
        customTimeLimit.set(timeLimit);
    }

    @Override
    public void run() {
        if(startFlag.get()) {
            Start();
        }
        else if(stopFlag.get()) {
            Stop();
        }
        else if (resetFlag.get()) {
            Reset();
        }
        if(gameState==GameState.BEFORE_THE_GAME) {
            BeforeTheGame();
        }
        else if(gameState==GameState.IN_THE_GAME) {
            InTheGame();
        }
        else if(gameState==GameState.AFTER_THE_GAME) {
            AfterTheGame();
        }
    }
    private void Start() {
        startFlag.set(false);
        gameState=GameState.IN_THE_GAME;
        timeLimit= (customTimeLimit.get()==0) ? (DEFAULT_TIME_LIMIT) : (customTimeLimit.get());
        time=timeLimit;
        Bukkit.broadcastMessage("スタート");
        gamePlayersList.TeamDivide(hunterTeam,runnerTeam);
        gamePlayersList.ClearALl();
        bossBarTimer = new BossBarTimer(gamePlayersList);
    }
    private void Stop() {
        stopFlag.set(false);
        gameState=GameState.AFTER_THE_GAME;
        timeLimit=DEFAULT_TIME_LIMIT;
        time= timeLimit;
        bossBarTimer.Remove();
        Bukkit.broadcastMessage("終了");
    }
    private void Reset() {
        resetFlag.set(false);
        gameState=GameState.BEFORE_THE_GAME;
        Bukkit.broadcastMessage("リセット");
    }
    private void BeforeTheGame() {
        gamePlayersList.SetItemToHostsInventory(startButton,4);
    }
    private void InTheGame() {
        bossBarTimer.Update(timeLimit,time, time % 100 == 0);
        time--;
        if(time<=0) {
            StopFlag();
        }
    }
    private void AfterTheGame() {
    }
}