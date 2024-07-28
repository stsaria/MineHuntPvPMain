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
    @Override
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null) return false;
        if(!(obj instanceof GamePlayer)) return false;
        GamePlayer gamePlayer = (GamePlayer) obj;
        return this.bukkitPlayer.getUniqueId().equals(gamePlayer.bukkitPlayer.getUniqueId());
    }

}
