package si.f5.manhuntearth.manhuntearthmain.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuitButton extends GameItemButton{
    public QuitButton(JavaPlugin javaPlugin) {
        super(javaPlugin);
    }

    @Override
    protected void Process(PlayerInteractEvent e) {
        e.getPlayer().kickPlayer("ゲームを退出しました。");
    }

    @Override
    Material MATERIAL() {
        return Material.RED_BED;
    }

    @Override
    String NAME() {
        return "ゲームを退出";
    }

    @Override
    List<String> LORE() {
        return new ArrayList<>(Collections.singletonList("ゲームを退出します。"));
    }
}
