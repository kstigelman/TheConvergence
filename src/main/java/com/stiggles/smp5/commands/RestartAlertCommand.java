package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RestartAlertCommand implements CommandExecutor {

    SMP5 main;
    public RestartAlertCommand (SMP5 main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.isOp()){
                for (Player player : Bukkit.getOnlinePlayers()){
                    player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 1, 2);
                }
                Bukkit.broadcastMessage(ChatColor.YELLOW + "[Server Restart] Server will be restarted at any second! Please stop.");
            } else {
                p.sendMessage(ChatColor.RED +"You do not have permission to use this command!");
            }
        } else {
            for (Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_0, 1, 2);
            }
            Bukkit.broadcastMessage(ChatColor.YELLOW + "[Server Restart] Server will be restarted at any second! Please stop.");
        }

        return false;
    }
}
