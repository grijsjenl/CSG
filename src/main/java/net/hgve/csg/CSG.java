package net.hgve.csg;

import net.hgve.csg.commands.*;
import net.hgve.csg.listeners.*;
import net.hgve.csg.managers.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class CSG extends JavaPlugin {

    private MessageManager messageManager;
    private SpawnManager spawnManager;
    private TeleportManager teleportManager;
    private HomeManager homeManager;
    private MenuManager menuManager;

    @Override
    public void onEnable() {
        // Plugin startup login

        this.getLogger().info("CSG is starting");
        this.saveDefaultConfig();

        this.messageManager = new MessageManager(this);
        this.spawnManager = new SpawnManager(this);
        this.teleportManager = new TeleportManager(this);
        this.homeManager = new HomeManager(this);
        this.menuManager = new MenuManager(this);

        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(this), this);
        this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(this), this);

        this.getCommand("feed").setExecutor(new FeedCommand(this));
        this.getCommand("god").setExecutor(new GodCommand(this));
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        this.getCommand("spawn").setExecutor(new SpawnCommand(this));
        this.getCommand("test").setExecutor(new TestCommand(this));
        this.getCommand("die").setExecutor(new DieCommand(this));
        this.getCommand("home").setExecutor(new HomeCommand(this));
        this.getCommand("sethome").setExecutor(new SetHomeCommand(this));
        this.getCommand("invtest").setExecutor(new InventoryTestCommand(this));
        this.getCommand("menu").setExecutor(new MenuCommand(this));
        this.getCommand("ban").setExecutor(new BanCommand(this));
        this.getCommand("unban").setExecutor(new UnbanCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.getLogger().info("CSG is stopping");
    }

    public SpawnManager getSpawnManager() {
        return this.spawnManager;
    }
    public MessageManager getMessageManager() {
        return this.messageManager;
    }
    public TeleportManager getTeleportManager() {
        return this.teleportManager;
    }
    public HomeManager getHomeManager() {
        return this.homeManager;
    }
    public MenuManager getMenuManager() {
        return this.menuManager;
    }
}
