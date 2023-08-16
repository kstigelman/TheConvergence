package com.stiggles.smp5.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/** Represents a command that can teleport a player between different worlds.
 *
 * @author Kyler Stigelman
 */
public class ChangeWorldCommand implements CommandExecutor {
    /** Teleports the sender player to the specified world.
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return True if the command succeeded, false otherwise.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length != 1)
                return false;

            Player p = (Player) sender;
            if (!p.isOp())
                return false;
            if (Bukkit.getWorld(args[0]) == null)
                return false;

            p.teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
            return true;
        }
        return false;
    }
}
