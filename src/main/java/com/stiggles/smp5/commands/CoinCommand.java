package com.stiggles.smp5.commands;

import com.stiggles.smp5.managers.BankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {

                if (p.isOp()) {
                    Player argPlayer = Bukkit.getPlayer(args[0]);
                    if (argPlayer != null) {
                        p.sendMessage(args[0] + " has " + BankManager.getBalance(argPlayer)+ " coins.");
                        return true;
                    }
                    p.sendMessage(ChatColor.RED + "Account does not exist!");
                    return false;
                }
                //If they have any other arguments and the player is not opped, just ignore
                // the arg and return the player's account instead.
            }
            p.sendMessage("You have " + BankManager.getBalance(p) + " coins.");
            return true;
        }
        if (args.length > 0) {
            Player argPlayer = Bukkit.getPlayer(args[0]);
            if (argPlayer != null) {
                Bukkit.getConsoleSender().sendMessage(args[0] + " has " + BankManager.getBalance(argPlayer)+ " coins.");
                return true;
            }
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Account does not exist!");
            return false;
        }
        return false;
    }
}
