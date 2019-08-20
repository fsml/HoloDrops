package me.fsml.holodrops.commands;

import me.fsml.holodrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    
    private String prefix = "" + ChatColor.DARK_RED + ChatColor.BOLD + "HoloDrops | ";
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        reload(sender);
        return true;
    }
    
    private void reload(CommandSender sender) {
        Main.m.settings.initialize();
        sender.sendMessage(prefix + ChatColor.RESET + ChatColor.GREEN + "Successfully reloaded the config");
    }
    
}
