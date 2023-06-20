package com.stiggles.smp5.commands;

import com.stiggles.smp5.entity.lostMerchant.LostMerchant;
import org.bukkit.*;
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
            if (p.isOp() && !spawned){
                spawnMerchants();
                spawned = true;
            } else {
                p.sendMessage(ChatColor.RED +"You do not have permission to use this command! That or you've already spawned them.");
            }
        } else {
            if (!spawned){
                spawnMerchants();
                spawned = true;
            }
        }

        return false;
    }

    private void spawnMerchants() {
        World world = Bukkit.getWorld("world");
        merchant.spawnLostMerchant(new Location(world, 1332, 123, -824));
        merchant.spawnLostMerchant(new Location(world, -302, 69, 315));
        merchant.spawnLostMerchant(new Location(world, -904, 72, 1517));
        merchant.spawnLostMerchant(new Location(world, -903, 72, 701));
        merchant.spawnLostMerchant(new Location(world, -456, 117, -1195));
        merchant.spawnLostMerchant(new Location(world, -873, 66, -312));
        merchant.spawnLostMerchant(new Location(world, 816, 110, 510));
        merchant.spawnLostMerchant(new Location(world, 1123, 67, -203));
    }

}
