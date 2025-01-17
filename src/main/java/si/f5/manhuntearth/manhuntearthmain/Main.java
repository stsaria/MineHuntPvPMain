package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import si.f5.manhuntearth.manhuntearthmain.commands.*;
import si.f5.manhuntearth.manhuntearthmain.items.TrackerCompass;
import si.f5.manhuntearth.manhuntearthmain.roles.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;

public class Main extends BukkitRunnable{
    private final HunterTeam hunterTeam;
    private final RunnerTeam runnerTeam;
    private final SpectatorRole spectatorRole;
    private final GamePlayersList gamePlayersList;
    private final JavaPlugin plugin;
    private GameTime time;
    private static final GameTime TIME_LIMIT =new GameTime(30,0);
    private GameTime hunterWaitingTime;
    public static final GameTime HUNTER_WAITING_TIME_LIMIT = new GameTime(0,30);
    private final List<GameTime> trackerUpdateTime;
    private static GameState gameState;
    private final TrackerCompass trackerCompass;
    private BossBarTimer bossBarTimer;
    private final VictoryJudge victoryJudge;
    private final GameWorld gameWorld;
    private final Director director = new Director();
    private static final AtomicBoolean startFlag=new AtomicBoolean(true);
    private static final AtomicBoolean stopFlag=new AtomicBoolean(false);
    private static final AtomicBoolean resetFlag=new AtomicBoolean(false);
    private static final AtomicBoolean allPlayersIntoHunterTeamFlag =new AtomicBoolean(false);

    public Main(JavaPlugin plugin) {
        this.plugin=plugin;

        Role.RemoveAllTeams();
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();

        gamePlayersList = new GamePlayersList();
        gameWorld = new GameWorld(this.plugin);
        gameState=GameState.BEFORE_THE_GAME;
        victoryJudge = new VictoryJudge(gamePlayersList,hunterTeam,runnerTeam,spectatorRole);
        Bukkit.getServer().getPluginManager().registerEvents(victoryJudge,this.plugin);

        Objects.requireNonNull(plugin.getCommand("debug_start")).setExecutor(new debug_startCommand());
        Objects.requireNonNull(plugin.getCommand("debug_stop")).setExecutor(new debug_stopCommand());
        Objects.requireNonNull(plugin.getCommand("debug_reset")).setExecutor(new debug_resetCommand());
        Objects.requireNonNull(plugin.getCommand("debug_gamestate")).setExecutor(new debug_gamestateCommand(gameState));
        Objects.requireNonNull(plugin.getCommand("lobby")).setExecutor(new lobbyCommand());

        trackerCompass= new TrackerCompass();

        Bukkit.getServer().getPluginManager().registerEvents(new PlayersListUpdater(gamePlayersList),this.plugin);

        trackerUpdateTime=new ArrayList<>(Arrays.asList(
                new GameTime(30,0),
                new GameTime(25,0),
                new GameTime(20,0),
                new GameTime(17,0),
                new GameTime(14,0),
                new GameTime(11,0),
                new GameTime(9,0),
                new GameTime(7,0),
                new GameTime(5,0),
                new GameTime(4,0),
                new GameTime(3,0),
                new GameTime(2,30),
                new GameTime(2,0),
                new GameTime(1,30),
                new GameTime(1,0),
                new GameTime(0,30)
        ));

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
    private void Start(){
        startFlag.set(false);
        CompletableFuture.supplyAsync(() -> {
            try{
                int i = 0;
                while (i < 60){
                    if (!Bukkit.getOnlinePlayers().isEmpty()){
                        break;
                    }
                    Thread.sleep(1000);
                    i++;
                }
                if (i >= 60 && Bukkit.getOnlinePlayers().isEmpty()){
                    Bukkit.getServer().shutdown();
                    return false;
                }
                for (i = 0; i < 15; i++){
                    for (Player player : Bukkit.getOnlinePlayers()){
                        player.sendTitle(String.valueOf(14-i), null);
                    }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                this.plugin.getLogger().log(Level.SEVERE, e.toString());
            }
            return true;
        }).thenAcceptAsync(result -> {
            Bukkit.getScheduler().runTask(plugin, () -> {
                gameState=GameState.IN_HUNTER_WAITING_TIME;
                gamePlayersList.TeamDivide(hunterTeam,runnerTeam, allPlayersIntoHunterTeamFlag.get());
                allPlayersIntoHunterTeamFlag.set(false);

                gamePlayersList.InitializeAllPlayers();
                gameWorld.StartTheGame();
                hunterTeam.StartWaiting(plugin,HUNTER_WAITING_TIME_LIMIT);
                director.start(gameWorld.GetOverWorld(),hunterTeam,runnerTeam);
                hunterWaitingTime=HUNTER_WAITING_TIME_LIMIT;
                bossBarTimer = new BossBarTimer(gamePlayersList);
            });
        });
    }
    private void FinishHunterWaitingTime() {
        gameState=GameState.IN_THE_GAME;
        time=new GameTime(TIME_LIMIT);
        hunterTeam.ClearAllPlayersItems();
        hunterTeam.SetItemToAllPlayers(trackerCompass,0);
        hunterTeam.giveEquipment();
        director.releaseHunter(gamePlayersList,hunterTeam,runnerTeam);
    }
    private void Stop() {
        stopFlag.set(false);
        gameState=GameState.AFTER_THE_GAME;
        time= TIME_LIMIT;
        bossBarTimer.Remove();
        gamePlayersList.SetAllPlayersGameMode(GameMode.CREATIVE);
        Bukkit.broadcastMessage("終了\n\n10秒後にサーバーが停止します");
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
                this.plugin.getLogger().log(Level.SEVERE, e.toString());
            }
            for (Player player : Bukkit.getOnlinePlayers()){
                BungeeCorder.moveServer(plugin, player, "manhuntLobby");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bukkit.getServer().shutdown();
            return null;
        }).thenAcceptAsync(result -> {
            Bukkit.getScheduler().runTask(plugin, () -> {

            });
        });
    }
    private void Reset() {
        resetFlag.set(false);
        gameState=GameState.BEFORE_THE_GAME;
        Bukkit.broadcastMessage("リセット");
    }
    private void BeforeTheGame() {
        // gamePlayersList.SetItemToHostsInventory(startButton,4);
        // gamePlayersList.SetItemToAllPlayersInventory(quitButton,8);
    }
    private void InHunterWaitingTime() {
        bossBarTimer.Update(HUNTER_WAITING_TIME_LIMIT,hunterWaitingTime,Optional.of(hunterTeam.BUKKIT_TEAM_DISPLAY_NAME()+"の解放まで"));
        hunterWaitingTime = hunterWaitingTime.Decrement();
        if(hunterWaitingTime.isZero()) {
            FinishHunterWaitingTime();
        }
    }
    private void InTheGame() {
        trackerCompass.TryUpdate(hunterTeam,runnerTeam,trackerUpdateTime,time, gameWorld.GetOverWorld(), plugin);
        AlliesLocationsActionBar.update(hunterTeam, runnerTeam);
        bossBarTimer.Update(TIME_LIMIT,time, Optional.empty());
        time = time.Decrement();
        if(time.isZero()) {
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
