package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class SpawnCommand implements CommandExecutor {

    private final CSG plugin;

    public SpawnCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //Make sure that they are a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("console_error", Map.of("command", "/spawn")));
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("CSG.spawn")) {

            p.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        Location location = plugin.getSpawnManager().getSpawnLocation();

        if (location == null) {
            if (p.hasPermission("CSG.setspawn")) {
                p.sendMessage(plugin.getMessageManager().getMessage("spawn_admin_error"));
            } else {
                p.sendMessage(plugin.getMessageManager().getMessage("spawn_error"));
            }
            return true;
        }

        plugin.getSpawnManager().playerTeleportSpawn(p);

        return true;
    }
}