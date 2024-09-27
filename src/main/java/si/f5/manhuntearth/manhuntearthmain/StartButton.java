package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartButton extends GameItemButton {
    @Override
    public Material MATERIAL() {
        return Material.LIME_DYE;
    }

    @Override
    public String NAME() {
        return "スタート";
    }

    @Override
    List<String> LORE() {
        return new ArrayList<String>(Arrays.asList("ゲームを開始します。"));
    }

    @Override
    protected void Process(PlayerInteractEvent e) {
        Main.StartFlag();
    }
}
