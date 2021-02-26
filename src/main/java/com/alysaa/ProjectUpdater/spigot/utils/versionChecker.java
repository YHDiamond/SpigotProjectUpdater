package com.alysaa.ProjectUpdater.spigot.utils;

import com.alysaa.ProjectUpdater.spigot.SpigotUpdater;
import com.alysaa.ProjectUpdater.spigot.pluginsdl.viaversion;

import java.util.logging.Logger;

import static org.bukkit.Bukkit.getPluginManager;

public class versionChecker {
    public static void CheckVersionViaVersion(){

        Logger logger = SpigotUpdater.plugin.getLogger();
       String pluginVersion = getPluginManager().getPlugin("ViaVersion").getDescription().getVersion();
        Runnable runnable = () -> {
            String version = getSpigotVersion.getVersionViaVersion();
            if (version.equals(pluginVersion)) {
                logger.info("There are no new updates for GeyserUpdater available.");
            } else {
                viaversion.ViaVersionDownload();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
