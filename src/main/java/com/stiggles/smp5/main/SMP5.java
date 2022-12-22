/**
 * Plugin for Stiggles SMP Season 5, the biggest,
 * most ambitious project yet.
 *
 * @author Kyler Stigelman
 * @version 0.0.0
 * @since 2022-08-28
 */


package com.stiggles.smp5.main;


import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.entity.npc.*;
import com.stiggles.smp5.entity.npc.shopnpcs.Starry;
import com.stiggles.smp5.listeners.ConnectionListener;
import com.stiggles.smp5.listeners.PacketListener;
import com.stiggles.smp5.managers.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class SMP5 extends JavaPlugin implements Listener {

    private static SMP5 instance;

    public NPCManager npcManager;
    public PacketListener packetListener;
    public static SMP5 getInstance() {
        return instance;
    }

    boolean loaded = false;
    StigglesNPC npc;
    StigglesNPC npc2;
    StigglesNPC npc3;

    public HashMap<String, StigglesPlayer> online_players;
    //private Plugin plugin = SMP5.getPlugin(SMP5.class);
    @Override
    public void onEnable() {
        instance = this;
        npcManager = new NPCManager();

        packetListener = new PacketListener (this);
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents (new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents (packetListener, this);
        Bukkit.getPluginManager().registerEvents (new NPCListener(this), this);
        npc = new Ned (this);
        npc2 = new Starry(this);
        npc3 = new EggDONTTake(this);


        online_players = new HashMap<>();
        //LOAD Registered player (UUIDS) from database


        //saveDefaultConfig()
        //stats = new PluginFile(this, "stats.yml", "stats.yml");
        //Load important database variables
        getCommand ("npc").setExecutor (new NPCCommand (this));

        //if (Bukkit.getWorld("smp5") == null);

        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        instance = null;
        //Update world database
    }

    public static SMP5 getPlugin () {
        return instance;
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {

        //Check if player is registered already
        //If so, add uuid + stigglesplayer to online_players
        //Otherwise, register new StigglesPlayer UUID and add to online_players
        online_players.put (e.getPlayer ().getName (), new StigglesPlayer());
       npc.showToPlayer (e.getPlayer());
       npc2.showToPlayer (e.getPlayer());
       npc3.showToPlayer(e.getPlayer());
       npc3.SetHolding(Material.TRIDENT);

        //npc.sendMessage ("So deep ned bayou");

    }

    /*
    @EventHandler
    public void onPlayerMove (PlayerMoveEvent e) {
        if (!loaded) {
            getServer().broadcastMessage("Hi!");

            npc.showToPlayer(e.getPlayer());
            loaded = true;
        }

    }*/



}
