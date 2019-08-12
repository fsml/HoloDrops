package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;

import java.util.HashMap;
import java.util.List;

public class Settings {
    
    private HashMap<String, Boolean> settings;
    private HashMap<String, String> format;
    private HashMap<String, String> names;
    private List<String> enabledWorlds;
    
    public void initialize() {
        settings = new HashMap<String, Boolean>();
        format = new HashMap<String, String>();
        names = new HashMap<String, String>();
        enabledWorlds = ConfigReader.getStringList("enabled-worlds");
        
        settings.put("item-frame-holos", ConfigReader.getBoolean("item-frame-holos"));
        settings.put("custom-names-only", ConfigReader.getBoolean("custom-names-only"));
        
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
    
    public String getNameFromMat(String material) {
        if (names.containsKey(material)) {
            return names.get(material);
        } else {
            return "";
        }
    }
    
}
