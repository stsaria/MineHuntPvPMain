package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class GameItem{
    abstract Material MATERIAL();
    abstract String NAME();
    abstract List<String> LORE();
    private final ItemStack itemStack;
    public GameItem() {
        itemStack= new ItemStack(MATERIAL());
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.setDisplayName(NAME());
        itemMeta.setLore(LORE());
        itemStack.setItemMeta(itemMeta);
    }
    public ItemStack GetItemStack() {
        return itemStack;
    }
}
