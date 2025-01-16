package si.f5.manhuntearth.manhuntearthmain;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import si.f5.manhuntearth.manhuntearthmain.roles.GameTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.HunterTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.RunnerTeam;

import java.util.Objects;


public class AlliesLocationsActionBar {
    public static void update(HunterTeam hunter, RunnerTeam runner){
        for (GameTeam team : new GameTeam[]{hunter, runner}){
            if (team.GetGamePlayers().isEmpty()){
                continue;
            }
            StringBuilder locationsStringBuilder = new StringBuilder();
            locationsStringBuilder.append("§a味方の位置§r ");
            Player player;
            Location location;
            for (GamePlayer gamePlayer : team.GetGamePlayers()){
                player = (Player) gamePlayer.getBukkitPlayer();
                location = player.getLocation();
                locationsStringBuilder.append(player.getName()+":"+location.getBlockX()+", "+location.getBlockY()+", "+location.getBlockZ()+" / ");
            }
            locationsStringBuilder.delete(locationsStringBuilder.length()-4, locationsStringBuilder.length()-1);
            for (GamePlayer gamePlayer : team.GetGamePlayers()){
                player = (Player) gamePlayer.getBukkitPlayer();
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(locationsStringBuilder.toString().replace(" "+player.getName()+":", " §e"+player.getName()+"§r:")));
            }
        }
    }
}
