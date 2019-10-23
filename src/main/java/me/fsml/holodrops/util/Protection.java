package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Protection {
    
    public static void dealWithProt(Item item, Player p){
        if (Main.m.settings.getProtItemList().isEmpty() || Main.m.settings.getProtItemList().contains(item.getItemStack().getType().toString())) {
            Main.m.settings.getProtectedDrops().put(item, p);
            new BukkitRunnable() {
                int time = Main.m.settings.getProtTime();
                String pName = p.getName();
                public void run() {
                    try {
                        if (time <= 0) {
                            pName = "";
                        }
                        item.setCustomName(Strings.makeName(item, item.getItemStack().getAmount(), pName, time));
                    } catch (Exception exc) {
                        this.cancel();
                    }
                    if (time <= 0) {
                        Main.m.settings.getProtectedDrops().remove(item);
                        this.cancel();
                    }
                    time--;
                }
            }.runTaskTimerAsynchronously(Main.m, 0, 20);
        }
    }
    
    
}
