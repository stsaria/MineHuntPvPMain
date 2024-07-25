package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class GamePlayer {
    OfflinePlayer bukkitPlayer;
    public GamePlayer(OfflinePlayer bukkitPlayer) {
        this.bukkitPlayer=bukkitPlayer;
    }
    public void SendMessage(String message) {
        if(bukkitPlayer.isOnline()) {
            Player player = (Player) bukkitPlayer;
            player.sendMessage(message);
        }
    }
}
