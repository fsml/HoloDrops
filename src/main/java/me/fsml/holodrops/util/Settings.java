package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;

import java.util.HashMap;
import java.util.List;

public class Settings {
    
    private HashMap<String, Boolean> settings = new HashMap<String, Boolean>();
    private HashMap<String, String> format = new HashMap<String, String>();
    private HashMap<String, String> names = new HashMap<String, String>();
    private List<String> enabledWorlds;
    private List<String> blacklist;
    
    public void initialize() {
        Main.m.reloadConfig();
        settings.clear();
        format.clear();
        names.clear();
        enabledWorlds = ConfigReader.getStringList("enabled-worlds");
        blacklist = ConfigReader.getStringList("blacklist");
        
        settings.put("item-frame-holos", ConfigReader.getBoolean("item-frame-holos"));
        settings.put("custom-names-only", ConfigReader.getBoolean("custom-names-only"));
        settings.put("single-stack", ConfigReader.getBoolean("single-stack"));
        
        format.put("stack-count", Strings.color(ConfigReader.getString("stack-count")));
        format.put("holo-prefix", Strings.color(ConfigReader.getString("holo-prefix")));
        format.put("holo-suffix", Strings.color(ConfigReader.getString("holo-suffix")));
        format.put("holo-format", Strings.color(ConfigReader.getString("holo-format")));
        
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
    
    public boolean isWorldEnabled(String world) {
        return enabledWorlds.contains(world);
    }
    
    public boolean isBlacklisted(String name) {
        for (String s : blacklist) {
            if (Strings.stripColor(s).equals(Strings.stripColor(name)))
                return true;
        }
        return false;
    }
    
    public String getNameFromMat(String material) {
        if (names.containsKey(material)) {
            return names.get(material);
        } else {
            return "";
        }
    }
    
}
