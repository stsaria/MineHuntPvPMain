package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class ChatColorToBlock {
    private static final Material[] materials =
            {
                Material.BLACK_CONCRETE,
                Material.BLUE_GLAZED_TERRACOTTA,
                Material.GREEN_TERRACOTTA,
                Material.STRIPPED_WARPED_HYPHAE,
                Material.STRIPPED_MANGROVE_WOOD,
                Material.PURPLE_GLAZED_TERRACOTTA,
                Material.GOLD_BLOCK,
                Material.LIGHT_GRAY_CONCRETE,
                Material.GRAY_CONCRETE,
                Material.BLUE_CONCRETE,
                Material.LIME_CONCRETE,
                Material.LIGHT_BLUE_CONCRETE,
                Material.RED_CONCRETE,
                Material.MAGENTA_CONCRETE,
                Material.YELLOW_CONCRETE,
                Material.WHITE_CONCRETE,
                Material.AIR,
                Material.AIR,
                Material.AIR,
                Material.AIR,
                Material.AIR,
                Material.AIR
            };
    public static Material ChatColorToBlock(ChatColor chatColor) {
        return materials[chatColor.ordinal()];
    }
}
