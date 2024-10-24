package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class GameWorld {
    final private World bukkitOverWorld;
    private final int WORLD_BORDER_DIAMETER=2048;
    public GameWorld() {
        bukkitOverWorld=Bukkit.getWorlds().stream().filter(world -> world.getEnvironment() == World.Environment.NORMAL).findFirst().get();
        bukkitOverWorld.getWorldBorder().setCenter(new Location(bukkitOverWorld,0,0,0));
        bukkitOverWorld.getWorldBorder().setSize(WORLD_BORDER_DIAMETER);
    }
    public void StartTheGame() {
        bukkitOverWorld.setTime(0);
        bukkitOverWorld.setStorm(false);
        bukkitOverWorld.setThundering(false);
    }
    public World GetOverWorld() {
        return bukkitOverWorld;
    }
}
