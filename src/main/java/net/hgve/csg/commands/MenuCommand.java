package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MenuCommand implements CommandExecutor {

    private final CSG plugin;

    public MenuCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            plugin.getComponentLogger().info(plugin.getMessageManager().getMessage("console_error"));
            return true;
        }

        Player p = (Player) sender;

        //todo add permissions

        //9, 18, 27, 36, 45, 54
        Inventory menu = Bukkit.createInventory(p, 54, ChatColor.RED + "CSG gui");

        plugin.getMenuManager().addMenu(p, menu);

        //Buttons
        ItemStack spawn = new ItemStack(Material.BEDROCK);
        ItemStack home = new ItemStack(Material.RED_BED);
        ItemStack itemTest = new ItemStack(Material.EMERALD);

        //metadata
        ItemMeta spawnMeta = spawn.getItemMeta();
        ItemMeta homeMeta = home.getItemMeta();
        ItemMeta itemTestMeta = itemTest.getItemMeta();

        spawnMeta.setDisplayName(ChatColor.BLUE + "spawn");
        spawnMeta.setLore(List.of(ChatColor.GOLD + "Teleports you to spawn"));
        spawn.setItemMeta(spawnMeta);

        homeMeta.setDisplayName(ChatColor.GREEN + "home");
        homeMeta.setLore(List.of(ChatColor.GOLD + "Teleports you to your home"));
        home.setItemMeta(homeMeta);

        itemTestMeta.setDisplayName(ChatColor.RED + "Test");
        itemTestMeta.setLore(List.of(ChatColor.GOLD + "Tests if the plugin can give you an item"));
        itemTest.setItemMeta(itemTestMeta);

        //Add buttons to menu
        menu.setItem(19, spawn);
        menu.setItem(22, home);
        menu.setItem(25, itemTest);

        p.openInventory(menu);

        return true;
    }
}
