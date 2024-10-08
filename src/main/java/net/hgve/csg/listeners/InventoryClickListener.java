package net.hgve.csg.listeners;

import net.hgve.csg.CSG;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private final CSG plugin;

    public InventoryClickListener(CSG plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        // checks
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if (!(plugin.getMenuManager().hasMenuOpen(player))) {
            return;
        }

        if (!(event.getView().getTitle().equals(ChatColor.RED + "CSG gui"))) {
            return;
        }

        if (event.getClick().equals(ClickType.SHIFT_LEFT)) {
            plugin.getLogger().info( event.getClick() + " click - " + event.getSlot());
            event.setCancelled(true);
            return;
        }

        if (event.getClick().equals(ClickType.SHIFT_RIGHT)) {
            plugin.getLogger().info( event.getClick() + " click - " + event.getSlot());
            event.setCancelled(true);
            return;
        }

        if (event.getClick().equals(ClickType.DOUBLE_CLICK)) {
            plugin.getLogger().info( event.getClick() + " click - " + event.getSlot());
            return;
        }

//        if (e.getClickedInventory() == null || !e.getClickedInventory().equals(e.getInventory())) {
//            return;
//        }

        // end of checks

        if (event.isLeftClick()) {

            plugin.getLogger().info("left click - " + event.getSlot());
            event.setCancelled(true);

            if (event.getClickedInventory() == null || !event.getClickedInventory().equals(event.getInventory())) {
                return;
            }

            if (event.getCurrentItem() == null) {
                return;
            }

            switch (event.getCurrentItem().getType()) {

                case BEDROCK:
                    player.sendMessage("test bedrock");
                    player.performCommand("spawn");
                    player.closeInventory();
                    break;
                case RED_BED:
                    player.sendMessage("test red_bed");
                    player.performCommand("home");
                    player.closeInventory();
                    break;
                case EMERALD:
                    player.getInventory().addItem(new ItemStack(Material.EMERALD));
                    player.sendMessage("given emerald");
                    break;
                default:
                    break;
            }
        } else {
            plugin.getLogger().info( event.getClick() + "click - " + event.getSlot());
            event.setCancelled(true);
        }

    }

}