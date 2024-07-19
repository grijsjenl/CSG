package net.hgve.csg.managers;

import net.hgve.csg.CSG;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class TeleportManager {

    private final CSG plugin;

    Map<Player, Location> queuedPlayerTeleport = new HashMap();

    public TeleportManager(CSG plugin) {
        this.plugin = plugin;
    }

    public void queuePlayerTeleport(Player p, Location destination, String destinationName) {

        this.queuedPlayerTeleport.put(p, p.getLocation());

        p.sendMessage(plugin.getMessageManager().getMessage("tpTimer"));

        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {

            if (queuedPlayerTeleport.containsKey(p)) {

                p.teleport(destination);
                p.sendMessage(plugin.getMessageManager().getMessage("teleport_success", Map.of("destination", destinationName)));
                this.queuedPlayerTeleport.remove(p);

            }

            //run after 100 ticks;
        }, 100L);

    }

    public void removeQueuedPlayer(Player p) {

        this.queuedPlayerTeleport.remove(p);
        if (p.isOnline()) {
            p.sendMessage(plugin.getMessageManager().getMessage("teleport_fail")); //this is also the same as in the queuedPlayerTeleport check
        }
    }

    public boolean isPlayerQueued(Player p) {

        return this.queuedPlayerTeleport.containsKey(p);

    }

    public Location getOrginalLocation(Player p) {

        return this.queuedPlayerTeleport.get(p);

    }

}
