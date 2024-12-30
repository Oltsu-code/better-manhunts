package dev.oltsu.bettermanhunts.cmd;

import dev.oltsu.bettermanhunts.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Manhunt implements CommandExecutor {
    private final GameManager gameManager;

    public Manhunt(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cOnly players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            sender.sendMessage("§cUsage: /manhunt <start|stop|addhunter|addspeedrunner>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "start":
                gameManager.startGame();
                break;
            case "stop":
                gameManager.stopGame("Manually stopped by " + sender.getName());
                break;
            case "addhunter":
                if (args.length < 2) {
                    sender.sendMessage("§cSpecify a player to add as a hunter.");
                    return true;
                }
                Player hunter = Bukkit.getPlayer(args[1]);
                if (hunter != null) {
                    gameManager.addHunter(hunter);
                } else {
                    sender.sendMessage("§cPlayer not found!");
                }
                break;
            case "addspeedrunner":
                if (args.length < 2) {
                    sender.sendMessage("§cSpecify a player to add as a speedrunner.");
                    return true;
                }
                Player speedrunner = Bukkit.getPlayer(args[1]);
                if (speedrunner != null) {
                    gameManager.addSpeedrunner(speedrunner);
                } else {
                    sender.sendMessage("§cPlayer not found!");
                }
                break;
            default:
                sender.sendMessage("§cInvalid command!");
        }
        return true;
    }
}
