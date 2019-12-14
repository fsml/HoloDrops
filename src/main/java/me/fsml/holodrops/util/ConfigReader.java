package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ConfigReader {
    
    public static boolean getBoolean(String option) {
        return Main.m.getConfig().getBoolean(option);
    }
    
    public static String getString(String option) {
        return Main.m.getConfig().getString(option);
    }
    
    public static List<String> getStringList(String option) {
        return Main.m.getConfig().getStringList(option);
    }
    
    public static int getInt(String option) {
        return Main.m.getConfig().getInt(option);
    }
    
    public static List<Material> getMissingItems() {
        ArrayList<Material> configMats = new ArrayList<Material>();
        for (String configMaterial : Main.m.getConfig().getConfigurationSection("item-names").getKeys(false)) {
            configMats.add(Material.getMaterial(configMaterial));
        }
        for (Material m : Material.values()) {
            if (!configMats.contains(m)) {
                configMats.add(m);
            } else {
                configMats.remove(m);
            }
        }
        return configMats;
    }
    
}
