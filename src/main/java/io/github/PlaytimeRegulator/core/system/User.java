package github.PlaytimeRegulator.core.system;

import org.bukkit.Bukkit;

import java.util.UUID;

public class User {
    private final UUID uuid;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUUID(){
        return uuid;
    }

    public boolean isOnline() { return Bukkit.getPlayer(uuid) != null; }

    public String getName() {
        //TODO: Database stuff
        return "";
    }
}
