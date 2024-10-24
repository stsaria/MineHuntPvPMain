package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import si.f5.manhuntearth.manhuntearthmain.GameState;
import si.f5.manhuntearth.manhuntearthmain.Main;
import si.f5.manhuntearth.manhuntearthmain.items.CarvedPumpkin;

import java.util.Objects;

public class HunterTeam extends GameTeam {
    public void StartWaiting(Plugin plugin) {
        SetItemToHeadOfAllPlayers(new CarvedPumpkin());
        Bukkit.getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onHunterTriedMove(PlayerMoveEvent e) {
                if(Main.GetGameState()!= GameState.IN_HUNTER_WAITING_TIME) {
                    return;
                }
                if(DoesNotContain(e.getPlayer())) {
                    return;
                }
                Location from = e.getFrom();
                Location to = Objects.requireNonNull(e.getTo());
                to.setX(from.getX());
                to.setY(from.getY());
                to.setZ(from.getZ());
                e.setTo(to);
            }
        },plugin);
        AddEffectAllPlayers(PotionEffectType.BLINDNESS, Main.HUNTER_WAITING_TIME_LIMIT,1,false);
        AddEffectAllPlayers(PotionEffectType.RESISTANCE,Main.HUNTER_WAITING_TIME_LIMIT+(10*Main.SECOND),255,false);
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
