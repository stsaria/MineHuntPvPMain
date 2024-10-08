package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import si.f5.manhuntearth.manhuntearthmain.roles.GameTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.HunterTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.RunnerTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.SpectatorRole;

public class VictoryJudge implements Listener {
    GamePlayersList gamePlayersList;
    HunterTeam hunterTeam;
    RunnerTeam runnerTeam;
    SpectatorRole spectatorRole;
    public VictoryJudge (GamePlayersList gamePlayersList, HunterTeam hunterTeam, RunnerTeam runnerTeam, SpectatorRole spectatorRole) {
        this.gamePlayersList=gamePlayersList;
        this.hunterTeam=hunterTeam;
        this.runnerTeam=runnerTeam;
        this.spectatorRole=spectatorRole;
    }
    private void GameOver(GameTeam winningTeam) {
        Main.StopFlag();
        gamePlayersList.playersList.forEach(p-> spectatorRole.AddPlayer(p));
        Bukkit.broadcastMessage(winningTeam.BUKKIT_TEAM_COLOR()+winningTeam.BUKKIT_TEAM_DISPLAY_NAME()+
                "の勝利!");
    }
    @EventHandler
    public void onRunnerEntersPortal(PlayerPortalEvent e) {
        e.setCancelled(true);
        if(runnerTeam.HasPlayer(e.getPlayer())){
            GameOver(runnerTeam);
        }
    }
    @EventHandler
    public void onPlayerBelongsToEitherTeamQuit(PlayerQuitEvent e) {
        onDecreaseInPlayers(new GamePlayer(e.getPlayer()));
    }
    @EventHandler
    public void onPlayerBelongsToEitherTeamDie(PlayerDeathEvent e) {
        onDecreaseInPlayers(new GamePlayer(e.getEntity()));
    }
    public void onTimeIsUp() {
        GameOver(hunterTeam);
    }
    private void onDecreaseInPlayers(GamePlayer gamePlayer) {
        spectatorRole.AddPlayer(gamePlayer);
        if(hunterTeam.Size()==0) {
            GameOver(runnerTeam);
        }
        else if(runnerTeam.Size()==0) {
            GameOver(hunterTeam);
        }
    }
}
