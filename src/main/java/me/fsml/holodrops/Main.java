package me.fsml.holodrops;

import me.fsml.holodrops.commands.Reload;
import me.fsml.holodrops.listeners.ItemDropListener;
import me.fsml.holodrops.listeners.ItemFrameClickListener;
import me.fsml.holodrops.listeners.ItemMergeListener;
import me.fsml.holodrops.listeners.ItemPickupListener;
import me.fsml.holodrops.listeners.protection.BlockDropListener;
import me.fsml.holodrops.util.Glow;
import me.fsml.holodrops.util.Settings;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    
    public static Main m;
    public Settings settings;
    
    @Override
    public void onEnable() {
        saveDefaultConfig();
        m = this;
        settings = new Settings();
        settings.initialize();
        getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
        getServer().getPluginManager().registerEvents(new ItemMergeListener(), this);
        getServer().getPluginManager().registerEvents(new ItemFrameClickListener(), this);
        getServer().getPluginManager().registerEvents(new ItemPickupListener(), this);
        getServer().getPluginManager().registerEvents(new BlockDropListener(), this);
        getCommand("hdreload").setExecutor(new Reload());
    
    }
    
    public void onDisable() {
        try {
            Glow.unregister();
        } catch (NoClassDefFoundError error) {
            // no items were set to glow... this try/catch block is to prevent console spam
        }
        settings.fixNames();
    }
}
