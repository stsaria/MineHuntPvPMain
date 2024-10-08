package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.ChatColor;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

public class SpectatorRole extends Role {

    @Override
    void DirectionOnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SendMessage("観戦者になりました。");
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
