package net.hgve.csg.managers;

import net.hgve.csg.CSG;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomeManager {

    private final CSG plugin;
    private Map<Player, Location> homeMap = new HashMap();
    private FileConfiguration homesYml;
    private File homesFile;

    public HomeManager(CSG plugin) {
        this.plugin = plugin;
        this.init();
    }

    private void init() {

        if (!new File(plugin.getDataFolder(), "homes.yml").exists()) {
            plugin.saveResource("homes.yml", false);
        }

        homesFile = new File(plugin.getDataFolder() + File.separator + "homes.yml");
        this.homesYml = YamlConfiguration.loadConfiguration(homesFile);

        this.homesYml.getKeys(false).forEach(p -> {
            Location home = this.homesYml.getLocation(p);
            if (home != null) this.homeMap.put(Bukkit.getPlayerExact(p), home);
            else plugin.getServer().getLogger().warning("Home for key '" + p + "' in homes.yml is missing and will be skipped.");
        });

    }

    public void setHome(Player p, Location location) throws IOException {

        // maybe check if it is safe.

        this.homesYml.set(p.getName(), location);
        this.homeMap.put(p, location);
        homesYml.save(homesFile);

    }

    public Location getHome(Player p) {
        return this.homeMap.get(p);
    }

    public void teleportPlayerHome(Player p) {

        plugin.getTeleportManager().queuePlayerTeleport(p, getHome(p), "your home");

    }
}
