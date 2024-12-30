package dev.oltsu.bettermanhunts.utils;

import dev.oltsu.bettermanhunts.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Settings {
    private static int headStartSeconds;

    public static void load(Main plugin) {
        File file = new File(plugin.getDataFolder(), "settings.yaml");
        if (!file.exists()) {
            plugin.saveResource("settings.yaml", false);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        headStartSeconds = config.getInt("head-start-seconds", 30);
    }

    public static int getHeadStartSeconds() {
        return headStartSeconds;
    }
}
