package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    private final CSG plugin;

    public TestCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (!(sender instanceof Player)) {
            plugin.getComponentLogger().info(plugin.getMessageManager().getMessage("console_error"));
            return true;
        }

        Player p = (Player) sender;

        if (p.hasPermission("permissions.test")) {

            p.sendMessage(plugin.getMessageManager().getMessage("permission"));

        } else {

            p.sendMessage(plugin.getMessageManager().getMessage("permission_error"));

        }

        return true;
    }
}