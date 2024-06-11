package net.hgve.csg.listeners;

import net.hgve.csg.CSG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final CSG plugin;

    public PlayerMoveListener(CSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if (plugin.getTeleportManager().isPlayerQueued(event.getPlayer())) {

            if (hasPlayerMoved(event.getPlayer())) {
                plugin.getTeleportManager().removeQueuedPlayer(event.getPlayer());
            }
        }
    }

    private boolean hasPlayerMoved(Player p) {

        if (p.getLocation().getBlockX() != plugin.getTeleportManager().getOrginalLocation(p).getBlockX()) {
            return true;
        }

        if (p.getLocation().getBlockZ() != plugin.getTeleportManager().getOrginalLocation(p).getBlockZ()) {
            return true;
        }

        if (p.getLocation().getBlockY() != plugin.getTeleportManager().getOrginalLocation(p).getBlockY()) {
            return true;
        }

        return false;
    }
}
