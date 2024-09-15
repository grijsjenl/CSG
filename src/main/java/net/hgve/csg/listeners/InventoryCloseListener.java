package net.hgve.csg.listeners;

import net.hgve.csg.CSG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    private final CSG plugin;

    public InventoryCloseListener(CSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

        if (!(event.getPlayer() instanceof Player)) {
            return;
        }

        if (!(plugin.getMenuManager().hasMenuOpen((Player) event.getPlayer()))) {
            return;
        }

        if (event.getInventory().equals(plugin.getMenuManager().getPlayerMenu((Player) event.getPlayer()))) {
            plugin.getMenuManager().removeMenu((Player) event.getPlayer());
        }
    }
}
