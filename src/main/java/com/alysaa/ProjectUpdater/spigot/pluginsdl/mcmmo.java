package com.alysaa.ProjectUpdater.spigot.pluginsdl;

import com.alysaa.ProjectUpdater.spigot.SpigotUpdater;
import org.bukkit.Bukkit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class mcmmo {
    public static void mcmmoDownload() {
        OutputStream os = null;
        InputStream is = null;
        String fileUrl = "https://popicraft.net/jenkins/job/mcMMO/lastSuccessfulBuild/artifact/mcMMO/target/mcMMO.jar";
        String outputPath = ("mcMMO.jar");
        try {
            // create a url object
            URL url = new URL(fileUrl);
            // connection to the file
            URLConnection connection = url.openConnection();
            // get input stream to the file
            is = connection.getInputStream();
            // get output stream to download file
            os = new FileOutputStream(outputPath);
            final byte[] b = new byte[2048];
            int length;
            // read from input stream and write to output stream
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // close streams
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (SpigotUpdater.plugin.getConfig().getBoolean("Auto-Restart-Server")) {
            SpigotUpdater.plugin.getLogger().info("[ProjectUpdater] The Server will restart in 10 seconds!");
            Runnable runnable = () -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
            Bukkit.spigot().restart();
        }
    }
}
