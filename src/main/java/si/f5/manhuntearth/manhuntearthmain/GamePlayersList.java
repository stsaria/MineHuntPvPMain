package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GamePlayersList {
    List<GamePlayer> playersList;
    public GamePlayersList() {
        this.playersList = new ArrayList<>();
    }
    public void SetItemToAllPlayersInventory(ItemStack itemStack,int slot) {
        for (GamePlayer gamePlayer:playersList) {
            gamePlayer.SetItem(itemStack,slot);
        }
    }
    private void Shuffle() {
        Collections.shuffle(playersList);
    }
    private int Number() {
        return playersList.size();
    }
    private GamePlayer GetGamePlayer(int index) {
        return playersList.get(index);
    }
    public void TeamDivide(Role role1,Role role2) {
        Shuffle();
        int i;
        for(i=0; i< Number()/2; i++) {
            role1.AddPlayer(GetGamePlayer(i));
        }
        for(; i<Number();i++) {
            role2.AddPlayer(GetGamePlayer(i));
        }
    }
}
