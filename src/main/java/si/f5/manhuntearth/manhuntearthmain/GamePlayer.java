package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class GamePlayer {
    private OfflinePlayer bukkitPlayer;
    public GamePlayer(OfflinePlayer bukkitPlayer) {
        this.bukkitPlayer=bukkitPlayer;
    }
    public void SendMessage(String message) {
        if(getBukkitPlayer().isOnline()) {
            Player player = (Player) getBukkitPlayer();
            player.sendMessage(message);
        }
    }
    public void SetItem(GameItem item,int slot) {
        if(getBukkitPlayer().isOnline()) {
            Player player = (Player) getBukkitPlayer();
            player.getInventory().setItem(slot,item.GetItemStack());
        }
    }
    public ItemStack GetItemInMainHand() {
        if(!(getBukkitPlayer().isOnline())) throw new IllegalStateException("Couldn't get "+bukkitPlayer.getName()+"'s item in their main hand because they are offline");
        Player player = (Player) getBukkitPlayer();
        return player.getInventory().getItemInMainHand();
    }
    public String GetName() {
        return getBukkitPlayer().getName();
    }
    @Override
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null) return false;
        if(!(obj instanceof GamePlayer)) return false;
        GamePlayer gamePlayer = (GamePlayer) obj;
        return this.getBukkitPlayer().getUniqueId().equals(gamePlayer.getBukkitPlayer().getUniqueId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getBukkitPlayer().getUniqueId());
    }

    public OfflinePlayer getBukkitPlayer() {
        return bukkitPlayer;
    }
}
