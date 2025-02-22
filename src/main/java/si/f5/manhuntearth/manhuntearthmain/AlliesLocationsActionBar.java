package si.f5.manhuntearth.manhuntearthmain;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import si.f5.manhuntearth.manhuntearthmain.roles.GameTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.HunterTeam;
import si.f5.manhuntearth.manhuntearthmain.roles.RunnerTeam;


public class AlliesLocationsActionBar {
    public static void update(HunterTeam hunter, RunnerTeam runner){
        for (GameTeam team : new GameTeam[]{hunter, runner}){
            if (team.GetGamePlayers().isEmpty()){
                continue;
            }
            StringBuilder locationsStringBuilder = new StringBuilder();
            locationsStringBuilder.append("§a味方の位置§r ");
            OfflinePlayer offlinePlayer;
            Player player;
            Location location;
            for (GamePlayer gamePlayer : team.GetGamePlayers()){
                offlinePlayer = gamePlayer.getBukkitPlayer();
                if (!offlinePlayer.isOnline()){
                    continue;
                }
                player = offlinePlayer.getPlayer();
                location = player.getLocation();
                locationsStringBuilder.append(player.getName()).append(":").append(location.getBlockX()).append(", ").append(location.getBlockY()).append(", ").append(location.getBlockZ()).append(" / ");
            }
            locationsStringBuilder.delete(locationsStringBuilder.length()-3, locationsStringBuilder.length()-1);
            for (GamePlayer gamePlayer : team.GetGamePlayers()){
                offlinePlayer = gamePlayer.getBukkitPlayer();
                if (!offlinePlayer.isOnline()){
                    continue;
                }
                player = offlinePlayer.getPlayer();
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(locationsStringBuilder.toString().replace(" "+player.getName()+":", " §e"+player.getName()+"§r:")));
            }
        }
    }
}
