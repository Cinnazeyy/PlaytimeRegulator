package github.PlaytimeRegulator;

import github.PlaytimeRegulator.commands.CMD_ptReset;
import github.PlaytimeRegulator.core.EventListener;
import github.PlaytimeRegulator.utils.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.*;
import sun.nio.ch.Util;

import java.util.Date;
import java.util.logging.Level;

public class PlaytimeRegulator extends JavaPlugin {
    private static PlaytimeRegulator plugin;

    // Scoreboard
    public static ScoreboardManager manager;
    public static Objective playtimeObjective;
    public static Objective lastUpdatedHour;
    public static Scoreboard board;
    
    @Override
    public void onEnable() {
        plugin = this;

        //Add Listeners
        getServer().getPluginManager().registerEvents(new EventListener(), plugin);

        //Set Commands
        getCommand("ptreset").setExecutor(new CMD_ptReset());

        getLogger().log(Level.INFO, "Successfully enabled PlaytimeRegulator plugin.");

        setupScoreboard();

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    // Get Score
                    Score score = playtimeObjective.getScore(player.getName());
                    int scoreInt = score.getScore();

                    if (scoreInt <= 0 && score.isScoreSet()) {
                        // Update Score
                        score.setScore(0);
                        player.kickPlayer("§aYou have run out of Playtime! Come back later...");
                        continue;
                    } else if (scoreInt <= 10) {
                        // Whole Second Reminder
                        player.sendMessage("§4You only have " + scoreInt + " seconds left!");
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    }

                    score.setScore((score.getScore() - 1) + givePlaytime(player));

                    // Show Title
                    TextComponent titleText = new TextComponent();
                    titleText.setText(ChatColor.GOLD + Utils.getFormattedTime(scoreInt));
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, titleText);
                }
            }
        }, 0L, 20L); // Run every second
    }

    public void setupScoreboard() {
        manager = Bukkit.getScoreboardManager();
        assert manager != null;
        board = manager.getNewScoreboard();
        playtimeObjective = board.registerNewObjective("playtime", "dummy", "Play Time");
        lastUpdatedHour = board.registerNewObjective("lastUpdated", "dummy", "Last Updated Hour");
    }

    public int givePlaytime(Player player) {
        int hourDifference = Utils.getCurrentHourInt() - lastUpdatedHour.getScore(player.getName()).getScore();
        if (hourDifference >= 12) {
            int newPlaytime = 60 * 5 * hourDifference;
            player.sendMessage("§aYou received another " + Utils.getFormattedTime(newPlaytime) + " of playtime!");
            return newPlaytime;
        }
        return 0;
    }

    public static PlaytimeRegulator getPlugin(){
        return plugin;
    }
}
