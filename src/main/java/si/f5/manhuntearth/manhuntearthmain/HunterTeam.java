package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.ChatColor;

public class HunterTeam extends GameTeam {

    @Override
    void DirectionOnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SendMessage("鬼になりました。");
    }

    @Override
    String bukkitTeamName() {
        return "hunter";
    }

    @Override
    ChatColor bukkitTeamColor() {
        return ChatColor.RED;
    }

    @Override
    String bukkitTeamDisplayName() {
        return "鬼";
    }

}
