package com.alysaa.ProjectUpdater.spigot.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class updateCommand implements CommandExecutor {
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equalsIgnoreCase("updateviaversion") && player.hasPermission("pu.updateviaversion")) {
                try {
                    com.alysaa.ProjectUpdater.spigot.pluginsdl.viaversion.ViaVersionDownload();
                    sender.sendMessage(ChatColor.WHITE + "[ProjectUpdater] Updating ViaVersion!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
