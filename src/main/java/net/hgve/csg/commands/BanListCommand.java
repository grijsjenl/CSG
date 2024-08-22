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

import java.util.Map;
import java.util.Set;

public class BanListCommand implements CommandExecutor {

    private final CSG plugin;

    public BanListCommand(CSG plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //todo /banlist <username>
        //returns <reason, duration, command sender>

        if (!(sender.hasPermission("CSG.banlist"))) {
            sender.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        if (!(args.length < 3)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("test_invalid_args"));
            return true;
        }

        OfflinePlayer bannerPlayer = Bukkit.getServer().getOfflinePlayer(args[0]);

        if (!(bannerPlayer.isBanned())) {
            sender.sendMessage(plugin.getMessageManager().getMessage("playerNotBanned"));
            return true;
        }

        if (sender == bannerPlayer) {
            sender.sendMessage(plugin.getMessageManager().getMessage("SelfCommandRestriction"));
            return true;
        }

        BanEntry<PlayerProfile> banEntry = plugin.getServer().getBanList(BanListType.PROFILE).getBanEntry(bannerPlayer.getPlayerProfile());

        String duration = "placeholder";

        int banDurationSec = banEntry.getExpiration().getSeconds();

        if (banDurationSec >= 31557600) {
            duration = String.valueOf(banDurationSec / 31557600) + 'y';
        } else if (banDurationSec >= 86400) {
            duration = String.valueOf(banDurationSec / 86400) + 'd';
        } else if (banDurationSec >= 3600) {
            duration = String.valueOf(banDurationSec / 3600) + 'h';
        } else {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_args"));
        }

        if (args.length == 1) {
            sender.sendMessage(plugin.getMessageManager().getMessage("banlist_entry",
                    Map.of("bannedplayer", banEntry.getBanTarget().getName(),
                            "source", banEntry.getSource(),
                            "duration", duration,
                            "expires", banEntry.getExpiration().toString(),
                            "reason", banEntry.getReason())));
//        } else if (args.length == 0) {
//            Set<BanEntry> bannedPlayerList = plugin.getServer().getBanList(BanListType.PROFILE).getBanEntries();
//
//            sender.sendMessage("e " + bannedPlayerList);
//
//            return true;
        } else {
            sender.sendMessage(plugin.getMessageManager().getMessage("test_command_fail"));
        }
        return true; //maybe false?

        //banlist_entry: "<bannedplayer> banned by <source> for <duration>
        //                expires: <expires>
        //                reason: <reason>"
    }
}
