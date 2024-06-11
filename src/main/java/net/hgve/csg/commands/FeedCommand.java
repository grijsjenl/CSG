package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import javax.swing.text.StyledEditorKit;

public class FeedCommand implements CommandExecutor {

    private final CSG plugin;

    public FeedCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        boolean isSenderConsole;

        if (sender instanceof Player) {
            isSenderConsole = false;
        } else if (sender instanceof ConsoleCommandSender) {
            isSenderConsole = true;
        } else {
            sender.sendMessage(plugin.getMessageManager().getMessage("general_error"));
            return true;
        }

        if (!(sender.hasPermission("CSG.feed"))) {
            sender.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        // Console but invalid args or player but invalid args
        if ((isSenderConsole && args.length != 1) || (!isSenderConsole && args.length > 1)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_args"));
            return true;
        }

        // ternary - value = boolean condition (like if condition) ? set value if true : set value if false
        Player hungryPlayer = args.length == 1 ? Bukkit.getServer().getPlayerExact(args[0]) : (Player) sender;

//        the code above is the same as below
//        if (args.length == 1) {
//            hungryPlayer = Bukkit.getServer().getPlayerExact(args[0]);
//        } else {
//            hungryPlayer = (Player) sender;
//        }

        if (hungryPlayer == null) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_player"));
            return true;
        }

        hungryPlayer.setFoodLevel(20);

        if (hungryPlayer.equals(sender)) {
            hungryPlayer.sendMessage(ChatColor.YELLOW + "Set food to max");
        } else {
            hungryPlayer.sendMessage(ChatColor.YELLOW + sender.getName() + " fed you");
            sender.sendMessage(ChatColor.YELLOW + "you fed " + hungryPlayer.getName());
        }

        plugin.getLogger().info(sender.getName() + " fed " + hungryPlayer.getName());
        return true;
    }
}