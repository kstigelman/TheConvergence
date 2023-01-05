package com.stiggles.smp5.commands;

import com.stiggles.smp5.managers.BankManager;
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
                p.sendMessage(ChatColor.RED + "Incorrect usage!");
            }

            int balance = BankManager.getBalance(p);

            p.sendMessage("You have " + balance + " coins.");
            return true;
        }
        else {
            System.out.println("You may not use this command as anything but a player!");
        }
        return false;
    }
}
