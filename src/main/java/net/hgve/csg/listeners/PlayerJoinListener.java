package net.hgve.csg.listeners;

import net.hgve.csg.CSG;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener  {

    private final CSG plugin;

    public PlayerJoinListener(CSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        event.setJoinMessage("Welcome " + event.getPlayer().getName() + " to the building server!");

    }
}
