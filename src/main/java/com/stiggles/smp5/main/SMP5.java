/**
 * Plugin for Stiggles SMP Season 5, the biggest,
 * most ambitious project yet.
 *
 * @author Kyler Stigelman
 * @version 0.0.0
 * @since 2022-08-28
 */


package com.stiggles.smp5.main;


import com.stiggles.smp5.commands.NPCCommand;
import com.stiggles.smp5.entity.npc.shopnpcs.EggDONTTake;
import com.stiggles.smp5.entity.npc.Ned;
import com.stiggles.smp5.entity.npc.shopnpcs.*;
import com.stiggles.smp5.listeners.*;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.MobKillListener;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.entity.npc.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

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
        if(getServer().getPluginManager().getPlugin("Citizens") == null || !getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
            getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        //Use CitizensAPI

        instance = this;
        database = new Database();

        try {
            database.connect ();
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to connect to database.");
        }
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (Bukkit.getWorld ("world") == null) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Could not load world.");
            Bukkit.getServer().shutdown();
        }

        //LOAD Registered player (UUIDS) from database
        try {
            ResultSet rs = database.query("SELECT * FROM player;");
            if (rs != null) {
                while (rs.next()) {
                    UUID uuid = UUID.fromString(rs.getString(1));
                    registeredUUIDs.add(uuid);
                }
                rs.close();
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to fetch players");
        }
        registeredUUIDs = new ArrayList<>();
        online_players = new HashMap<>();
        playerManager = new PlayerManager();
        bankManager = new BankManager(this);




        /*
            This is the lobby setup stuff
         */
        registerCommands();
        if (CitizensAPI.hasImplementation()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    registerEvents();
                    registerCommands();
                    createNPCs();
                }
            }.runTaskLater(this, 20);
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        //Update world database
        //database.runQueue();
        try {
            if (database != null)
                database.disconnect ();
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to close db");
        }
        //bankManager.onDisable();

        Bukkit.getServer().shutdown();
    }

    public DateTimeFormatter getFormatter() {return formatter; }
    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }

    public static SMP5 getPlugin () {
        return instance;
    }
    @EventHandler
    public void onCitizensEnable(CitizensEnableEvent ev) {
        Bukkit.getConsoleSender().sendMessage("NV: Citizens Plugin enabled");
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {

        //Check if player is registered already
        Player p = e.getPlayer();
        /*
        if (!registeredUUIDs.contains(p.getUniqueId())) {
            //Register player record

            database.execute("INSERT INTO player VALUES ('" + p.getUniqueId() + "', '" + p.getName() + "', " + 0 + ")");
            //Register bank record
            database.execute("INSERT INTO bank VALUES ('" + p.getUniqueId() + "', '" + 0 + ")");
        }*/
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

        manager.registerEvents(new LogEventListener(this), this);
        //manager.registerEvents(this, this);
        //manager.registerEvents (new ConnectionListener(this), this);
        //Bukkit.getPluginManager().registerEvents (packetListener, this);
        //Bukkit.getPluginManager().registerEvents (new NPCListener(this), this);
        manager.registerEvents(new CitizensRightClickEvent(this), this);
        manager.registerEvents(new ElytraEventListener(this), this);
        //manager.registerEvents(new DungeonListener(this), this);
        manager.registerEvents(new MobKillListener(), this);
    }
    public void createNPCs () {
        CitizensAPI.getNPCRegistry().deregisterAll();
        npcs = new ArrayList<>();
        npcs.add (new Ned(this));
        npcs.add (new Starry (this));
        npcs.add (new EggDONTTake(this, "Francis Smurf"));
        npcs.add (new Drem (this));
        npcs.add (new DungeonKeeper(this));
        npcs.add (new Mister8Bit(this, "Luke the Fisherman"));
        npcs.add (new Spiffy (this, "Spiffy"));
        npcs.add (new Astronomer(this, "The Astronomer"));
        npcs.add (new Inventor(this, "The Inventor"));
        npcs.add (new Philippe(this, "Sir Philippe Alfred"));
    }
    public void registerCommands () {
        //Bukkit.getPluginCommand("coins").setExecutor(new CoinCommand());
        //saveDefaultConfig()
        //stats = new PluginFile(this, "stats.yml", "stats.yml");
        //Load important database variables
        Bukkit.getPluginCommand("loadcitizens").setExecutor(new NPCCommand(this));
    }
}
