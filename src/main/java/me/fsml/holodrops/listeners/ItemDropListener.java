package me.fsml.holodrops.listeners;

import me.fsml.holodrops.Main;
import me.fsml.holodrops.util.Strings;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class ItemDropListener implements Listener {
    
    @EventHandler
    public void itemDrop(ItemSpawnEvent e) {
        Item drop = e.getEntity();
        if (Main.m.settings.isWorldEnabled(drop.getWorld().getName())) {
            ItemStack item = drop.getItemStack();
            /*
             * hardcoded solution to shop display items in UShop showing their name
             * TODO: add lore blacklist in the config?
             */
            ItemMeta meta = item.getItemMeta();
            if (meta.hasLore()) {
                if (meta.getLore().get(0).contains("Display Item!")) {
                    return;
                }
            }
            Strings.removeWatermark(item);
            String name = Strings.makeName(drop, item.getAmount());
            drop.setCustomName(name);
            drop.setCustomNameVisible(true);
        }
    }
    
}
