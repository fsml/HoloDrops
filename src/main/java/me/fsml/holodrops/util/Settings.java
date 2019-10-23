package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class Settings {
    
    private HashMap<String, Boolean> settings = new HashMap<String, Boolean>();
    private HashMap<String, String> format = new HashMap<String, String>();
    private HashMap<String, String> names = new HashMap<String, String>();
    private List<String> enabledWorlds;
    private List<String> blacklist;
    private List<String> glowlist;
    private List<String> protitemlist;
    private HashMap<Item, Player> protectedDrops = new HashMap<Item, Player>();
    private int protTime;
    
    public void initialize() {
        Main.m.reloadConfig();
        settings.clear();
        format.clear();
        names.clear();
        enabledWorlds = ConfigReader.getStringList("enabled-worlds");
        blacklist = ConfigReader.getStringList("blacklist");
        glowlist = Strings.colorList(ConfigReader.getStringList("glowlist"));
        protitemlist = ConfigReader.getStringList("protection-item-list");
        
        settings.put("item-frame-holos", ConfigReader.getBoolean("item-frame-holos"));
        settings.put("custom-names-only", ConfigReader.getBoolean("custom-names-only"));
        settings.put("single-stack", ConfigReader.getBoolean("single-stack"));
        settings.put("item-glow", ConfigReader.getBoolean("item-glow"));
        settings.put("glow-color", ConfigReader.getBoolean("glow-color"));
        settings.put("drop-protection", ConfigReader.getBoolean("drop-protection"));
        settings.put("protect-block-drops", ConfigReader.getBoolean("protection-sources.block-drops"));
        protTime = ConfigReader.getInt("protection-time");
        
        format.put("stack-count", Strings.color(ConfigReader.getString("stack-count")));
        format.put("holo-prefix", Strings.color(ConfigReader.getString("holo-prefix")));
        format.put("holo-suffix", Strings.color(ConfigReader.getString("holo-suffix")));
        format.put("holo-format", Strings.color(ConfigReader.getString("holo-format")));
        format.put("protection-format", Strings.color(ConfigReader.getString("protection-format")));
        
        for (String configMaterial : Main.m.getConfig().getConfigurationSection("item-names").getKeys(false)) {
            String mat = Main.m.getConfig().getString("item-names." + configMaterial);
            names.put(configMaterial, Strings.color(mat));
        }
    }
    
    public boolean getFrames() {
        return settings.get("item-frame-holos");
    }
    
    public boolean getCustomNamesOnly() {
        return settings.get("custom-names-only");
    }
    
    public boolean getSingleStack() {
        return settings.get("single-stack");
    }
    
    public boolean getItemGlow() {
        return settings.get("item-glow");
    }
    
    public boolean getGlowColor() {
        return settings.get("glow-color");
    }
    
    public String getStackFormat() {
        return format.get("stack-count");
    }
    
    public String getPrefix() {
        return format.get("holo-prefix");
    }
    
    public String getSuffix() {
        return format.get("holo-suffix");
    }
    
    public String getFormat() {
        return format.get("holo-format");
    }
    
    public String getProtFormat() {
        return format.get("protection-format");
    }
    
    public boolean isWorldEnabled(String world) {
        return enabledWorlds.contains(world);
    }
    
    public boolean isBlacklisted(String name) {
        for (String s : blacklist) {
            if (Strings.stripColor(s).equals(Strings.stripColor(name))) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isGlowlisted(String name) {
        if (glowlist.isEmpty()) { // all items are allowed if empty
            return true;
        } else {
            return glowlist.contains(name); // color sensitive, so different way to check than the blacklist
        }
        // return glowlist.isEmpty() ? true : glowlist.contains(name);
    }
    
    public String getNameFromMat(String material) {
        if (names.containsKey(material)) {
            return names.get(material);
        } else {
            return "";
        }
    }
    
    public boolean protectionEnabled() {
        return settings.get("drop-protection");
    }
    
    public String protectionFormat() {
        return format.get("protection-format");
    }
    
    public List<String> getProtItemList() {
        return protitemlist;
    }
    
    public boolean getBlockProtection() {
        return settings.get("protect-block-drops");
    }
    
    public int getProtTime() {
        return protTime;
    }
    
    public HashMap<Item, Player> getProtectedDrops() {
        return protectedDrops;
    }
    
    public void fixNames() {
        for (Item item : protectedDrops.keySet()) {
            if (item != null) {
                item.setCustomName(Strings.makeName(item, item.getItemStack().getAmount(), "", 0));
            }
        }
        protectedDrops.clear();
    }
}
