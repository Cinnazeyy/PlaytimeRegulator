package github.PlaytimeRegulator.core;

import github.PlaytimeRegulator.PlaytimeRegulator;
import github.PlaytimeRegulator.core.system.User;
import github.PlaytimeRegulator.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Score;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Set Scoreboard
        player.setScoreboard(PlaytimeRegulator.board);

        // Get Score
        Score lastUpdatedScore = PlaytimeRegulator.lastUpdatedHour.getScore(player.getName());
        Score playtimeScore = PlaytimeRegulator.playtimeObjective.getScore(player.getName());

        // Set Initial Playtime:
        if (playtimeScore.getScore() <= 0) {
            if (!lastUpdatedScore.isScoreSet() || lastUpdatedScore.getScore() <= 0) {
                PlaytimeRegulator.board.resetScores(player.getName());
                lastUpdatedScore.setScore(Utils.getCurrentHourInt());
                playtimeScore.setScore(60 * 60 * 2);
                player.sendMessage("§aWelcome! You received 2 hours of starting playtime. Have Fun!");
            }
        }
        event.setJoinMessage("§l§7>> §r§6" + player.getName() + " has joined with a playtime of " + Utils.getFormattedTime(playtimeScore.getScore()));
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        //TODO: Leave Message stuff
    }
}
