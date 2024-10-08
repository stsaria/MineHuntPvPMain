package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.ChatColor;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

public class HunterTeam extends GameTeam {

    @Override
    void DirectionOnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SendMessage("鬼になりました。");
    }

    @Override
    String BUKKIT_TEAM_NAME() {
        return "hunter";
    }

    @Override
    public ChatColor BUKKIT_TEAM_COLOR() {
        return ChatColor.RED;
    }

    @Override
    public String BUKKIT_TEAM_DISPLAY_NAME() {
        return "鬼";
    }

}
