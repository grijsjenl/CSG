package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import net.hgve.csg.events.BanEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class BanCommand implements CommandExecutor {

    private final CSG plugin;

    public BanCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //: /ban <player> <time> <reason>
        //: /ban NotZerek 10d Meneerple bought this username to hack on f64

        //: /ban arg[0] arg[1] else
        // confirm permission
        // confirm argument count
        // confirm player has played before
        // confirm time, if not skip
        // CHECKS ------------------------------------------------------------------------------------------------------

        if (!(sender.hasPermission("CSG.ban"))) {
            sender.sendMessage(plugin.getMessageManager().getMessage("permission_error"));
            return true;
        }

        if (!(args.length >= 3)) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_args_ban"));
            return true;
        }

        OfflinePlayer accusedPlayer = Bukkit.getServer().getOfflinePlayer(args[0]);

        if (!accusedPlayer.hasPlayedBefore()) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_player"));
            return true;
        }

        if (!this.isValidDuration(args[1])) {
            sender.sendMessage(plugin.getMessageManager().getMessage("invalid_duration"));
            return true;
        }

        // END OF CHECKS -----------------------------------------------------------------------------------------------

        // EXECUTION ---------------------------------------------------------------------------------------------------

        String reason = Arrays.stream(args).skip(2).collect(Collectors.joining(" "));

        int durationSec = convertToSeconds(args[1]);

        accusedPlayer.ban(reason, Duration.ofSeconds(durationSec), sender.getName());
        BanEvent banEvent = new BanEvent(durationSec, accusedPlayer, sender, reason);

        // END OF EXECUTION --------------------------------------------------------------------------------------------

        return true;

    }


    private int convertToSeconds(String time) {

        if (Objects.equals(time, "perm")) {
            return -1;
        }

        int i = Integer.parseInt(time.substring(0, time.length() - 1));

        switch (time.charAt(time.length() -1)) {

            case 'y':
                return i*365*24*60*60;

            case 'd':
                return i*24*60*60;

            case 'h':
                return i*60*60;

        }

        plugin.getLogger().warning("Ban failed could not convert to seconds");
        return 0;

    }

    private boolean isValidDuration(String time) {

        if (Objects.equals(time, "perm")) {
            return true;
        } else {

            // Regular expression to match the format: integer followed by "y", "d", or "h"
            // Return true if the time matches the regex pattern
            return time.matches("\\d+(y|d|h)");

        }
    }



}