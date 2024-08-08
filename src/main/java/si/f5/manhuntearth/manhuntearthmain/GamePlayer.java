package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
    public void SetItem(ItemStack itemStack,int slot) {
        if(bukkitPlayer.isOnline()) {
            Player player = (Player) bukkitPlayer;
            player.getInventory().setItem(slot,itemStack);
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
