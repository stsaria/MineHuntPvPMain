package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Main {
    GameState gameState;
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    GamePlayersList gamePlayersList;
    Timer timer;
    TeamDivider teamDivider;

    public Main(Plugin plugin) {
        gameState=GameState.BEFORE_THE_GAME;
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        Bukkit.getServer().getPluginManager().registerEvents(new AddNewPlayerToPlayersList(gamePlayersList),plugin);
        teamDivider = new TeamDivider(
            gamePlayersList,
            new ArrayList<Role>(){
                {
                    add(hunterTeam);
                    add(runnerTeam);
                }
            }
        );
        BeforeTheGame();
        InTheGame();
        AfterTheGame();
    }
    public void BeforeTheGame() {
        gameState=GameState.BEFORE_THE_GAME;
    }
    public void InTheGame() {
        gameState=GameState.IN_THE_GAME;
    }
    public void AfterTheGame() {
        gameState=GameState.AFTER_THE_GAME;
    }
}
