package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.EventListener;
import java.util.Objects;

public final class ManhuntEarthMain extends JavaPlugin implements EventListener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new Main(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        Player killer = e.getEntity().getKiller();
        if (killer == null){
            return;
        }
        double halfHealth = Objects.requireNonNull(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getDefaultValue() / 2d;
        if (halfHealth > killer.getHealth()){
            killer.setHealth(halfHealth);
        }
    }
}
