/*

your plugin has a spawn command to change the spawn, so you need to update the location when setspawn is used

storage data is not a map it is a location, the data is just the spawn location

when players run /spawn the plugin would use the spawn location from spawnManager

when players run /setspawn the plugin would update the config.yml file and the spawn location in spawnManager

init function in this manager is used even when the server is live

*/

package net.hgve.csg.managers;

import net.hgve.csg.CSG;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnManager {

    private final CSG plugin;

    Location spawnLocation;
    //Map<Player, Location> queuedTeleports = new HashMap<>(); when you want to use multiple locations

    /*
    listener for player movement, player leaves otherwise console error
    when a player uses the /spawn command there should be a timer for 5 seconds (100 ticks) before the player gets teleported.
    Also when the player moves the teleport should be cancelled
     */

    public SpawnManager(CSG plugin) {
        this.plugin = plugin;
        this.init();
    }

    private void init() {

        //ConfigurationSection spawnYml = plugin.getConfig().getConfigurationSection("spawn");
        this.spawnLocation = plugin.getConfig().getLocation("spawn");

        if (spawnLocation != null) {
            plugin.getServer().getWorld(spawnLocation.getWorld().getUID()).setSpawnLocation(this.spawnLocation);
        }
    }

    public void setSpawnLocation(Location location) {

        // maybe check if it is safe.

        plugin.getConfig().set("spawn", location);
        plugin.saveConfig();

        this.spawnLocation = location;

        //this.init(); would make init read the spawn from the config again

    }

    public Location getSpawnLocation() {

        return this.spawnLocation;

    }

    public void playerTeleportSpawn(Player p) {

        plugin.getTeleportManager().queuePlayerTeleport(p, spawnLocation, "the spawn");

    }
}
