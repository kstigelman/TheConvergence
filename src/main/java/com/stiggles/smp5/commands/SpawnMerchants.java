package com.stiggles.smp5.commands;

import com.stiggles.smp5.entity.lostMerchant.LostMerchant;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnMerchants implements CommandExecutor {

    boolean spawned;

    LostMerchant merchant = new LostMerchant();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.isOp()) {
                spawnMerchants(p);
            } else {
                p.sendMessage(ChatColor.RED + "You do not have permission to use this command! That or you've already spawned them.");
            }
        } else {
            if (!spawned) {
                spawned = true;
            }
        }

        return false;
    }

    private void spawnMerchants(Player player) {
        World world = Bukkit.getWorld("world");
        merchant.spawnLostMerchant(player.getLocation());
    }

}
