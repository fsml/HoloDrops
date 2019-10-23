package me.fsml.holodrops.listeners;

import me.fsml.holodrops.Main;
import me.fsml.holodrops.util.Strings;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class ItemPickupListener implements Listener {
    
    @EventHandler
    public void itemPickup(EntityPickupItemEvent e) {
        if (Main.m.settings.getProtectedDrops().containsKey(e.getItem())) {
            if (Main.m.settings.getProtectedDrops().get(e.getItem()) != e.getEntity()) {
                e.setCancelled(true);
                return;
            }
        }
        int stack = e.getRemaining();
        if (stack > 0) {
            Item drop = e.getItem();
            String name = Strings.makeName(drop, stack, "", 0);
            drop.setCustomName(name);
        }
    }
    
}
