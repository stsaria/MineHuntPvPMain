package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.ChatColor;

public class SpectatorRole extends Role {

    @Override
    void DirectionOnPlayerAdded(GamePlayer addedPlayer) {
        addedPlayer.SendMessage("観戦者になりました。");
    }

    @Override
    String bukkitTeamName() {
        return "spectator";
    }

    @Override
    ChatColor bukkitTeamColor() {
        return ChatColor.GRAY;
    }

    @Override
    String bukkitTeamDisplayName() {
        return "観戦者";
    }
}
