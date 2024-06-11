package net.hgve.csg.listeners;

import net.hgve.csg.CSG;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final CSG plugin;

    public PlayerQuitListener(CSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        event.setQuitMessage("cya " + event.getPlayer().getName() + ", hope to you see you again!");

        plugin.getTeleportManager().removeQueuedPlayer(event.getPlayer());

    }
}
