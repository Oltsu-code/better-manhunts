package dev.oltsu.bettermanhunts.event;

import dev.oltsu.bettermanhunts.manager.GameManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class TrackerListener implements Listener {
    private final GameManager gameManager;

    public TrackerListener(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerUseCompass(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!gameManager.isGameRunning()) return;

        if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
            // Simplified tracking logic
            Player target = gameManager.getSpeedrunners().stream()
                    .map(uuid -> player.getServer().getPlayer(uuid))
                    .filter(p -> p != null)
                    .findFirst()
                    .orElse(null);

            if (target != null) {
                player.setCompassTarget(target.getLocation());
                player.sendMessage("§aCompass is now tracking " + target.getName());
            } else {
                player.sendMessage("§cNo target available to track.");
            }
        }
    }
}
