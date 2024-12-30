package dev.oltsu.bettermanhunts.manager;

import dev.oltsu.bettermanhunts.Main;
import dev.oltsu.bettermanhunts.utils.Settings;
import dev.oltsu.bettermanhunts.utils.StatsTracker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GameManager {
    private final Main plugin;
    private final Set<UUID> hunters = new HashSet<>();
    private final Set<UUID> speedrunners = new HashSet<>();
    private boolean gameRunning = false;

    public GameManager(Main plugin) {
        this.plugin = plugin;
    }

    public void startGame() {
        if (speedrunners.isEmpty() || hunters.isEmpty()) {
            Bukkit.broadcastMessage(ChatColor.RED + "You need at least one speedrunner and one hunter to start the game!");
            return;
        }
        gameRunning = true;
        Bukkit.broadcastMessage("§aManhunt will start in " + Settings.getHeadStartSeconds() + " seconds!");

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (gameRunning) {
                Bukkit.broadcastMessage("§aManhunt has started! Good luck to all players.");
            }
        }, Settings.getHeadStartSeconds() * 20L);
    }

    public void stopGame(String reason) {
        gameRunning = false;
        hunters.clear();
        speedrunners.clear();
        Bukkit.broadcastMessage("§cManhunt has ended! Reason: " + reason);
    }

    public void addHunter(Player player) {
        hunters.add(player.getUniqueId());
        player.sendMessage("§aYou have been added as a hunter.");
    }

    public void addSpeedrunner(Player player) {
        speedrunners.add(player.getUniqueId());
        player.sendMessage("§aYou are now a speedrunner.");
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public Set<UUID> getSpeedrunners() {
        return speedrunners;
    }
}
