package si.f5.manhuntearth.manhuntearthmain.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.*;

public class CarvedPumpkin extends GameItem{
    @Override
    Material MATERIAL() {
        return Material.CARVED_PUMPKIN;
    }

    @Override
    String NAME() {
        return "J A C K";
    }

    @Override
    List<String> LORE() {
        return new ArrayList<String>(Arrays.asList("CURSE OF JACK"));
    }

    @Override
    Optional<Map<Enchantment, Integer>> ENCHANTMENT() {
        Map<Enchantment,Integer> enchantment=new HashMap<>();
        enchantment.put(Enchantment.BINDING_CURSE,1);
        return Optional.of(enchantment);
    }
}
