/**
 * Plugin for Stiggles SMP Season 5, the biggest,
 * most ambitious project yet.
 *
 * @author Kyler Stigelman
 * @version 0.0.0
 * @since 2022-08-28
 */


package com.stiggles.smp5.main;


import com.stiggles.smp5.commands.CoinCommand;
import com.stiggles.smp5.dungeons.Dungeon;
import com.stiggles.smp5.entity.npc.shopnpcs.Drem;
import com.stiggles.smp5.entity.npc.shopnpcs.ElytraEventListener;
import com.stiggles.smp5.events.DungeonListener;
import com.stiggles.smp5.listeners.CustomPlayer;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.MobKillListener;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.entity.npc.*;
import com.stiggles.smp5.entity.npc.shopnpcs.Starry;
import com.stiggles.smp5.listeners.ConnectionListener;
import com.stiggles.smp5.listeners.PacketListener;
import com.stiggles.smp5.managers.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SMP5 extends JavaPlugin implements Listener {

    private static SMP5 instance;
    public boolean inDungeon = true;
    public NPCManager npcManager;
    public BankManager bankManager;
    public PacketListener packetListener;
    public static SMP5 getInstance() {
        return instance;
    }

    private Database database;
    private PlayerManager playerManager;
    private CustomPlayer customPlayer;


    boolean loaded = false;
    StigglesNPC npc;
    StigglesNPC npc2;
    StigglesNPC npc3;

    private ArrayList<StigglesNPC> npcs;

    public HashMap<String, StigglesPlayer> online_players;
    //private Plugin plugin = SMP5.getPlugin(SMP5.class);
    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();


        instance = this;
        npcManager = new NPCManager();
        bankManager = new BankManager(this);

        packetListener = new PacketListener (this);
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents (new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents (packetListener, this);
        Bukkit.getPluginManager().registerEvents (new NPCListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ElytraEventListener(this), this);
        Bukkit.getPluginManager().registerEvents(new DungeonListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MobKillListener(), this);
        //npc = new Ned (this);
        //npc2 = new Starry(this);
        //npc3 = new EggDONTTake(this);

        npcs = new ArrayList<>();
        npcs.add (new Ned (this));
        npcs.add (new Starry (this));
        npcs.add (new EggDONTTake (this));
        npcs.add (new Drem (this));
        npcs.add (new DungeonKeeper(this));

        online_players = new HashMap<>();
        //LOAD Registered player (UUIDS) from database

        database = new Database();
        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        playerManager = new PlayerManager();

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginCommand("coins").setExecutor(new CoinCommand());
        //saveDefaultConfig()
        //stats = new PluginFile(this, "stats.yml", "stats.yml");
        //Load important database variables
        getCommand ("npc").setExecutor (new NPCCommand (this));

        //if (Bukkit.getWorld("smp5") == null);

        // Plugin startup logic



        /*
            This is the lobby setup stuff
         */
    }

    @Override
    public void onDisable() {
        instance = null;
        //Update world database
        if (database != null)
            database.disconnect ();
    }

    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }

    public static SMP5 getPlugin () {
        return instance;
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {

        //Check if player is registered already
        //If so, add uuid + stigglesplayer to online_players
        //Otherwise, register new StigglesPlayer UUID and add to online_players
        online_players.put (e.getPlayer ().getName (), new StigglesPlayer());
        BankManager.addPlayer(e.getPlayer());

        for (StigglesNPC n : NPCManager.getHashMap().values ())
            n.showToPlayer(e.getPlayer());

        //npc3.SetHolding(Material.TRIDENT);
        npcs.get (2).SetHolding(Material.TRIDENT);
        npcs.get (4).SetHolding (Material.GOLDEN_PICKAXE);
    }



    public void RegisterEvents () {

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
