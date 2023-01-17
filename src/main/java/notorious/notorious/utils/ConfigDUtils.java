package notorious.notorious.utils;

import notorious.notorious.Notorious;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigDUtils implements Listener {

    private File file;
    private File filepath;
    private final JavaPlugin plugin;
    private final String name;
    private final String path;
    private FileConfiguration config;

    public ConfigDUtils(JavaPlugin plugin, String pathname, String filename) {
        this.plugin = plugin;
        this.path = pathname;
        this.name = filename;
        this.filepath = new File(plugin.getDataFolder(), path);
        this.file = new File(filepath, name);
    }

    public FileConfiguration getConfig() {
        if (!filepath.exists())
            this.saveDefaultConfig();
        if (config == null)
            this.reloadConfig();
        return config;
    }

    public void saveDefaultConfig() {
        if (!filepath.exists()) {
            boolean success = filepath.mkdirs();
            if (!success)
                Bukkit.getConsoleSender().sendMessage("There was a problem creating the config..");
        }
        if (!file.exists())
            this.plugin.saveResource(path + name, false);
    }

    public static void save(ConfigDUtils config) {
        config.saveConfig();
        config.reloadConfig();
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (Throwable t) {
            Bukkit.getConsoleSender().sendMessage("There was a problem saving the config..");
        }
    }

    public void reloadConfig() {
        if (filepath == null)
            filepath = new File(plugin.getDataFolder(), path);
        if (file == null)
            file = new File(filepath, name);
        config = YamlConfiguration.loadConfiguration(file);
        InputStream stream = plugin.getResource(name);
        if (stream != null) {
            YamlConfiguration YmlFile = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));
            config.setDefaults(YmlFile);
        }
    }

    public void configOnEnable() {
        Notorious.Config.saveDefaultConfig();
        Notorious.Config.reloadConfig();
        Notorious.Config.saveConfig();
    }

}
