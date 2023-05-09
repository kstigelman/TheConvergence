package com.stiggles.smp5.dungeons;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckCommand implements CommandExecutor {

    //DungeonStartCommand dSC = new DungeonStartCommand();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(sender instanceof Player) {
            Player p = (Player) sender;
            sender.sendMessage("Dungeon players array list amount: " + DungeonManager.getPlayers(p.getWorld().getName()).size() +".");
            sender.sendMessage("Dungeon alive array list amount: " + DungeonManager.getAlivePlayers(p.getWorld().getName()).size() + ".");

        } else {
            if (args.length == 0)
                return false;

            System.out.println("Dungeon players array list amount: " + DungeonManager.getPlayers(args[0]).size() +".");
            System.out.println("Dungeon alive array list amount: " + DungeonManager.getPlayers(args[0]).size() +".");
        }

        return false;
    }
}
