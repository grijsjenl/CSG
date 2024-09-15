package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class FlyCommand implements CommandExecutor {

    private final CSG plugin;

    public FlyCommand(CSG plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("console_error", Map.of("command", "/fly")));
            return true;
        }

        if (!(sender.hasPermission("CSG.fly"))) {
            sender.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        if (args.length > 1) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_args"));
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.AQUA + "Fly disabled");
            } else {
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.AQUA + "Fly enabled");
            }
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getServer().getPlayerExact(args[0]);

            if (target == null) {
                sender.sendMessage(plugin.getMessageManager().getMessage(""));
                return true;
            }

            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                sender.sendMessage("Enabled flying for " + target.getName());
            } else {
                target.setAllowFlight(true);
                sender.sendMessage("Disabled flying for " + target.getName());
            }
            return true;
        }

        sender.sendMessage(plugin.getMessageManager().getMessage("invalid_args"));
        return true;
    }
}
