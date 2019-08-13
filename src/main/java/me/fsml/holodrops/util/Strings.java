package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Strings {
    
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    
    public static String stripColor(String string) {
        return ChatColor.stripColor(string);
    }
    
    public static String makeName(Item drop, int count) {
        String formatted = Main.m.settings.getFormat().toUpperCase();
        String itemName = "";
        
        ItemMeta meta = drop.getItemStack().getItemMeta();
        if (meta.hasDisplayName() || Main.m.settings.getCustomNamesOnly()) {
            String name = meta.getDisplayName();
            if (!Main.m.settings.isBlacklisted(name)) {
                itemName = name;
            }
        } else {
            itemName = Main.m.settings.getNameFromMat(drop.getItemStack().getType().name());
        }
        
        formatted = rePlaceholders(formatted, itemName, count);
        
        return itemName.length() == 0 ? itemName : formatted;
        
    }
    
    private static String rePlaceholders(String formatted, String item, int count) {
        formatted = formatted.replace("%P%", Main.m.settings.getPrefix())
                .replace("%I%", item)
                .replace("%S%", Main.m.settings.getSuffix());
        // single stacks
        // count != 0 is for item frames (never display the stack count)
        if (count != 0 && count != 1 || Main.m.settings.getSingleStack()) {
            formatted = formatted.replace("%C%", Main.m.settings.getStackFormat().replace("%amount%", "" + count));
        } else {
            // remove a the space at the end
            formatted = formatted.replace(" %C%", "");
            // in case there was no space
            formatted = formatted.replace("%C%", "");
        }
        return formatted;
    }
    
    // count is supplied to make a call to rePlaceholders
    public static void makeItemFrameName(ItemStack item, int count) {
        ItemMeta meta = item.getItemMeta();
        String formatted = Main.m.settings.getFormat().toUpperCase();
        String itemName = "";
        
        itemName = Main.m.settings.getNameFromMat(item.getType().name());
        formatted = rePlaceholders(formatted, itemName, count);
        
        meta.setDisplayName(itemName.length() == 0 ? itemName : formatted);
        item.setItemMeta(meta);
        
    }
    
    public static void addWatermark(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList();
        lore.add("HoloDrops");
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    
    public static void removeWatermark(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (hasWatermark(item)) {
            List<String> lore = meta.getLore();
            lore.remove(lore.size() - 1);
            meta.setLore(lore);
            meta.setDisplayName("");
            item.setItemMeta(meta);
        }
    }
    
    public static boolean hasWatermark(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            // last line has watermark
            return lore.get(lore.size() - 1).equals("HoloDrops");
        }
        return false;
    }
    
    
}
