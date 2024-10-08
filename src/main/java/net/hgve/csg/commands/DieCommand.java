package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class DieCommand implements CommandExecutor {

    private final CSG plugin;

    public DieCommand(CSG plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("console_error", Map.of("command", "/die")));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("CSG.die")) {
            p.setHealth(0.0);
            p.sendMessage(ChatColor.RED + "You did /die");
        } else {
            p.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
        }

        return true;
    }
}
