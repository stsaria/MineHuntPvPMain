package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public abstract class Role {
    Team bukkitTeam;
    public Role() {
        ScoreboardManager scoreboardManager= Bukkit.getScoreboardManager();
        Scoreboard scoreboard=scoreboardManager.getMainScoreboard();
        for(Team team:scoreboard.getTeams()) {
            team.unregister();
        }
        bukkitTeam=scoreboard.registerNewTeam(bukkitTeamName());
        bukkitTeam.setAllowFriendlyFire(true);
        bukkitTeam.setCanSeeFriendlyInvisibles(true);
        bukkitTeam.setColor(bukkitTeamColor());
        bukkitTeam.setDisplayName(bukkitTeamDisplayName());
        bukkitTeam.setPrefix(bukkitTeamColor()+"["+bukkitTeamDisplayName()+"]");
        bukkitTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.ALWAYS);
        bukkitTeam.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.ALWAYS);
        bukkitTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
    }
    void AddPlayer(GamePlayer gamePlayer){
        bukkitTeam.addPlayer(gamePlayer.getBukkitPlayer());
        DirectionOnPlayerAdded(gamePlayer);
    }
    Material ColorBlock() {
        return ChatColorToBlock.ChatColorToBlock(bukkitTeamColor());
    }
    abstract void DirectionOnPlayerAdded(GamePlayer addedPlayer);
    abstract String bukkitTeamName();
    abstract ChatColor bukkitTeamColor();
    abstract String bukkitTeamDisplayName();
}
