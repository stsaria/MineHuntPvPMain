package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.ChatColor;

public class RunnerTeam extends GameTeam {

    @Override
    void DirectionOnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SendMessage("逃走者になりました。");
    }

    @Override
    String bukkitTeamName() {
        return "runner";
    }

    @Override
    ChatColor bukkitTeamColor() {
        return ChatColor.AQUA;
    }

    @Override
    String bukkitTeamDisplayName() {
        return "逃走者";
    }
}
