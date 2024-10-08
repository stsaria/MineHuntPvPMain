package si.f5.manhuntearth.manhuntearthmain.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class GameItem{
    abstract Material MATERIAL();
    abstract String NAME();
    abstract List<String> LORE();
    abstract Optional<Map<Enchantment,Integer>> ENCHANTMENT();
    private final ItemStack itemStack;
    public GameItem() {
        itemStack= new ItemStack(MATERIAL());
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.setDisplayName(NAME());
        itemMeta.setLore(LORE());
        ENCHANTMENT().ifPresent(m-> m.entrySet().forEach(e-> itemMeta.addEnchant(e.getKey(),e.getValue(),true)));
        itemStack.setItemMeta(itemMeta);
    }
    public ItemStack GetItemStack() {
        return itemStack;
    }
}
