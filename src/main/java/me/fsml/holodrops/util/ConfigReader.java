package me.fsml.holodrops.util;

import me.fsml.holodrops.Main;

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
    
    
}
