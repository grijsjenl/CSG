package net.hgve.csg.commands;

import net.hgve.csg.CSG;
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

        return true;
    }
}
