package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public Main(Plugin plugin) {
        List<GamePlayer> playersList = new ArrayList<>();
        Bukkit.getServer().getPluginManager().registerEvents(new AddNewPlayerToPlayersList(playersList),plugin);
    }
}
