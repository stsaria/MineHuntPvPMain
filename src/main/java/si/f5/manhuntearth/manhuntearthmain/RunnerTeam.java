package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.ChatColor;

public class RunnerTeam extends GameTeam {

    @Override
    void DirectionOnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SendMessage("逃走者になりました。");
    }

    @Override
    String BUKKIT_TEAM_NAME() {
        return "runner";
    }

    @Override
    ChatColor BUKKIT_TEAM_COLOR() {
        return ChatColor.AQUA;
    }

    @Override
    String BUKKIT_TEAM_DISPLAY_NAME() {
        return "逃走者";
    }
}
