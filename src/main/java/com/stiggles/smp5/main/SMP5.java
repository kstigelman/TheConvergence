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

import com.stiggles.smp5.entity.npc.dialoguenpc.EggDONTTake;
import com.stiggles.smp5.entity.npc.dialoguenpc.Ned;
import com.stiggles.smp5.entity.npc.questnpc.Drem;
import com.stiggles.smp5.entity.npc.shopnpcs.DungeonKeeper;
import com.stiggles.smp5.listeners.*;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.CoinBankManager;
import com.stiggles.smp5.managers.MobKillListener;
import com.stiggles.smp5.player.CoinBank;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.entity.npc.*;
import com.stiggles.smp5.entity.npc.shopnpcs.Starry;
import com.stiggles.smp5.managers.NPCManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SMP5 extends JavaPlugin implements Listener {


    /*
        TODO - REMOVE NPC FROM plugin.yml
     */
    private static SMP5 instance;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean inDungeon = true;
    public BankManager bankManager;
    //public PacketListener packetListener;
    public static SMP5 getInstance() {
        return instance;
    }

    private Database database;
    private PlayerManager playerManager;
    private ArrayList<UUID> registeredUUIDs;


    private ArrayList<StigglesNPC> npcs;
    public HashMap<String, StigglesPlayer> online_players;
    //private Plugin plugin = SMP5.getPlugin(SMP5.class);

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (Bukkit.getWorld ("smp5") == null) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Could not load world.");
            Bukkit.getServer().shutdown();
        }

        try {
            database.connect();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Database connection failed. Server shutting down.");
            Bukkit.getServer().shutdown();
        }

        //LOAD Registered player (UUIDS) from database
        try {
            ResultSet rs =database.query("SELECT * FROM players");
            while (rs.next ()) {
                UUID uuid = UUID.fromString(rs.getString(1));
                registeredUUIDs.add (uuid);
            }
            rs.close ();
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to fetch players");
        }

        registeredUUIDs = new ArrayList<>();
        online_players = new HashMap<>();
        playerManager = new PlayerManager();
        bankManager = new BankManager(this);
        database = new Database();

        registerEvents();
        registerCommands();
        createNPCs ();

        /*
            This is the lobby setup stuff
         */
    }

    @Override
    public void onDisable() {
        instance = null;
        //Update world database
        database.runQueue();

        if (database != null)
            database.disconnect ();

        bankManager.onDisable();

        Bukkit.getServer().shutdown();
    }

    public DateTimeFormatter getFormatter() {return formatter; }
    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }

    public static SMP5 getPlugin () {
        return instance;
    }
    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {

        //Check if player is registered already
        Player p = e.getPlayer();

        if (!registeredUUIDs.contains(p.getUniqueId())) {
            try {
                //Register player record
                database.execute("INSERT INTO players VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', " + 0 + ")");
                //Register bank record
                database.execute("INSERT INTO bank VALUES ('" + p.getUniqueId() + "', '" + 0 + ")");
            }
            catch (SQLException event) {
                Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to register new player.");
            }
        }
        //online_players.put (e.getPlayer ().getName (), new StigglesPlayer());
        //If so, add uuid + stigglesplayer to online_players
        //Otherwise, register new StigglesPlayer UUID and add to online_players


        //for (StigglesNPC n : NPCManager.getHashMap().values ())
          //  n.showToPlayer(e.getPlayer());

        //npc3.SetHolding(Material.TRIDENT);
        //npcs.get (2).SetHolding(Material.TRIDENT);
        //npcs.get (4).SetHolding (Material.GOLDEN_PICKAXE);
    }

    public void registerEvents () {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(this, this);
        manager.registerEvents(new LogEventListener(this), this);
        //manager.registerEvents (new ConnectionListener(this), this);
        //Bukkit.getPluginManager().registerEvents (packetListener, this);
        //Bukkit.getPluginManager().registerEvents (new NPCListener(this), this);
        manager.registerEvents(new CitizensRightClickEvent(this), this);
        manager.registerEvents(new ElytraEventListener(this), this);
        manager.registerEvents(new DungeonListener(this), this);
        manager.registerEvents(new MobKillListener(), this);
    }
    public void createNPCs () {
        npcs = new ArrayList<>();
        npcs.add (new Ned(this));
        npcs.add (new Starry (this));
        npcs.add (new EggDONTTake(this));
        npcs.add (new Drem (this));
        npcs.add (new DungeonKeeper(this));
    }
    public void registerCommands () {
        Bukkit.getPluginCommand("coins").setExecutor(new CoinCommand());
        //saveDefaultConfig()
        //stats = new PluginFile(this, "stats.yml", "stats.yml");
        //Load important database variables

    }
}
