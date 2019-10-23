package me.fsml.holodrops.listeners.protection;

import me.fsml.holodrops.Main;
import me.fsml.holodrops.util.Protection;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

import java.util.List;

public class BlockDropListener implements Listener {
    
    @EventHandler
    public void blockBreak(BlockDropItemEvent e) {
        if (!Main.m.settings.protectionEnabled()) {
            return;
        }
        if (!Main.m.settings.isWorldEnabled(e.getPlayer().getWorld().getName())) {
            return;
        }
        if (!Main.m.settings.getBlockProtection()) {
            return;
        }
        List<Item> drops = e.getItems();
        Player p = e.getPlayer();
        
        for (Item item : drops) {
            Protection.dealWithProt(item, p);
        }
        
        
    }
    
    
}
