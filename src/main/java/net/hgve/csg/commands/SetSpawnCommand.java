package  net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final CSG plugin;

    public SetSpawnCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            plugin.getLogger().info(plugin.getMessageManager().getMessage("console_error"));
            return true;
        }

        Player p = (Player) sender;

        if (!(p.hasPermission("CSG.setspawn"))) {
            p.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        Location location = p.getLocation();
        location.setX(location.getBlockX() + 0.5);
        location.setZ(location.getBlockZ() + 0.5);

        plugin.getSpawnManager().setSpawnLocation(location);

        p.sendMessage(plugin.getMessageManager().getMessage("setspawn_success"));

        return true;
    }
}