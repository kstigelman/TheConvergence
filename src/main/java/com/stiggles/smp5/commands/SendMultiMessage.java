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

public class SendMultiMessage implements CommandExecutor {

    SMP5 main;

    public SendMultiMessage(SMP5 main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length != 2)
            return false;

        if (Bukkit.getPlayer(args[0]) == null)
            return false;

        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        StigglesNPC npc = NPCManager.getNPC(id);
        Player p = Bukkit.getPlayer(args[0]);

        ArrayList<String> dialogue = npc.getDialogues();

        if (dialogue == null || dialogue.isEmpty())
            return false;

        for (int i = 0; i < dialogue.size(); ++i) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(main, () -> {
                npc.sendMessage(p, dialogue.get(finalI));
            }, (long) finalI * 120);
        }
        return true;
    }
}
