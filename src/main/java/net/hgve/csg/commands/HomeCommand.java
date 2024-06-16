package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    private final CSG plugin;

    public HomeCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            plugin.getComponentLogger().info(plugin.getMessageManager().getMessage("console_error"));
            return true;
        }

        Player p = (Player) sender;

        if (!(p.hasPermission("CSG.home"))) {
            p.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        Location location = plugin.getHomeManager().getHome(p);

        if (location == null) {
            p.sendMessage(plugin.getMessageManager().getMessage("home_error"));
            return true;
        }

        plugin.getHomeManager().teleportPlayerHome(p);

        return true;
    }
}
