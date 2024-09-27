package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public abstract class Role {
    Team bukkitTeam;
    public Role() {
        ScoreboardManager scoreboardManager= Bukkit.getScoreboardManager();
        Scoreboard scoreboard=scoreboardManager.getMainScoreboard();
        bukkitTeam=scoreboard.registerNewTeam(BUKKIT_TEAM_NAME());
        bukkitTeam.setAllowFriendlyFire(true);
        bukkitTeam.setCanSeeFriendlyInvisibles(true);
        bukkitTeam.setColor(BUKKIT_TEAM_COLOR());
        bukkitTeam.setDisplayName(BUKKIT_TEAM_DISPLAY_NAME());
        bukkitTeam.setPrefix(BUKKIT_TEAM_COLOR()+"["+ BUKKIT_TEAM_DISPLAY_NAME()+"]");
        bukkitTeam.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.ALWAYS);
        bukkitTeam.setOption(Team.Option.DEATH_MESSAGE_VISIBILITY, Team.OptionStatus.ALWAYS);
        bukkitTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OWN_TEAM);
    }
    void AddPlayer(GamePlayer gamePlayer){
        bukkitTeam.addPlayer(gamePlayer.getBukkitPlayer());
        DirectionOnPlayerAdded(gamePlayer);
    }
    public static void RemoveAllTeams() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        for(Team team:scoreboard.getTeams()) {
            team.unregister();
        }
    }
    Material ColorBlock() {
        return ChatColorToBlock.ChatColorToBlock(BUKKIT_TEAM_COLOR());
    }
    abstract void DirectionOnPlayerAdded(GamePlayer addedPlayer);
    abstract String BUKKIT_TEAM_NAME();
    abstract ChatColor BUKKIT_TEAM_COLOR();
    abstract String BUKKIT_TEAM_DISPLAY_NAME();
}
