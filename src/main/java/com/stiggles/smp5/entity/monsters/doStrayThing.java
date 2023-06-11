package com.stiggles.smp5.entity.monsters;

import com.stiggles.smp5.entity.Entities;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class doStrayThing implements CommandExecutor {

    private SMP5 main;
    public doStrayThing(SMP5 main){
        this.main = main;
    }
    Entities entities = new Entities();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.isOp()){
                //main.spawnStrayGroup();
                //CustomSpawns.spawnTheBeast();
            }
        }
        return false;
    }
}