package me.fsml.holodrops.commands;

import me.fsml.holodrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    
    private String prefix = "" + ChatColor.DARK_RED + ChatColor.BOLD + "HoloDrops | ";
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hdreload")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("holodrops.reload")) {
                    reload(sender);
                } else {
                    p.sendMessage(prefix + ChatColor.RESET + ChatColor.RED + "You lack the permission: holodrops.reload");
                }
            } else {
                reload(sender);
            }
        }
        return true;
    }
    
    private void reload(CommandSender sender) {
        Main.m.settings.initialize();
        sender.sendMessage(prefix + ChatColor.RESET + ChatColor.GREEN + "Successfully reloaded the config");
    }
    
}
