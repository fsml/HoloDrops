package me.fsml.holodrops.commands;

import me.fsml.holodrops.Main;
import me.fsml.holodrops.util.ConfigReader;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Check implements CommandExecutor {
    
    private String prefix = "" + ChatColor.DARK_RED + ChatColor.BOLD + "HoloDrops ";
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        check(sender);
        return true;
    }
    
    private void check(CommandSender sender) {
        List<Material> mats = ConfigReader.getMissingItems();
        if (mats.size() > 0) {
            sender.sendMessage(prefix + ChatColor.RESET + ChatColor.RED + "Your config is missing:");
            for (Material m : mats) {
                sender.sendMessage(m.toString());
            }
        } else if (mats.size() == 0){
            sender.sendMessage(prefix + ChatColor.RESET + ChatColor.GREEN + "Your config isn't missing anything");
        }
    }
    
}
