package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.ChatColor;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

public class RunnerTeam extends GameTeam {
    @Override
    String BUKKIT_TEAM_NAME() {
        return "runner";
    }

    @Override
    public ChatColor BUKKIT_TEAM_COLOR() {
        return ChatColor.AQUA;
    }

    @Override
    public String BUKKIT_TEAM_DISPLAY_NAME() {
        return "逃走者";
    }
}
