package com.stiggles.smp5.dungeons;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class DungeonStartCommand implements CommandExecutor, Listener {

    public static ArrayList players = new ArrayList<Player>();
    public static ArrayList alivePlayers = new ArrayList<Player>();
    public ArrayList<Player> getPlayersList(){
        return players;
    }
    public ArrayList<Player> getAlivePlayers(){
        return alivePlayers;
    }
    boolean move = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (!sender.isOp())
                return false;
                if (sender.isOp()) {
                    if (!players.contains(p)) {
                        if ((players.size()) <= 3) {//3
                            if (players.size() == 0) {
                                //Bukkit.broadcastMessage(ChatColor.YELLOW +"A Dungeon is being started by " + p.getName() +" you have 30 seconds to type the command: /dungeon join " + p.getName());
                                p.sendMessage(ChatColor.GREEN + "You are being sent to the dungeon! Prepare yourself! (" + Math.addExact(1, players.size()) + "/4 Players)");
                               // p.teleport(Bukkit.getWorld("testdungeon").getBlockAt((int) 43.5, -42, (int) 190.5).getLocation());
                                p.getLocation().setYaw(90);
                                p.getLocation().setPitch(0);
                                //players.add(p);
                                //alivePlayers.add(p);
                                DungeonManager.addPlayer(p, "testdungeon");
                                p.getInventory().clear();
                                p.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                                p.getInventory().addItem(new ItemStack(Material.IRON_HELMET));
                                p.getInventory().addItem(new ItemStack(Material.IRON_CHESTPLATE));
                                p.getInventory().addItem(new ItemStack(Material.IRON_LEGGINGS));
                                p.getInventory().addItem(new ItemStack(Material.IRON_BOOTS));
                                p.getInventory().addItem(new ItemStack(Material.SHIELD));
                                p.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 64));

                                p.setGameMode(GameMode.ADVENTURE);
                               // p.sendMessage(ChatColor.GREEN + "You have been given items to aid you in the dungeon! Good luck.");
                            } else {
                                p.sendMessage(ChatColor.GREEN + "You are being sent to the dungeon! Prepare yourself! (" + Math.addExact(1, players.size()) + "/4 Players)");
                                //p.teleport(Bukkit.getWorld("testdungeon").getBlockAt(43, -42, 190).getLocation());

                                DungeonManager.addPlayer(p, "testdungeon");

                                if (players.size() == 4) {
                                    System.out.println("[DUNGEON ALERT] Dungeon is currently full.");
                                }
                            }

                    } else {
                        p.sendMessage(ChatColor.RED + "The dungeon you are trying to join is currently filled.");
                    }
                }
            } else {
                p.sendMessage(ChatColor.RED +"You may not use this command!");
            }
        } else {
            System.out.println("NO");
        }
        return false;
    }
}