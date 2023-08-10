package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class FinaleCommands implements CommandExecutor {

    SMP5 main;
    float timer = 0.0f;
    boolean ended = false;
    boolean active = false;
    public FinaleCommands (SMP5 main) {
        this.main = main;

        new BukkitRunnable() {
            @Override
            public void run() {

                if (!active || ended)
                    return;
                if (timer >= 100.0f) {
                    ended = true;
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Success. Power generator charged to 100%");
                    return;
                }
                timer += 0.4f;
                if (timer % 10 == 0)
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Power generator charged to " + (int) (timer / 10) + "0%");
            }
        }.runTaskTimer(main, 20, 20);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp())
            return false;
        if (args.length == 0)
            return false;

        if (args[0].equals("init")) {


            return true;
        }
        if (args[0].equals("start")) {


            return true;
        }
        if (args[0].equals("defeat")) {

            return true;
        }
        if (args[0].equals("wanderer")) {

            return true;
        }
        if (args[0].equals("reset_time")) {
            timer = 0.0f;
            ended = false;
            Bukkit.broadcastMessage(ChatColor.GREEN + "Power generator online. Please ensure the charging process does not get interrupted.");
        }
        if (args[0].equals("toggle_time")) {
            active = !active;
        }
        if (args[0].equals("cameo")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle(ChatColor.RED + "Drem, Defender of Worlds", ChatColor.GRAY + "Boss Fight", 1, 4, 1);
            }
        }

        if (args[0].equals("switch")) {
            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "Incorrect usage: /finale switch <int>");
                return false;
            }

            try {
                main.getSpawnerCuboids().setActiveCuboid(Integer.parseInt(args[1]));
            }
            catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Incorrect usage: /finale switch <int>");
            }
        }
        sender.sendMessage(ChatColor.RED + "Incorrect usage: /finale init|start|defeat|wanderer|switch|toggle_time|reset_time");
        return false;
    }
}
