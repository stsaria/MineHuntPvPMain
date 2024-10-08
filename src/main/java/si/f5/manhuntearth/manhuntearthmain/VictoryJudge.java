package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VictoryJudge implements Listener {
    GamePlayersList gamePlayersList;
    public VictoryJudge (GamePlayersList gamePlayersList) {
        this.gamePlayersList=gamePlayersList;
    }
    private void GameOver(GameTeam winningTeam) {
        Main.StopFlag();
        Bukkit.broadcastMessage(winningTeam.BUKKIT_TEAM_COLOR()+winningTeam.BUKKIT_TEAM_DISPLAY_NAME()+
                "の勝利!");
    }
    @EventHandler
    public void onRunnerEntersPortal(PlayerPortalEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlayerBelongsToEitherTeamQuit(PlayerQuitEvent e) {

    }
    @EventHandler
    public void onPlayerBelongsToEitherTeamDie(PlayerDeathEvent e) {

    }
    public void onTimeIsUp() {

    }
}
