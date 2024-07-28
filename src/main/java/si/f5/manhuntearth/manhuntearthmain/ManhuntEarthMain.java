package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.plugin.java.JavaPlugin;

public final class ManhuntEarthMain extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new Main(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
