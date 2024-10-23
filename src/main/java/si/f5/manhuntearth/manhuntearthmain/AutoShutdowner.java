package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class AutoShutdowner implements Listener {
    @EventHandler
    public void ShutDownWhenPlayersAreGone(PlayerQuitEvent e) {
        if(Bukkit.getOnlinePlayers().size()==1) { // 最後の一人だったら
            Bukkit.shutdown();
        }
    }
}
