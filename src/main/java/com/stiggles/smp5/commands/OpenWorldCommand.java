package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class OpenWorldCommand implements CommandExecutor {

    SMP5 main;
    public OpenWorldCommand (SMP5 main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp())
            return false;

        if (main.isOpen()) {
            main.setOpen(false);
            sender.sendMessage("Server is closed");
        }
        else {
            main.setOpen(true);
            sender.sendMessage("Server is open");
        }


        return true;
    }
}
