package net.hgve.csg.listeners;

import net.hgve.csg.CSG;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class PlayerQuitListener implements Listener {

    private final CSG plugin;

    public PlayerQuitListener(CSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        event.quitMessage(plugin.getMessageManager().getMessage("quit_message", Map.of("player", event.getPlayer().getName())));

        plugin.getTeleportManager().removeQueuedPlayer(event.getPlayer());

    }
}
