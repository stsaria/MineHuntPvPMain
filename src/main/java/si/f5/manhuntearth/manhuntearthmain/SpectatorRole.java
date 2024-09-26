package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.ChatColor;

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
    ChatColor BUKKIT_TEAM_COLOR() {
        return ChatColor.GRAY;
    }

    @Override
    String BUKKIT_TEAM_DISPLAY_NAME() {
        return "観戦者";
    }
}
