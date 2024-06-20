package net.hgve.csg.listeners;

import net.hgve.csg.CSG;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;

public class PlayerJoinListener implements Listener  {

    private final CSG plugin;

    public PlayerJoinListener(CSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.joinMessage(plugin.getMessageManager().getMessage("join_message", Map.of("player", event.getPlayer().getName())));

    }

}
