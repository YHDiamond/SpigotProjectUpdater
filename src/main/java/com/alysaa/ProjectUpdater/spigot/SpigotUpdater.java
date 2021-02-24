package com.alysaa.ProjectUpdater.spigot;


import com.alysaa.ProjectUpdater.spigot.command.updateCommand;
import com.alysaa.ProjectUpdater.spigot.pluginsdl.luckperms;
import com.alysaa.ProjectUpdater.spigot.pluginsdl.mcmmo;
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



public class SpigotUpdater extends JavaPlugin {
    public static SpigotUpdater plugin;
    @Override
    public void onEnable() {

        getLogger().info("ProjectUpdater v0.0.1 has been enabled");
        enableCommands();
        enableUpdateCycle();
        createFiles();
        checkConfigVer();
        plugin = this;

    }

    private void enableUpdateCycle() {
        Timer StartAutoUpdate;
        StartAutoUpdate = new Timer();
        StartAutoUpdate.schedule(new updatetimer(), 0, 100 * 60 * 14400);
        // Auto Update Cycle on Startup and each 24h after startup
    }

    private void enableCommands() {
        this.getCommand("updateviaversion").setExecutor(new updateCommand());
        this.getCommand("updateworldedit").setExecutor(new updateCommand());
        this.getCommand("updatemcmmo").setExecutor(new updateCommand());
        this.getCommand("updateluckperms").setExecutor(new updateCommand());
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
        FileConfiguration config = new YamlConfiguration();
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

    private static class updatetimer extends TimerTask {
        @Override
        public void run() {
            if (new SpigotUpdater().getConfig().getBoolean("Auto-Update-WorldEdit")) {
                try {
                    worldedit.WorldEditDownload();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (new SpigotUpdater().getConfig().getBoolean("Auto-Update-LuckPerms")) {
                try {
                    luckperms.luckpermsDownload();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (new SpigotUpdater().getConfig().getBoolean("Auto-Update-Mcmmo")) {
                try {
                    mcmmo.mcmmoDownload();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


