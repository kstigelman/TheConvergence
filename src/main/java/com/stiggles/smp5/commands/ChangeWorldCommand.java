package com.stiggles.smp5.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeWorldCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length != 1)
                return false;

            Player p = (Player) sender;
            if (!p.isOp())
                return false;
            if (Bukkit.getWorld (args[0]) == null)
                return false;

            p.teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
            return true;
        }
        return false;
    }
}
