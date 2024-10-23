package si.f5.manhuntearth.manhuntearthmain.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import si.f5.manhuntearth.manhuntearthmain.Main;

import java.util.ArrayList;
import java.util.Collections;
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
        return new ArrayList<>(Collections.singletonList("ゲームを開始します。"));
    }

    @Override
    protected void Process(PlayerInteractEvent e) {
        Main.StartFlag();
    }
}
