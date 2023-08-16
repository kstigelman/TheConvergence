package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/** Represents a command that displays a player's stats
 *
 * @author Kyler Stigelman
 */
public class GetStatsCommand implements CommandExecutor {

    //SMP5 instance for PlayerManager access.
    SMP5 main;
    public GetStatsCommand(SMP5 main) {
        this.main = main;
    }

    /** Displays a player's stats including coins collected, quests completed,
     *  kill streak, and NPCs talked to.
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return True if the command succeeded, false otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player p = (Player) sender;
        p.sendMessage(main.getPlayerManager().getStigglesPlayer(p.getUniqueId()).toString());
        return true;
    }
}
