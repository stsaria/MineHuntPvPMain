package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.entity.Player;
import si.f5.manhuntearth.manhuntearthmain.items.GameItem;
import si.f5.manhuntearth.manhuntearthmain.roles.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GamePlayersList {
    List<GamePlayer> playersList;
    public GamePlayersList() {
        this.playersList = new ArrayList<>();
    }
    public void AddPlayer(GamePlayer player) {
        if(this.playersList.contains(player)) {
            return;
        }
        this.playersList.add(player);
    }
    public void AddPlayer(Player player) {
        AddPlayer(new GamePlayer(player));
    }
    public void RemovePlayer(GamePlayer player) {
        this.playersList.remove(player);
    }
    public void RemovePlayer(Player player) {
        RemovePlayer(new GamePlayer(player));
    }
    public void SetItemToAllPlayersInventory(GameItem item, int slot) {
        for (GamePlayer gamePlayer:playersList) {
            gamePlayer.SetItem(item,slot);
        }
    }
    public void SetItemToHostsInventory(GameItem item,int slot) {
        GetHost().ifPresent(v ->v.SetItem(item,slot));
    }
    public void ClearALl() {
        playersList.forEach(GamePlayer::Clear);
    }
    public void TeamDivide(Role role1, Role role2) {
        ArrayList<GamePlayer> l = new ArrayList<>(playersList);
        Collections.shuffle(l);
        int i;
        for (i=0;i<l.size()/2;i++) {
            role1.AddPlayer(l.get(i));
        }
        for(;i<l.size();i++) {
            role2.AddPlayer(l.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(GamePlayer gamePlayer:playersList) {
            string.append(gamePlayer.GetName());
            string.append("\n");
        }
        return string.toString();
    }
    private Optional<GamePlayer> GetHost() {
        if(playersList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(playersList.get(0));
    }
}
