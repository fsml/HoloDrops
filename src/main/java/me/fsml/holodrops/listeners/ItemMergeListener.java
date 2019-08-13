package me.fsml.holodrops.listeners;

import me.fsml.holodrops.Main;
import me.fsml.holodrops.util.Strings;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;

public class ItemMergeListener implements Listener {
    
    @EventHandler
    public void itemMerge(ItemMergeEvent e) {
        Item drop = e.getTarget();
        Item ent = e.getEntity();
        int count = ent.getItemStack().getAmount() + drop.getItemStack().getAmount();
        if (Main.m.settings.isWorldEnabled(drop.getWorld().getName())) {
            String name = Strings.makeName(drop, count);
            drop.setCustomName(name);
            drop.setCustomNameVisible(true);
        }
    }
    
}
