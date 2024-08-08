package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    GameState gameState;
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    GamePlayersList gamePlayersList;
    Timer timer;
    TeamDivider teamDivider;

    public Main(Plugin plugin) {
        gameState=GameState.NOT_STARTED;
        hunterTeam = new HunterTeam();
        runnerTeam = new RunnerTeam();
        spectatorRole = new SpectatorRole();
        gamePlayersList = new GamePlayersList();
        Bukkit.getServer().getPluginManager().registerEvents(new AddNewPlayerToPlayersList(gamePlayersList),plugin);
        teamDivider = new TeamDivider(
            gameState,
            gamePlayersList,
            new ArrayList<Role>(){
                {
                    add(hunterTeam);
                    add(runnerTeam);
                    add(spectatorRole);
                }
            }
        );

    }
}
