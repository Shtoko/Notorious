package notorious.notorious;

import notorious.notorious.commands.ReloadConfig;
import notorious.notorious.events.JoinLeaveEvent;
import notorious.notorious.utils.ColorUtils;
import notorious.notorious.utils.ConfigDUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Notorious extends JavaPlugin {

    public static ConfigDUtils Config;

    @Override
    public void onEnable() {

        // config setup
        Config = new ConfigDUtils(this, "", "config.yml");
        Config.configOnEnable();

        // plugin starting message
        if (!Objects.requireNonNull(Config.getConfig().getString("start-up-message")).isEmpty()) {
            Bukkit.getConsoleSender().sendMessage(ColorUtils.changeColorCode(Objects.requireNonNull(Config.getConfig().getString("start-up-message"))));
        }

        // plugin events
        getServer().getPluginManager().registerEvents(new JoinLeaveEvent(), this);

        // plugin commands
        Objects.requireNonNull(getCommand("noreload")).setExecutor(new ReloadConfig());

    }

    @Override
    public void onDisable() {

        // plugin stopping message
        if (!Objects.requireNonNull(Config.getConfig().getString("start-down-message")).isEmpty()) {
            Bukkit.getConsoleSender().sendMessage(ColorUtils.changeColorCode(Objects.requireNonNull(Config.getConfig().getString("start-down-message"))));
        }

    }
}
