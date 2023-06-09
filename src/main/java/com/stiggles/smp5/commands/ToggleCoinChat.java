package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ToggleCoinChat implements CommandExecutor {



    public SMP5 main;

    public ToggleCoinChat (SMP5 main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            ArrayList<String> toggled = main.getToggledChatPlayers();

            if (toggled.contains (p.getName())) {
                toggled.remove(p.getName());
                p.sendMessage("Coin rewards chat is now " + ChatColor.GREEN + "ON");
            }
            else {
                toggled.add(p.getName());
                p.sendMessage("Coin rewards chat is now " + ChatColor.RED + "OFF");
            }
            return true;
        }
        //Set global variable for player to false/true
        //Store in database and locally
        //Add code to MobKillListner to check for this
        return false;
    }
}
