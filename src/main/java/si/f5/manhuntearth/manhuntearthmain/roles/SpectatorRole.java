package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

public class SpectatorRole extends Role {

    @Override
    void OnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SetGameMode(GameMode.SPECTATOR);
    }

    @Override
    String BUKKIT_TEAM_NAME() {
        return "spectator";
    }

    @Override
    public ChatColor BUKKIT_TEAM_COLOR() {
        return ChatColor.GRAY;
    }

    @Override
    public String BUKKIT_TEAM_DISPLAY_NAME() {
        return "観戦者";
    }
}
