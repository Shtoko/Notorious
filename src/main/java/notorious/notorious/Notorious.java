package notorious.notorious;

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
        Bukkit.getConsoleSender().sendMessage(ColorUtils.changeColorCode
                (Objects.requireNonNull(Config.getConfig().getString("StartUp-Message"))));

    }

    @Override
    public void onDisable() {

        // plugin stopping message
        Bukkit.getConsoleSender().sendMessage(ColorUtils.changeColorCode
                (Objects.requireNonNull(Config.getConfig().getString("StartDown-Message"))));

        // saves config
        ConfigDUtils.save(Config);
        Bukkit.getConsoleSender().sendMessage(ColorUtils.changeColorCode("&c[Notorious] Saving configs."));

    }
}
