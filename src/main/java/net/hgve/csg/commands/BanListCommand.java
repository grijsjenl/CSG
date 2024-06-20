package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BanListCommand implements CommandExecutor {

    private final CSG plugin;

    public BanListCommand(CSG plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //todo /banlist <username>
        //returns <reason, duration, command sender>



        return false;
    }
}
