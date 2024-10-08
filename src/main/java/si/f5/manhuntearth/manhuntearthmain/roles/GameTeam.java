package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.GameMode;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

public abstract class GameTeam extends Role{
    @Override
    void OnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SendMessage(BUKKIT_TEAM_COLOR()+BUKKIT_TEAM_DISPLAY_NAME()+"になりました。");
        addedPlayer.SetGameMode(GameMode.SURVIVAL);
    }
}
