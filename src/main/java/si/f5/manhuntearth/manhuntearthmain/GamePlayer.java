package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Optional;

public class GamePlayer {
    private final OfflinePlayer bukkitPlayer;
    public GamePlayer(OfflinePlayer bukkitPlayer) {
        this.bukkitPlayer=bukkitPlayer;
    }
    public void SendMessage(String message) {
        getOnlinePlayer().ifPresent(p-> p.sendMessage(message));
    }
    public void SetItem(GameItem item,int slot) {
        getOnlinePlayer().ifPresent(p-> p.getInventory().setItem(slot, item.GetItemStack()));
    }
    public void Clear() {
        getOnlinePlayer().ifPresent(p-> p.getInventory().clear());
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
    public Optional<Player> getOnlinePlayer() {
        if(getBukkitPlayer().isOnline()) {
            return Optional.of((Player)getBukkitPlayer());
        } else {
            return Optional.empty();
        }
    }
}
