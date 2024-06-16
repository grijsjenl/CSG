package net.hgve.csg.commands;

import net.hgve.csg.CSG;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryTestCommand implements CommandExecutor {

    private final CSG plugin;

    public InventoryTestCommand(CSG plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            plugin.getComponentLogger().info(plugin.getMessageManager().getMessage("console_error"));
            return true;
        }

        Player player = (Player) sender;

        ItemStack flower = new ItemStack(Material.FLOWERING_AZALEA, 2);
        player.getInventory().setItem(1, flower);

        ItemStack food = new ItemStack(Material.BEEF, 7);
        ItemMeta itemMeta = food.getItemMeta();

        itemMeta.setDisplayName(ChatColor.RED + "Beef-t");

        List<String> foodLore = new ArrayList<>();
        foodLore.add("Test1");
        foodLore.add("Test2");
        foodLore.add("Test4");

        itemMeta.setLore(foodLore);

        itemMeta.addEnchant(Enchantment.KNOCKBACK, 10, true);

        food.setItemMeta(itemMeta);
        player.getInventory().addItem(food);

        return true;
    }
}
