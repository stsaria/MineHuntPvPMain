package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_gamestateCommand;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_resetCommand;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_startCommand;
import si.f5.manhuntearth.manhuntearthmain.commands.debug_stopCommand;
import si.f5.manhuntearth.manhuntearthmain.items.CarvedPumpkin;
import si.f5.manhuntearth.manhuntearthmain.items.StartButton;
import si.f5.manhuntearth.manhuntearthmain.roles.HunterTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.Role;
import si.f5.manhuntearth.manhuntearthmain.roles.RunnerTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.SpectatorRole;

import java.util.Objects;
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
    private int hunterWaitingTime;
    private static GameState gameState;
    StartButton startButton;
    BossBarTimer bossBarTimer;
    VictoryJudge victoryJudge;
    private static final AtomicBoolean startFlag=new AtomicBoolean(false);
    private static final AtomicBoolean stopFlag=new AtomicBoolean(false);
    private static final AtomicBoolean resetFlag=new AtomicBoolean(false);
    private static final AtomicInteger customTimeLimit=new AtomicInteger(0);
    private static final AtomicBoolean allPlayersIntoHunterTeamFlag =new AtomicBoolean(false);
    public static final int SECOND=20;
    public static final int MINUTES=60*SECOND;
    private static final int DEFAULT_TIME_LIMIT =30*MINUTES;
    private static final int HUNTER_WAITING_TIME_LIMIT = 30*SECOND;

    public Main(JavaPlugin plugin) {
        this.plugin=plugin;
        Role.RemoveAllTeams();
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        gameState=GameState.BEFORE_THE_GAME;
        victoryJudge = new VictoryJudge(gamePlayersList,hunterTeam,runnerTeam,spectatorRole);
        Bukkit.getServer().getPluginManager().registerEvents(victoryJudge,this.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(new AutoShutdowner(),this.plugin);
        Objects.requireNonNull(plugin.getCommand("debug_start")).setExecutor(new debug_startCommand());
        Objects.requireNonNull(plugin.getCommand("debug_stop")).setExecutor(new debug_stopCommand());
        Objects.requireNonNull(plugin.getCommand("debug_reset")).setExecutor(new debug_resetCommand());
        Objects.requireNonNull(plugin.getCommand("debug_gamestate")).setExecutor(new debug_gamestateCommand(gameState));
        startButton= new StartButton();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayersListUpdater(gamePlayersList),this.plugin);
        Bukkit.getServer().getPluginManager().registerEvents(startButton,this.plugin);
        runTaskTimer(plugin,0,0);
    }
    public static GameState GetGameState() {
        return gameState;
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
    public static void AllPlayersIntoHunterTeamFlag(){
        allPlayersIntoHunterTeamFlag.set(true);
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
        else if(gameState==GameState.IN_HUNTER_WAITING_TIME) {
            InHunterWaitingTime();
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
        gameState=GameState.IN_HUNTER_WAITING_TIME;
        Bukkit.broadcastMessage("スタート");
        gamePlayersList.TeamDivide(hunterTeam,runnerTeam, allPlayersIntoHunterTeamFlag.get());
        allPlayersIntoHunterTeamFlag.set(false);
        gamePlayersList.ClearALlPlayers();
        gamePlayersList.ClearEffectsAllPlayers();
        gamePlayersList.SetHealthMaxAllPlayers();
        gamePlayersList.SetFoodLevelMaxAllPlayers();
        gamePlayersList.SetItemToHeadOfPlayersInTeam(hunterTeam,new CarvedPumpkin());
        hunterWaitingTime=HUNTER_WAITING_TIME_LIMIT;
        bossBarTimer = new BossBarTimer(gamePlayersList);
    }
    private void FinishHunterWaitingTime() {
        gameState=GameState.IN_THE_GAME;
        timeLimit= (customTimeLimit.get()==0) ? (DEFAULT_TIME_LIMIT) : (customTimeLimit.get());
        customTimeLimit.set(0);
        time=timeLimit;
        gamePlayersList.ClearPlayersInTeam(hunterTeam);
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
    private void InHunterWaitingTime() {
        bossBarTimer.Update(HUNTER_WAITING_TIME_LIMIT,hunterWaitingTime,false);
        hunterWaitingTime--;
        if(hunterWaitingTime<=0) {
            FinishHunterWaitingTime();
        }
    }
    private void InTheGame() {
        bossBarTimer.Update(timeLimit,time, time % 100 == 0);
        time--;
        if(time<=0) {
            OnTimeIsUp();
        }
    }
    private void AfterTheGame() {
    }
    private void OnTimeIsUp() {
        victoryJudge.onTimeIsUp();
        StopFlag();
    }
}