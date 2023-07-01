package com.stiggles.smp5.commands;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DremBotCommand implements CommandExecutor {
    SMP5 main;

    public DremBotCommand (SMP5 main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 2)
            return false;

        if (Bukkit.getPlayer(args[0]) == null)
            return false;



        Player p = Bukkit.getPlayer(args[0]);
        StigglesNPC npc = NPCManager.getNPCByName("Drem-Bot");

        if (npc == null || p == null)
            return false;

        switch (Integer.parseInt(args[1])) {
            case 1:
            case 2:
                npc.sendMessage(p, "Name: Anarcho\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Bearsharken\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Cryptorg\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Emperor Nouveau\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Gabe\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Luke the Fisherman\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Mole A Quacks\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Ralph\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Starry\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Spiffy\nHomeworld: Stiggles 1\nInfo: ");
                npc.sendMessage(p, "Name: Tigerfist\nHomeworld: Stiggles 1\nInfo: ");

                npc.sendMessage(p, "Name: The Astronomer\nHomeworld: Earth-360\nInfo: ");
                npc.sendMessage(p, "Name: Dr. Trog\nHomeworld: Earth-360\nInfo: ");

                npc.sendMessage(p, "Name: Dungeon Keeper\nHomeworld: Origins Kingdom\nInfo: ");
                npc.sendMessage(p, "Name: Sir Philippe Alfred\nHomeworld: Origins Kingdom\nInfo: ");

                npc.sendMessage(p, "Name: Mr. Morabito\nHomeworld: Clan 2\nInfo: ");
                npc.sendMessage(p, "Name: Mr. Orangeflips\nHomeworld: Clan 2\nInfo: ");

                npc.sendMessage(p, "Name: Beachman\nHomeworld: Stiggles Vacation!\nInfo: ");

                npc.sendMessage(p, "Name: Captain Beast\nHomeworld: DREAD\nInfo: ");

                npc.sendMessage(p, "Name: Francis Smurf\nHomeworld: Stiggles 3\nInfo: ");

                npc.sendMessage(p, "Name: The Inventor\nHomeworld: Into the Ashes\nInfo: ");




                break;

        }
        return true;
    }
}
