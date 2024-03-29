package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/** Represents a command that displays to the player the amount of coins they have collected.
 *
 * @author Kyler Stigelman
 */
public class CoinCommand implements CommandExecutor {

    //Need SMP5 object to access PlayerManager.
    SMP5 main;
    public CoinCommand(SMP5 main) {
        this.main = main;
    }

    /** Displays the amount of coins the sender has.
     *  Admins may check other players coins and give players coins.
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return True if the command succeeded, false otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                if (p.isOp()) {
                    if (args.length >= 3) {
                        //Give players coins: /coins give <username> <amount>
                        if (args[0].equals("give")) {
                            Player argPlayer = Bukkit.getPlayer(args[1]);

                            if (argPlayer != null) {
                                StigglesPlayer sPlayer = main.getPlayerManager().getStigglesPlayer(argPlayer.getUniqueId());
                                try {
                                    int amount = Integer.parseInt(args[2]);
                                    //BankManager.deposit(argPlayer, amount);
                                    sPlayer.deposit(amount);
                                    p.sendMessage("Added " + amount + " to " + argPlayer.getName());
                                    return true;
                                } catch (NumberFormatException e) {
                                    p.sendMessage(ChatColor.RED + args[2] + " is not a number!");
                                    return false;
                                }
                            }
                            p.sendMessage(ChatColor.RED + "Account does not exist!");
                            return false;
                        }
                    }
                    //Check other player's coins: /coins <username>
                    Player argPlayer = Bukkit.getPlayer(args[0]);
                    if (argPlayer != null) {
                        StigglesPlayer sPlayer = main.getPlayerManager().getStigglesPlayer(argPlayer.getUniqueId());
                        p.sendMessage(args[0] + " has " + sPlayer.getBalance() + " coins.");
                        //p.sendMessage(args[0] + " has " + BankManager.getBalance(argPlayer)+ " coins.");
                        return true;
                    }
                    p.sendMessage(ChatColor.RED + "Account does not exist!");
                    return false;
                }
                //If they have any other arguments and the player is not opped, just ignore
                // the arg and return the player's account instead.
            }
            //Check own coins: /coins
            p.sendMessage("You have " + ChatColor.GOLD + main.getPlayerManager().getStigglesPlayer(p.getUniqueId()).getBalance() + " coins.");
            return true;
        }
        //Repeat process for console.
        else if (args.length > 0) {
            if (args.length >= 3) {
                if (args[0].equals("give")) {
                    Player argPlayer = Bukkit.getPlayer(args[1]);
                    if (argPlayer != null) {
                        try {
                            int amount = Integer.parseInt(args[2]);
                            StigglesPlayer sPlayer = main.getPlayerManager().getStigglesPlayer(argPlayer.getUniqueId());
                            sPlayer.deposit(amount);
                            //BankManager.deposit(argPlayer, amount);
                            Bukkit.getConsoleSender().sendMessage("Added " + amount + " to " + argPlayer.getName());
                            return true;
                        } catch (NumberFormatException e) {
                            Bukkit.getConsoleSender().sendMessage(args[2] + " is not a number!");
                            return false;
                        }
                    }
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Account does not exist!");
                    return false;
                }
            }
            Player argPlayer = Bukkit.getPlayer(args[0]);
            if (argPlayer != null) {
                StigglesPlayer sPlayer = main.getPlayerManager().getStigglesPlayer(argPlayer.getUniqueId());
                Bukkit.getConsoleSender().sendMessage(args[0] + " has " + sPlayer.getBalance() + " coins.");
                //Bukkit.getConsoleSender().sendMessage(args[0] + " has " + BankManager.getBalance(argPlayer)+ " coins.");
                return true;
            }
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Account does not exist!");
            return false;
        }
        return false;
    }
}
