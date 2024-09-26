package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.ChatColor;

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
    ChatColor BUKKIT_TEAM_COLOR() {
        return ChatColor.RED;
    }

    @Override
    String BUKKIT_TEAM_DISPLAY_NAME() {
        return "鬼";
    }

}
