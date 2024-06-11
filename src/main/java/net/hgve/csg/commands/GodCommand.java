package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

    private final CSG plugin;

    public GodCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            plugin.getLogger().info(plugin.getMessageManager().getMessage("console_error"));
            return true;
        }

        Player p = (Player) sender;

        if (!(p.hasPermission("CSG.god"))) {
            p.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        if (p.isInvulnerable()) {
            p.setInvulnerable(false);
            p.sendMessage(ChatColor.RED + "God mode disabled");
        } else {
            p.setInvulnerable(true);
            p.sendMessage(ChatColor.GREEN + "God mode enabled");
        }

        //return super.onCommand(sender, command, label, args);
        return true;
    }
}