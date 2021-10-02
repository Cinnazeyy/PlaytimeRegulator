package github.PlaytimeRegulator.core;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        //TODO: Join Message stuff
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        //TODO: Leave Message stuff
    }
}
