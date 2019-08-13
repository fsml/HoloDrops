package me.fsml.holodrops.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class Glow {
    
    public static ArrayList<Team> teams = new ArrayList<Team>();
    public static Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    
    public static void setGlowColor(ChatColor color, Entity entity) {
        String name = "HD" + color.getChar();
        Team team = scoreboard.getTeam(name);
        if (team == null) {
            team = scoreboard.registerNewTeam(name);
            teams.add(team);
        }
        team.setColor(color);
        team.addEntry(entity.getUniqueId().toString());
    }
    
    public static ChatColor getColor(String string) {
        string = Strings.color(string);
        String color = ChatColor.getLastColors(string);
        if (color.length() == 0) color = ChatColor.COLOR_CHAR + "f";
        return ChatColor.getByChar(color.charAt(1));
    }
    
    public static void unregister() {
        for (Team team : teams) {
            team.unregister();
        }
    }

}
