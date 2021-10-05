package github.PlaytimeRegulator.commands;

import github.PlaytimeRegulator.PlaytimeRegulator;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static github.PlaytimeRegulator.PlaytimeRegulator.board;

public class CMD_ptReset implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission("playtime.admin")) {
            for (String player : PlaytimeRegulator.board.getEntries()) {
                board.resetScores(player);
                PlaytimeRegulator.lastUpdatedHour.getScore(player).setScore(0);
            }
            Bukkit.broadcastMessage("§aReset all playtime!");
        }
        return true;
    }
}
