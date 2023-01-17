package notorious.notorious.events;

import notorious.notorious.Notorious;
import notorious.notorious.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class JoinLeaveEvent implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (Notorious.Config.getConfig().getBoolean("show-join-quit-message")) {
            if (!Objects.requireNonNull(Notorious.Config.getConfig().getString("join-message")).isEmpty()) {
                String customJoinMessage = ColorUtils.changeColorCode(Objects.requireNonNull
                        (Notorious.Config.getConfig().getString("join-message"))).replaceAll("%player%", player.getDisplayName());
                event.setJoinMessage(customJoinMessage);
            } else {
                event.setJoinMessage(ColorUtils.changeColorCode("&e" + player.getDisplayName() + " joined the game"));
            }
        } else {
            event.setJoinMessage("");
        }

    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if (Notorious.Config.getConfig().getBoolean("show-join-quit-message")) {
            if (!Objects.requireNonNull(Notorious.Config.getConfig().getString("quit-message")).isEmpty()) {
                String customQuitMessage = ColorUtils.changeColorCode(Objects.requireNonNull
                        (Notorious.Config.getConfig().getString("quit-message"))).replaceAll("%player%", player.getDisplayName());
                event.setQuitMessage(customQuitMessage);
            } else {
                event.setQuitMessage(ColorUtils.changeColorCode("&e" + player.getDisplayName() + " quit the game"));
            }
        } else {
            event.setQuitMessage("");
        }

    }

}
