package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayersListUpdater implements Listener {
    final GamePlayersList gamePlayersList;
    public PlayersListUpdater(GamePlayersList gamePlayersList) {
        this.gamePlayersList=gamePlayersList;
    }
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        gamePlayersList.AddPlayer(e.getPlayer());
    }
    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent e) {
        gamePlayersList.RemovePlayer(e.getPlayer());
    }
    public void Refresh() {
        Bukkit.getOnlinePlayers().forEach(gamePlayersList::AddPlayer);
    }
}
