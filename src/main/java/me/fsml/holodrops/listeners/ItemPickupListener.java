package me.fsml.holodrops.listeners;

import me.fsml.holodrops.util.Strings;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class ItemPickupListener implements Listener {
    
    @EventHandler
    public void itemPickup(EntityPickupItemEvent e) {
        int stack = e.getRemaining();
        if (stack > 0) {
            Item drop = e.getItem();
            String name = Strings.makeName(drop, stack);
            drop.setCustomName(name);
        }
    }
    
}
