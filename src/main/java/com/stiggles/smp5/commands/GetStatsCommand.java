package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetStatsCommand implements CommandExecutor {

    SMP5 main;

    public GetStatsCommand(SMP5 main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(main.getPlayerManager().getStigglesPlayer(p.getUniqueId()).toString());
        }

        return false;
    }
}
