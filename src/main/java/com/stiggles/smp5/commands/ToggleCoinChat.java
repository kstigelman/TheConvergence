package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleCoinChat implements CommandExecutor {


    public SMP5 main;

    public ToggleCoinChat(SMP5 main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;

            StigglesPlayer playerData = main.getPlayerManager().getStigglesPlayer(p.getUniqueId());
            //ArrayList<String> toggled = main.getToggledChatPlayers();

            if (playerData.hasChatToggledOn()) {
                playerData.setChatToggledOn(false);
                p.sendMessage("Coin rewards chat is now " + ChatColor.RED + "OFF");

            } else {
                playerData.setChatToggledOn(true);
                p.sendMessage("Coin rewards chat is now " + ChatColor.GREEN + "ON");
            }
/*
            if (toggled.contains(p.getName())) {
                toggled.add (p.getName());
                p.sendMessage("Coin rewards chat is now " + ChatColor.RED + "OFF");

            } else {
                toggled.remove (p.getName());
                p.sendMessage("Coin rewards chat is now " + ChatColor.GREEN + "ON");
            }
*/
            return true;
        }
        //Set global variable for player to false/true
        //Store in database and locally
        //Add code to MobKillListner to check for this
        return false;
    }
}
