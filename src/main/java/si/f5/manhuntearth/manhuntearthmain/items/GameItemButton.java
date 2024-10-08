package si.f5.manhuntearth.manhuntearthmain.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.f5.manhuntearth.manhuntearthmain.GamePlayer;

import java.util.Map;
import java.util.Optional;

public abstract class GameItemButton extends GameItem implements Listener {
    final Enchantment ENCHANTMENT_FOR_IDENTIFICATION=Enchantment.UNBREAKING;
    final int LEVEL_OF_ENCHANTMENT_FOR_IDENTIFICATION=100;
    public GameItemButton() {
        ItemMeta itemMeta = super.GetItemStack().getItemMeta();
        itemMeta.addEnchant(ENCHANTMENT_FOR_IDENTIFICATION,LEVEL_OF_ENCHANTMENT_FOR_IDENTIFICATION,true);
        itemMeta.addEnchant(Enchantment.VANISHING_CURSE,1,true);
        super.GetItemStack().setItemMeta(itemMeta);
    }
    @EventHandler
    public void OnInteract(PlayerInteractEvent e) {
        if(!(hasThisItemInMainHand(new GamePlayer(e.getPlayer())))) {
            return;
        }
        Process(e);
    }
    @EventHandler
    public void OnDrop(PlayerDropItemEvent e) {
        if(IsThisItem(e.getItemDrop().getItemStack())) {
            e.setCancelled(true);
        }
    }
    private boolean hasThisItemInMainHand(GamePlayer player) {
        return IsThisItem(player.GetItemInMainHand());
    }
    private boolean IsThisItem(ItemStack itemStack) {
        if(itemStack.getType() != this.MATERIAL()) return false;
        if(!(itemStack.getItemMeta().getDisplayName().equals(this.NAME()))) return false;
        for(Map.Entry<Enchantment,Integer> entry:itemStack.getItemMeta().getEnchants().entrySet()) {
            if(entry.getKey().getKey().equals(ENCHANTMENT_FOR_IDENTIFICATION.getKey()) && entry.getValue()==LEVEL_OF_ENCHANTMENT_FOR_IDENTIFICATION) {
                return true;
            }
        }
        return false;
    }
    @Override
    public Optional<Map<Enchantment, Integer>> ENCHANTMENT() {
        return Optional.empty();
    }
    abstract protected void Process(PlayerInteractEvent e);
}
