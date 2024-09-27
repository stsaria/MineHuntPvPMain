package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public void SetItemToAllPlayersInventory(GameItem item,int slot) {
        for (GamePlayer gamePlayer:playersList) {
            gamePlayer.SetItem(item,slot);
        }
    }
    public void SetItemToHostsInventory(GameItem item,int slot) {

    }
    public void TeamDivide(Role role1,Role role2) {
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
        String string = "";
        for(GamePlayer gamePlayer:playersList) {
            string+=gamePlayer.GetName();
            string+="\n";
        }
        return string;
    }
}
