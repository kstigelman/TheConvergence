package com.stiggles.smp5.commands;

import com.stiggles.smp5.entity.lostMerchant.InventoryManager;
import com.stiggles.smp5.entity.lostMerchant.MerchantListener;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class ResetMerchants implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!sender.isOp()) {
            return false;
        }
        MerchantListener.resetMerchantCheckMap();
        InventoryManager.resetInventoryMapping();
        sender.sendMessage(ChatColor.AQUA+"Fully reset ALL merchant shops!");
        Bukkit.getLogger().log(Level.FINEST, "Fully Reset ALL Merchant Shops!");
        return false;
    }
}
