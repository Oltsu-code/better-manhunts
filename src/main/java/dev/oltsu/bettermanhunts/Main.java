package dev.oltsu.bettermanhunts;

import dev.oltsu.bettermanhunts.cmd.Manhunt;
import dev.oltsu.bettermanhunts.event.TrackerListener;
import dev.oltsu.bettermanhunts.manager.GameManager;
import dev.oltsu.bettermanhunts.utils.Settings;
import dev.oltsu.bettermanhunts.utils.StatsTracker;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private GameManager gameManager;

    @Override
    public void onEnable() {
        getLogger().info("Manhunt Plugin Enabled!");

        Settings.load(this);
        StatsTracker.load(this);

        gameManager = new GameManager(this);

        // Register commands
        this.getCommand("manhunt").setExecutor(new Manhunt(gameManager));

        // Register events
        getServer().getPluginManager().registerEvents(new TrackerListener(gameManager), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Manhunt Plugin Disabled!");
        StatsTracker.save(this);
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
