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
            if (command.getName().equalsIgnoreCase("updateluckperms") && player.hasPermission("pu.updateluckperms")) {
                try {
                    com.alysaa.ProjectUpdater.spigot.pluginsdl.luckperms.luckpermsDownload();
                    sender.sendMessage(ChatColor.WHITE + "[ProjectUpdater] Updating LuckPerms!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (command.getName().equalsIgnoreCase("updateworldedit") && player.hasPermission("pu.updateworldedit")) {
                try {
                    com.alysaa.ProjectUpdater.spigot.pluginsdl.worldedit.WorldEditDownload();
                    sender.sendMessage(ChatColor.WHITE + "[ProjectUpdater] Updating WorldEdit!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (command.getName().equalsIgnoreCase("updatemcmmo") && player.hasPermission("pu.updatemcmmo")) {
                try {
                    com.alysaa.ProjectUpdater.spigot.pluginsdl.mcmmo.mcmmoDownload();
                    sender.sendMessage(ChatColor.WHITE + "[ProjectUpdater] Updating Mcmmo!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
