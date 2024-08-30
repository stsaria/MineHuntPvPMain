package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class AddNewPlayerToPlayersList implements Listener {
    List<GamePlayer> playersList;
    public AddNewPlayerToPlayersList(GamePlayersList gamePlayersList) {
        this.playersList=gamePlayersList.playersList;
    }
    @EventHandler
    public void OnPlayerJoined(PlayerJoinEvent e) {
        if(playersList==null) {
            return;
        }
        GamePlayer gamePlayer = new GamePlayer(e.getPlayer());
        if(this.playersList.contains(gamePlayer)){
            return;
        }
        this.playersList.add(gamePlayer);
        Bukkit.broadcastMessage(playersList.toString());
    }
}
