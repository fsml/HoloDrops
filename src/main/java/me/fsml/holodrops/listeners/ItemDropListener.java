package me.fsml.holodrops.listeners;

import me.fsml.holodrops.Main;
import me.fsml.holodrops.util.ConfigReader;
import me.fsml.holodrops.util.Strings;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

import java.util.List;

public class ItemDropListener implements Listener {
    
    @EventHandler
    public void itemDrop(ItemSpawnEvent e) {
        Item drop = e.getEntity();
        if (Main.m.settings.isWorldEnabled(drop.getWorld().getName())) {
            String name = Strings.makeName(drop);
            drop.setCustomName(name);
            drop.setCustomNameVisible(true);
        }
    }
    
}
