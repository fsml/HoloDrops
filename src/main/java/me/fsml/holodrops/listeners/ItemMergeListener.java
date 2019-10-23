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
        // if theyre both protected compare the owners
        if (isItemProtected(drop) && isItemProtected(ent)) {
            if (Main.m.settings.getProtectedDrops().get(drop) != Main.m.settings.getProtectedDrops().get(ent)) {
                e.setCancelled(true);
                return;
            }
        }
        // if only 1 is protected they dont merge
        else if (isItemProtected(drop) || isItemProtected(ent)) {
            e.setCancelled(true);
            return;
        }
        int count = ent.getItemStack().getAmount() + drop.getItemStack().getAmount();
        if (Main.m.settings.isWorldEnabled(drop.getWorld().getName())) {
            String name = Strings.makeName(drop, count, "", 0);
            drop.setCustomName(name);
            drop.setCustomNameVisible(true);
        }
    }
    
    private boolean isItemProtected(Item item) {
        return Main.m.settings.getProtectedDrops().containsKey(item);
    }
    
}
