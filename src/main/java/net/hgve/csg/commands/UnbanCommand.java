package net.hgve.csg.commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import io.papermc.paper.ban.BanListType;
import net.hgve.csg.CSG;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {

    private final CSG plugin;

    public UnbanCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender.hasPermission("CSG.unban"))) {
            sender.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        if (!(args.length == 1)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_args_ban"));
            return true;
        }

        OfflinePlayer bannedPlayer = Bukkit.getServer().getOfflinePlayer(args[0]);

        if (!bannedPlayer.hasPlayedBefore()) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_player"));
            return true;
        }

        if (!bannedPlayer.isBanned()) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_player"));
            return true;
        }

        BanEntry<PlayerProfile> banEntry = Bukkit.getBanList(BanListType.PROFILE).getBanEntry(bannedPlayer.getPlayerProfile());
        banEntry.remove();

        return true;
    }
}
