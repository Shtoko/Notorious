package notorious.notorious.commands;

import notorious.notorious.Notorious;
import notorious.notorious.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.Objects;

public class ReloadConfig implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        PluginDescriptionFile pdf = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Notorious")).getDescription();
        Notorious.Config.reloadConfig();

        if (sender instanceof Player player) {
            player.sendMessage(ColorUtils.changeColorCode(Objects.requireNonNull(Notorious.Config.getConfig().getString("config-reload-message"))) + " Version: " + pdf.getVersion());
            Bukkit.getConsoleSender().sendMessage(ColorUtils.changeColorCode(Objects.requireNonNull(Notorious.Config.getConfig().getString("config-reload-message"))) + " Version:" + pdf.getVersion());
        } else {
            Bukkit.getConsoleSender().sendMessage(ColorUtils.changeColorCode(Objects.requireNonNull(Notorious.Config.getConfig().getString("config-reload-message"))) + " Version:" + pdf.getVersion());
        }

        return true;
    }

}
