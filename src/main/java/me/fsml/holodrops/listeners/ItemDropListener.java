package me.fsml.holodrops.listeners;

import me.fsml.holodrops.Main;
import me.fsml.holodrops.util.Glow;
import me.fsml.holodrops.util.Strings;
import org.bukkit.ChatColor;
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
            if (checkBlacklistLore(item.getItemMeta())) {
                return;
            }
            Strings.removeWatermark(item);
            String name = Strings.makeName(drop, item.getAmount());
            drop.setCustomName(name);
            drop.setCustomNameVisible(true);
            
            if (Main.m.settings.getItemGlow()) {
                drop.setGlowing(true);
                if (Main.m.settings.getGlowColor()) {
                    ChatColor color = Glow.getColor(Strings.makeItemName(drop));
                    Glow.setGlowColor(color, drop);
                }
            }
        }
    }
    
    private boolean checkBlacklistLore(ItemMeta meta) {
        /*
         * hardcoded solution to stop display items in UShop showing their name
         * TODO: add lore blacklist in the config?
         */
        
        if (meta.hasLore()) {
            for (String s : meta.getLore()) {
                if (s.contains("Display")) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
