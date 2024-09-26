package si.f5.manhuntearth.manhuntearthmain;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public abstract class GameItemButton extends GameItem implements Listener {
    final Enchantment ENCHANTMENT_FOR_IDENTIFICATION=Enchantment.UNBREAKING;
    final int LEVEL_OF_ENCHANTMENT_FOR_IDENTIFICATION=100;
    public GameItemButton() {
        ItemMeta itemMeta = super.GetItemStack().getItemMeta();
        itemMeta.addEnchant(ENCHANTMENT_FOR_IDENTIFICATION,LEVEL_OF_ENCHANTMENT_FOR_IDENTIFICATION,true);
        super.GetItemStack().setItemMeta(itemMeta);
    }
    @EventHandler
    public void OnInteract(PlayerInteractEvent e) {
        if(!(hasThisItemInMainHand(new GamePlayer(e.getPlayer())))) {
            return;
        }
        Process(e);
    }
    private boolean hasThisItemInMainHand(GamePlayer player) {
        ItemStack itemStack = player.GetItemInMainHand();
        if(itemStack.getType() != this.MATERIAL()) return false;
        if(!(itemStack.getItemMeta().getDisplayName().equals(this.NAME()))) return false;
        for(Map.Entry<Enchantment,Integer> entry:itemStack.getItemMeta().getEnchants().entrySet()) {
            if(entry.getKey().getKey().equals(ENCHANTMENT_FOR_IDENTIFICATION.getKey()) && entry.getValue()==LEVEL_OF_ENCHANTMENT_FOR_IDENTIFICATION) {
                return true;
            }
        }
        return false;
    }
    abstract protected void Process(PlayerInteractEvent e);
}
