package com.alysaa.ProjectUpdater.spigot;


import com.alysaa.ProjectUpdater.spigot.command.updateCommand;
import com.alysaa.ProjectUpdater.spigot.pluginsdl.worldedit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public abstract class SpigotUpdater extends JavaPlugin {
    public static SpigotUpdater plugin;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("ProjectUpdater v0.0.1 has been enabled");
        enableCommands();
        enableBooleans();
        createFiles();
        checkConfigVer();
        plugin = this;

    }

    private void enableBooleans() {
        if (getConfig().getBoolean("Auto-Update-ViaVersion")) {
            try {
                Timer StartAutoUpdate;
                StartAutoUpdate = new Timer();
                StartAutoUpdate.schedule(new viaversiontimer(), 0, 100 * 60 * 14400);
                // Auto Update Cycle on Startup and each 24h after startup
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getConfig().getBoolean("Auto-Update-WorldEdit")) {
            try {
                Timer StartAutoUpdate;
                StartAutoUpdate = new Timer();
                StartAutoUpdate.schedule(new worldedittimer(), 0, 100 * 60 * 14400);
                // Auto Update Cycle on Startup and each 24h after startup
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getConfig().getBoolean("Auto-Update-LuckPerms")) {
            try {
                Timer StartAutoUpdate;
                StartAutoUpdate = new Timer();
                StartAutoUpdate.schedule(new luckpermstimer(), 0, 100 * 60 * 14400);
                // Auto Update Cycle on Startup and each 24h after startup
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getConfig().getBoolean("Auto-Update-Mcmmo")) {
            try {
                Timer StartAutoUpdate;
                StartAutoUpdate = new Timer();
                StartAutoUpdate.schedule(new mcmmotimer(), 0, 100 * 60 * 14400);
                // Auto Update Cycle on Startup and each 24h after startup
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void enableCommands() {
        this.getCommand("update").setExecutor(new updateCommand());
    }

    private void checkConfigVer() {
        Logger logger = this.getLogger();
        //Change version number only when editing config.yml!
        if (!(getConfig().getInt("version") == 1)) {
            logger.info("Config.yml is outdated. please regenerate a new config.yml!");
        }
    }

    private void createFiles() {
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        File updateDir = new File("plugins/update");
        if (!updateDir.exists()) {
            try {
                updateDir.mkdirs();
            } catch (Exception ignored) {
            }
        }

    }

    @Override
    public void onDisable() {

    }

    private static class viaversiontimer extends TimerTask {
        @Override
        public void run() {
            com.alysaa.ProjectUpdater.spigot.pluginsdl.viaversion.ViaVersionDownload();
        }
    }

    private static class worldedittimer extends TimerTask {
        @Override
        public void run() {
            com.alysaa.ProjectUpdater.spigot.pluginsdl.worldedit.WorldEditDownload();
        }
    }
    private static class luckpermstimer extends TimerTask {
        @Override
        public void run() {
            com.alysaa.ProjectUpdater.spigot.pluginsdl.luckperms.luckpermsDownload();
        }
    }
    private static class mcmmotimer extends TimerTask {
        @Override
        public void run() {
            com.alysaa.ProjectUpdater.spigot.pluginsdl.mcmmo.mcmmoDownload();
        }
    }
}



