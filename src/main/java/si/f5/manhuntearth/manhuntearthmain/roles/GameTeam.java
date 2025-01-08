package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.GameMode;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

public abstract class GameTeam extends Role{
    @Override
    void OnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SetGameMode(GameMode.SURVIVAL);
    }
    public abstract String titleOnStartDirection();
    public abstract String subtitleOnStartDirection();
    public abstract String[] winConditions();
}
