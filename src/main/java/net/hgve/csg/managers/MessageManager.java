package net.hgve.csg.managers;

import net.hgve.csg.CSG;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageManager {

    private final CSG plugin;

    private final Map<String, String> messages = new HashMap();

    public MessageManager(CSG plugin) {
        this.plugin = plugin;
        this.init();
    }

    private void init() {

        if (!new File(plugin.getDataFolder(), "messages.yml").exists()) {
            plugin.saveResource("messages.yml", false);
        }

        File file = new File(plugin.getDataFolder() + File.separator + "messages.yml");
        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

        fileConfig.getKeys(false).forEach(m -> {
            String message = fileConfig.getString(m);
            if (message != null) this.messages.put(m, message);
            else plugin.getServer().getLogger().warning("Message for key '" + m + "' in messages.yml is missing and will be skipped.");
        });

        //ConfigurationSection messagesYml = plugin.getConfig().getConfigurationSection("messages");
        //messagesYml.getKeys(false).forEach(key -> messages.put(key, messagesYml.getString(key)));

    }

    public Component getMessage(String key) {
        return MiniMessage.miniMessage().deserialize(this.messages.get(key));
    }

    public Component getMessage(String  key, Map<String, String> placeholders) {
        List<TagResolver> rs = placeholders
                .entrySet()
                .stream()
                .map(entry -> (TagResolver) Placeholder.parsed(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return MiniMessage.miniMessage().deserialize(this.messages.get(key), TagResolver.resolver(rs));
    }
}
