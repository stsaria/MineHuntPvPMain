package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class PlayersListUpdater implements Listener {
    List<GamePlayer> playersList;
    public PlayersListUpdater(GamePlayersList gamePlayersList) {
        this.playersList=gamePlayersList.playersList;
    }
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e) {
        if(playersList==null) {
            return;
        }
        GamePlayer gamePlayer = new GamePlayer(e.getPlayer());
        if(this.playersList.contains(gamePlayer)){
            return;
        }
        this.playersList.add(gamePlayer);
    }
    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent e) {
        if(playersList==null) {
            return;
        }
        this.playersList.remove(new GamePlayer(e.getPlayer()));
    }
}
