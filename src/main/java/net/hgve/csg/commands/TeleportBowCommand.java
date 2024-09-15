package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class TeleportBowCommand implements CommandExecutor {

    private final CSG plugin;

    public TeleportBowCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("console_error", Map.of("command", "TeleportBow")));
        }

        return false;
    }
}
