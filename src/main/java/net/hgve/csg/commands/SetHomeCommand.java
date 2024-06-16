package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetHomeCommand implements CommandExecutor {

    private final CSG plugin;

    public SetHomeCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            plugin.getComponentLogger().info(plugin.getMessageManager().getMessage("console_error"));
            return true;
        }

        Player p = (Player) sender;

        if (!(p.hasPermission("CSG.sethome"))) {
            p.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        Location location = p.getLocation();
        location.setX(location.getBlockX() + 0.5);
        location.setZ(location.getBlockZ() + 0.5);

        try {
            plugin.getHomeManager().setHome(p, location);
            p.sendMessage(plugin.getMessageManager().getMessage("sethome_success"));
        } catch (IOException e) {
            p.sendMessage(plugin.getMessageManager().getMessage("sethome_fail"));
            throw new RuntimeException(e);
        }

        return true;
    }
}
