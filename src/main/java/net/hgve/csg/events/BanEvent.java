package net.hgve.csg.events;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BanEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();
    private final int duration;

    private final OfflinePlayer bannedPlayer;
    private final CommandSender commandSender;
    private final String reason;

    public BanEvent(int duration, OfflinePlayer bannedPlayer, CommandSender commandSender, String reason) {
        this.duration = duration;
        this.bannedPlayer = bannedPlayer;
        this.commandSender = commandSender;
        this.reason = reason;
    }

    @Override
    public HandlerList getHandlers() {
        return this.handlerList;
    }

    public int getDuration() {
        return this.duration;
    }
    public OfflinePlayer getBannedPlayer() {
        return this.bannedPlayer;
    }
    public CommandSender getCommandSender() {
        return this.commandSender;
    }
    public String getReason() {
        return this.reason;
    }
}
