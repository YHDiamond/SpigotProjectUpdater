package com.alysaa.ProjectUpdater.spigot.utils;

import com.alysaa.ProjectUpdater.spigot.SpigotUpdater;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class getSpigotVersion {
    public static String getVersionViaVersion() {

        try (InputStream inputStream = new URL("https://api.spigotmc.org/simple/0.1/index.php?action=getResource&id=19254").openStream(); Scanner scanner = new Scanner(inputStream)) {
            String total = "";
            while (scanner.hasNext()) {
                total += scanner.next();
            }
            JsonObject jsonObject = new JsonParser().parse(total).getAsJsonObject();
            String version = jsonObject.get("current_version").getAsString();
            return version;
        } catch (IOException exception) {
            SpigotUpdater.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
            return null;
        }
    }
}
