package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

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
    final public void AddPlayer(GamePlayer gamePlayer){
        bukkitTeam.addPlayer(gamePlayer.getBukkitPlayer());
        OnPlayerAdded(gamePlayer);
    }
    final public boolean HasPlayer(GamePlayer gamePlayer) {
        return HasPlayer(gamePlayer.getBukkitPlayer());
    }
    final public boolean HasPlayer(OfflinePlayer player) {
        return bukkitTeam.hasPlayer(player);
    }
    final public int Size() {
        return bukkitTeam.getSize();
    }
    final public static void RemoveAllTeams() {
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        for(Team team:scoreboard.getTeams()) {
            team.unregister();
        }
    }
    abstract void OnPlayerAdded(GamePlayer addedPlayer);
    abstract String BUKKIT_TEAM_NAME();
    public abstract ChatColor BUKKIT_TEAM_COLOR();
    public abstract String BUKKIT_TEAM_DISPLAY_NAME();
}
