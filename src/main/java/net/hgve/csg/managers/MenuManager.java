package net.hgve.csg.managers;

import net.hgve.csg.CSG;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {

    private final CSG plugin;
    private Map<Player, Inventory> menuMap = new HashMap<>();

    public MenuManager(CSG plugin) {
        this.plugin = plugin;
    }

    public void addMenu(Player player, Inventory inv) {

        this.menuMap.put(player, inv);

    }

    public void removeMenu(Player player) {

        this.menuMap.remove(player);

    }

    public boolean hasMenuOpen(Player player) {

        return this.menuMap.containsKey(player);

    }

    public Inventory getPlayerMenu(Player player) {

        return this.menuMap.get(player);

    }

}
