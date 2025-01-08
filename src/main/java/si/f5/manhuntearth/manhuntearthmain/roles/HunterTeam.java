package si.f5.manhuntearth.manhuntearthmain.roles;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import si.f5.manhuntearth.manhuntearthmain.*;
import si.f5.manhuntearth.manhuntearthmain.items.CarvedPumpkin;

import java.util.Objects;

public class HunterTeam extends GameTeam {
    public void StartWaiting(Plugin plugin, GameTime waitingTime) {
        SetItemToHeadOfAllPlayers(new CarvedPumpkin());
        for(OfflinePlayer offlinePlayer:bukkitTeam.getPlayers()) {
            GamePlayer gamePlayer = GamePlayer.New(offlinePlayer);
            gamePlayer.Teleport(new Location(
                    gamePlayer.getBukkitPlayer().getLocation().getWorld(),
                    GameWorld.WORLD_SPAWN_X+0.5,
                    gamePlayer.getBukkitPlayer().getLocation().getWorld().getHighestBlockYAt(GameWorld.WORLD_SPAWN_X,GameWorld.WORLD_SPAWN_Z)+1.0,
                    GameWorld.WORLD_SPAWN_Z+0.5));
        }
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
        AddEffectAllPlayers(PotionEffectType.BLINDNESS,waitingTime,1,false);
        AddEffectAllPlayers(PotionEffectType.RESISTANCE,waitingTime.Add(new GameTime(0,10)),255,false);
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

    @Override
    public String titleOnStartDirection() {
        return "追いかけろ";
    }

    @Override
    public String subtitleOnStartDirection() {
        return BUKKIT_TEAM_COLOR()+"鬼になった 開放まで待て";
    }

    @Override
    public String[] winConditions() {
        return new String[]{"時間切れ","逃走者全員が死ぬ(全員を倒す)"};
    }
}
