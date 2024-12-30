package dev.oltsu.bettermanhunts.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.oltsu.bettermanhunts.Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StatsTracker {
    private static final Gson gson = new Gson();
    private static JsonObject stats;

    public static void load(Main plugin) {
        File file = new File(plugin.getDataFolder(), "stats.json");
        if (!file.exists()) {
            stats = new JsonObject();
            stats.addProperty("wins", 0);
            stats.addProperty("losses", 0);
            save(plugin);
        } else {
            try (FileReader reader = new FileReader(file)) {
                stats = gson.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save(Main plugin) {
        File file = new File(plugin.getDataFolder(), "stats.json");
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(stats, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void incrementWins() {
        stats.addProperty("wins", stats.get("wins").getAsInt() + 1);
    }

    public static void incrementLosses() {
        stats.addProperty("losses", stats.get("losses").getAsInt() + 1);
    }
}
