package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
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
        Bukkit.getWorld("testdungeon").setSpawnLocation(895, 98, 944);

        new BukkitRunnable() {
            @Override
            public void run() {

                if (!active || ended)
                    return;
                if (timer >= 100.0f) {
                    ended = true;
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Success. Power generator charged to 100%");
                    int active = main.getSpawnerCuboids().getActiveCuboid();

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.playSound(player, Sound.BLOCK_BEACON_POWER_SELECT, 1, 2);
                    }

                    if (active == 0) {
                        Bukkit.getScheduler().runTaskLater(main, () -> Bukkit.broadcastMessage("<Starry> Good job everyone! All units are online!"), 60);
                        broadcastLater(ChatColor.RED + "[WARNING] Generator collapse process commenced. Please evacuate immediately.", 120);
                        Bukkit.getScheduler().runTaskLater(main, () -> Bukkit.broadcastMessage("<" + ChatColor.DARK_PURPLE + "Emperor Nouveau" +ChatColor.WHITE + "> " + ChatColor.DARK_RED + "NO! YOU CAN'T DO THIS. AGH.... Fine... I'll fight you myself"), 200);
                        sendMessageLater ("Sir Philippe Alfred", "Regarder! Look! He's over there on that floating platform!", 280);

                        main.getSpawnerCuboids().setActiveCuboid(4);

                    }
                    else if (active == 1) {
                        Bukkit.getScheduler().runTaskLater(main, () -> Bukkit.broadcastMessage("<Anarcho> YYYEEEESSSS! HAHA! TAKE THAT NOUVEAU!"), 80);
                        Bukkit.getScheduler().runTaskLater(main, () -> Bukkit.broadcastMessage("<" + ChatColor.DARK_PURPLE + "Emperor Nouveau" +ChatColor.WHITE + "> " + "YOU IDIOTIC MINIONS!!! STOP THEM!!!!"), 140);
                        sendMessageLater("Dr. Trog","Haha! Fantastic work everyone. We're halfway done!", 220);
                        main.getSpawnerCuboids().setActiveCuboid(2);
                    }
                    else if (active == 2) {
                        Bukkit.getScheduler().runTaskLater(main, () -> Bukkit.broadcastMessage("<Captain Beast> Just like the good old days."), 60);
                        sendMessageLater("Dr. Trog", "Good job everyone! Now we just need to finish charging the Community City!", 120);
                        main.getSpawnerCuboids().setActiveCuboid(0);
                    }
                    else if (active == 3) {
                        Bukkit.getScheduler().runTaskLater(main, () -> Bukkit.broadcastMessage("<The Inventor> Perfect. That went perfectly. Just as I predicted."), 60);
                        sendMessageLater("Dr. Trog", "Great start. Next, go help Anarcho at the mesa!", 120);
                        main.getSpawnerCuboids().setActiveCuboid(1);
                    }
                    return;
                }
                timer += 0.6f;
                if (((int) Math.floor(timer + 0.6)) % 10 == 0)
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
            sendMessage("Dr. Trog", "Attention everyone! It's time for us to turn on the power generator and get out of this world!");
            sendMessageLater("Dr. Trog", "Once it's on, we'll have to go enable each other generator manually. Captain Beast, Anarcho, and the Inventor are already at those locations waiting for your arrival!", 80);
            sendMessageLater("Dr. Trog", "Get in position everyone! Ralph, flip the switch!", 180);

            sendMessageLater("Ralph", "Yessir.", 240);
            sendMessageLater("EGO POWER GENERATOR", ChatColor.GREEN + "Startup sequence initiated. Connecting to remote locations...", 280);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "What's this?", 360);
            sendMessageLater("Starry", "There he is...", 420);
            sendMessageLater("EGO POWER GENERATOR", ChatColor.GREEN + "Connected. Please enable each source.", 480);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", ChatColor.DARK_RED + "NO.", 540);
            sendMessageLater("Dr. Trog", "Let's go everyone! We have to start up those other sources! Go find the Inventor at the Mooshroom Island first!", 620);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", ChatColor.DARK_RED + "MR. EGO IS DEAD.", 700);
            sendMessageLater("Starry", "WHAT?", 750);
            sendMessageLater("Sir Philippe Alfred", "QUOI?", 750);
            sendMessageLater("Dr. Trog", "WHAT?", 750);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", ChatColor.DARK_RED + "YOU WON'T WIN...", 820);

            Bukkit.getScheduler().runTaskLater(main, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.teleport(new Location(Bukkit.getWorld("world"), -278, 69, 1312));
                    main.getSpawnerCuboids().setActiveCuboid(3);
                }
            }, 900);

            sendMessageLater("Dr. Trog", "Uh... sorry... I'm a bit flustered here. I don't know what to feel. He killed Mr. Ego?", 1000);
            sendMessageLater("Dr. Trog", "I'm sorry. That's heavy for our current situation. That man has lost his mind. We need to continue the plan, no matter what.", 1080);
            return true;
        }
        if (args[0].equals("mesa")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.teleport(new Location(Bukkit.getWorld("world"), 1257, 93, 1491));
            }
        }
        if (args[0].equals("ruins")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.teleport(new Location(Bukkit.getWorld("world"), -586, 120, -1111));
            }
        }
        if (args[0].equals("cc")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.teleport(new Location(Bukkit.getWorld("testdungeon"), 895, 98, 944));
            }
        }
        if (args[0].equals("vis")) {
            for (Player p : Bukkit.getOnlinePlayers())
                p.setInvisible(false);
        }
        if (args[0].equals("labs")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.teleport(Bukkit.getWorld("sanctuary").getSpawnLocation());
                p.setInvisible(true);
                p.setGameMode(GameMode.ADVENTURE);
                if (p.getName().equals ("Kaayylo")) {
                    p.teleport(new Location(Bukkit.getWorld("sanctuary"), 0, 100, 0));
                    p.setGameMode(GameMode.CREATIVE);
                }
            }
            sendMessage("Dr. Trog", "...");
            sendMessageLater("Dr. Trog", "Wow.", 60);
            sendMessageLater("Dr. Trog", "...we actually did it...", 120);
            sendMessageLater("Dr. Trog", "...but not without loss. Mr. Ego is dead...", 180);
            sendMessageLater("Dr. Trog", "What do we do now?", 300);
            sendMessageLater("Dr. Trog", "We're free...", 400);
            sendMessageLater("Dr. Trog", "Thank you all for your help. I don't know what the state of EGO Labs will be from this point onwards, but I will help each one of you get back to a place of comfort.", 500);
            sendMessageLater("Dr. Trog", "Whether its on this world, or another world, we can undo the damage both Nouveau and Mr. Ego caused to all of you.", 620);
            sendMessageLater("Dr. Trog", "Though it take a long time, let's put this all in the past. Look-- we're free! Letting our grievances brood won't get us anywhere.", 700);
            sendMessageLater("Dr. Trog", "Now... let's get to work on getting this stuff back together.", 800);

            Bukkit.getScheduler().runTaskLater(main, () -> { for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle(ChatColor.GOLD + "The End", null,40, 200, 40);

                for (int i = 0; i < 3; ++i) {
                    playSoundLater(Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 860 + (i * 150));
                    playSoundLater(Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 880 + (i * 150));
                    playSoundLater(Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 900 + (i * 150));
                    playSoundLater(Sound.ENTITY_FIREWORK_ROCKET_BLAST, 930 + (i * 150));
                    playSoundLater(Sound.ENTITY_FIREWORK_ROCKET_BLAST, 950 + (i * 150));
                    playSoundLater(Sound.ENTITY_FIREWORK_ROCKET_BLAST, 970 + (i * 150));
                    playSoundLater(Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 990 + (i * 150));
                }

            }}, 860);


        }
        if (args[0].equals("wanderer")) {
            broadcast(ChatColor.RED + "[WARNING] Generator collapse expected in 2 minutes.");
            sendMessage(ChatColor.DARK_PURPLE + "Emperor Nouveau", "No... this can't be happening...");
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "Why... why must you destroy everything I've worked for...", 80);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "I just wanted to have control over something. I wanted to rule again. Like the good old days...", 160);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "And you all... I thought this world would be a place you all wanted...", 260);
            sendMessageLater("Starry", "Nouveau, you forced us all to be here against our will-- sure, EGO Labs didn't really respect us, any of us-- but you certainly didn't either!", 360);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "No. And I can't let you leave. YOU ALL WILL STAY HERE WITH ME! STOP THE GENERATOR!", 440);


            sendMessageLater("The Wanderer", "It's over, Nouveau", 500);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "No... no no no... HOW?", 560);
            sendMessageLater("Anarcho", "Who are you?", 620);
            sendMessageLater("The Wanderer", "I am the Wanderer, once known as Clato. I became corrupted by the Convergence after Nouveau killed me during the raid on EGO Labs. I'm here for justice.", 700);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "PLEASE, LET ME FIX THIS.", 780);
            sendMessageLater("The Wanderer", "You can't, Nouveau. You caused enough harm already.", 840);
            sendMessageLater("The Wanderer", "Dr. Trog. Starry. Phil. Captain. Anarcho. Inventor. Thank you for your help. I'll take it from here. Get off this world before it's destroyed.", 900);
            sendMessageLater("Dr. Trog", "Oh... well then, alright. Take it away. Come on everyone, we don't have much time, let's get out of here.", 1000);
            sendMessageLater(ChatColor.DARK_PURPLE + "Emperor Nouveau", "PLEASE. YOU CAN'T LEAVE ME HERE.", 1080);
            broadcastLater(ChatColor.RED + "[WARNING] Generator collapse is imminent. Please evacuate.", 1140);
            return true;
        }
        if (args[0].equals("reset_time")) {
            timer = 0.0f;
            ended = false;
            Bukkit.broadcastMessage(ChatColor.GREEN + "Power generator online. Please ensure the charging process does not get interrupted.");
            return true;
        }
        if (args[0].equals("toggle_time")) {
            active = !active;
            return true;
        }
        if (args[0].equals("cameo")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle(ChatColor.RED + "Drem, Defender of Worlds", ChatColor.GRAY + "Boss Fight", 20, 80, 20);
            }
            Bukkit.getScheduler().runTaskLater(main, () -> Bukkit.broadcastMessage("<YoDrem> Oh, uh... wrong timeline."), 120);
            Bukkit.getScheduler().runTaskLater(main, () -> { for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle(ChatColor.RED + "Never mind", ChatColor.GRAY + "He's in the wrong timeline", 20, 80, 20);
            }}, 180);
        }

        if (args[0].equals("switch")) {
            if (args.length != 2) {
                sender.sendMessage(ChatColor.RED + "Incorrect usage: /finale switch <int>");
                return false;
            }

            try {
                main.getSpawnerCuboids().setActiveCuboid(Integer.parseInt(args[1]));

                sender.sendMessage(ChatColor.GREEN + "Switched selection " + main.getSpawnerCuboids().getActiveCuboid());
                return true;
            }
            catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Incorrect usage: /finale switch <int>");

                return false;
            }
        }
        sender.sendMessage(ChatColor.RED + "Incorrect usage: /finale init|start|defeat|wanderer|switch|toggle_time|reset_time");
        return false;
    }

    /**
     * Send a message from the NPC to all players.
     *
     * @param name The name of the message sender.
     * @param msg The message to be sent.
     */
    public void sendMessage (String name, String msg) {
        Bukkit.broadcastMessage("<" + name + "> " + msg);
    }

    public void broadcast (String msg) {
        Bukkit.broadcastMessage(msg);
    }
    public void broadcastLater (String msg, int delay) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            Bukkit.broadcastMessage(msg);
        }, delay);
    }
    /**
     * Send a message from the NPC to all players after a delay.
     *
     * @param name The name of the message sender.
     * @param msg The message to be sent.
     * @param delay The time duration in ticks to wait before sending.
     */
    public void sendMessageLater (String name, String msg, int delay) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            Bukkit.broadcastMessage("<" + name + ChatColor.WHITE + "> " + msg);

        }, delay);

    }
    public void playSoundLater (Sound sound, int delay) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p, sound, 1, 1);
            }
        }, delay);
    }
}
