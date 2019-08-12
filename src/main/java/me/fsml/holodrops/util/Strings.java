package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;

public class Strings {
    
    
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    
    public static String makeName(Item drop) {
        String formatted = Main.m.settings.getFormat().toUpperCase();
        String itemName = "";
        
        if (drop.getItemStack().getItemMeta().hasDisplayName() || Main.m.settings.getCustomNamesOnly()) {
            itemName = drop.getItemStack().getItemMeta().getDisplayName();
        } else {
            itemName = Main.m.settings.getNameFromMat(drop.getItemStack().getType().name());
        }
        formatted = formatted.replace("P", Main.m.settings.getPrefix())
                .replace("I", itemName)
                .replace("C", "" + drop.getItemStack().getAmount())
                .replace("S", Main.m.settings.getSuffix());
        
        return itemName.length() == 0 ? itemName : formatted;
        
    }
    
}
