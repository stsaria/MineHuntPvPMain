package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
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
}
