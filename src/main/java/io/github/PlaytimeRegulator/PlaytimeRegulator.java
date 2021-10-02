package github.PlaytimeRegulator;

import github.PlaytimeRegulator.core.EventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class PlaytimeRegulator extends JavaPlugin {
    private static PlaytimeRegulator plugin;
    
    @Override
    public void onEnable() {
        plugin = this;

        //Add Listeners
        getServer().getPluginManager().registerEvents(new EventListener(), plugin);

        getLogger().log(Level.INFO, "Successfully enabled PlaytimeRegulator plugin.");
    }

    public static PlaytimeRegulator getPlugin(){
        return plugin;
    }
}
