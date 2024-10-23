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
    public void SetItemToPlayersInTeam(Role role, GameItem item, int slot) {
        playersList.stream().filter(p-> role.HasPlayer(p)).forEach(p-> p.SetItem(item,slot));
    }
    public void SetItemToHeadOfPlayersInTeam(Role role, GameItem item) {
        playersList.stream().filter(p-> role.HasPlayer(p)).forEach(p-> p.SetItemToHead(item));
    }
    public void ClearALlPlayers() {
        playersList.forEach(GamePlayer::Clear);
    }
    public void ClearPlayersInTeam(Role role) {
        playersList.stream().filter(p-> role.HasPlayer(p)).forEach(GamePlayer::Clear);
    }
    public void ClearEffectsAllPlayers() {
        playersList.forEach(GamePlayer::ClearEffects);
    }
    public void SetHealthMaxAllPlayers() {
        playersList.forEach(GamePlayer::SetHealthMax);
    }
    public void SetFoodLevelMaxAllPlayers() {
        playersList.forEach(GamePlayer::SetFoodLevelMax);
    }
    public void TeamDivide(Role role1, Role role2, boolean allPlayersIntoRole2) {
        ArrayList<GamePlayer> l = new ArrayList<>(playersList);
        if(allPlayersIntoRole2) {
            l.forEach(p-> role2.AddPlayer(p));
            return;
        }
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
