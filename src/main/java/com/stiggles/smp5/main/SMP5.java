/**
 * Plugin for Stiggles SMP Season 5, the biggest,
 * most ambitious project yet.
 *
 * @author Kyler Stigelman
 * @version 0.0.0
 * @since 2022-08-28
 */
package com.stiggles.smp5.main;

import com.stiggles.smp5.commands.*;
import com.stiggles.smp5.dungeons.DungeonStartCommand;
import com.stiggles.smp5.entity.Entities;
import com.stiggles.smp5.entity.lostMerchant.InventoryManager;
import com.stiggles.smp5.entity.lostMerchant.LostMerchant;
import com.stiggles.smp5.entity.lostMerchant.MerchantListener;
import com.stiggles.smp5.entity.monsters.CustomSpawns;
import com.stiggles.smp5.entity.monsters.KillMagmaBoss;
import com.stiggles.smp5.entity.npc.*;
import com.stiggles.smp5.entity.npc.dialoguenpc.*;
import com.stiggles.smp5.entity.npc.shopnpcs.*;
import com.stiggles.smp5.items.*;
import com.stiggles.smp5.listeners.AllMiscEvents;
import com.stiggles.smp5.items.armor.AnarchysWardrobe;
import com.stiggles.smp5.items.armor.PeacesSymphony;
import com.stiggles.smp5.items.armor.runArmorCheck;
import com.stiggles.smp5.items.bows.BoomBow;
import com.stiggles.smp5.items.bows.GlowBow;
import com.stiggles.smp5.items.crafting.CustomCrafting;
import com.stiggles.smp5.listeners.*;
import com.stiggles.smp5.managers.BankManager;
import com.stiggles.smp5.managers.Bounty;
import com.stiggles.smp5.managers.MobKillListener;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.stats.Quest;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.scheduler.BukkitRunnable;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;

public class SMP5 extends JavaPlugin implements Listener {
    /*
     *  TODO - REMOVE NPC FROM plugin.yml
     */
    private static SMP5 instance;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public boolean inDungeon = true;
    public BankManager bankManager;
    //public PacketListener packetListener;
    public static SMP5 getInstance() {
        return instance;
    }
    Convergence c;
    private Database database;

    private PlayerManager playerManager;
    private ArrayList<UUID> registeredUUIDs;

    private ArrayList<StigglesNPC> npcs;
    public HashMap<String, StigglesPlayer> online_players;
    private ArrayList<String> toggled = new ArrayList<>();
    //private Plugin plugin = SMP5.getPlugin(SMP5.class);
    Random random = new Random(System.currentTimeMillis());
    private CustomSpawns customSpawns;
    GrapplingHook grapplingHook = new GrapplingHook();
    boolean open = false;

    @Override
    public void onEnable() {

        Cooldown.setupCooldown();

        if (getServer ().getPluginManager ().getPlugin ("Citizens") == null || !getServer ().getPluginManager ().getPlugin ("Citizens").isEnabled()) {
            getLogger ().log (Level.SEVERE, "Citizens 2.0 not found or not enabled");
            getServer ().getPluginManager ().disablePlugin (this);
            return;
        }

        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();
        //Use CitizensAPI

        instance = this;
        database = new Database();

        try {
            database.connect ();
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to connect to database.");
        }


        if (Bukkit.getWorld ("world") == null) {
            Bukkit.getConsoleSender().sendMessage("NVTECH: Could not load world.");
            Bukkit.getServer().shutdown();
        }
        if (Bukkit.getWorld ("sanctuary") == null) {
            new WorldCreator("sanctuary").createWorld();
        }
        if (Bukkit.getWorld ("testdungeon") == null) {
            new WorldCreator("testdungeon").createWorld();
        }


        registeredUUIDs = new ArrayList<>();
        online_players = new HashMap<>();
        playerManager = new PlayerManager();
        //bankManager = new BankManager(this);


        if (database.isConnected()) {
            Bounty.initializeMap(this);
            //LOAD Registered player (UUIDS) from database
            Bukkit.getPluginManager().registerEvents(new LogEventListener(this), this);
            try {
                //ResultSet rs = database.query("SELECT * FROM player;");
                ResultSet rs = database.query("SELECT uuid FROM player;");
                if (rs != null) {
                    while (rs.next()) {
                        UUID uuid = UUID.fromString(rs.getString(1));
                        registeredUUIDs.add(uuid);
                    }
                    rs.close();
                }
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("NVTECH: Failed to fetch players");
            }
        }
        Bukkit.getConsoleSender().sendMessage("Adding NPC");
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
            }.runTaskLater(this, 5);
        }

        customSpawns = new CustomSpawns();
        new BukkitRunnable(){
            public void run(){
                if (rollNumber(1,3) == 2){
                    customSpawns.startCountForBlazingBeast();
                }
            }
        }.runTaskTimer(this, 20 * (60 * (60 * 1)), 20 * (60 * (60 * 1)));

        /*new BukkitRunnable(){
            public void run(){
                for (Player p : Bukkit.getOnlinePlayers()){
                    if (p.getInventory().contains(grapplingHook.getHook())){
                        if (Quest.isQuestComplete(p, Quest.QuestName.ACQUIRE_GRAPPLING_HOOK)) {
                            return;
                        } else {
                            TextComponent textComponent = new TextComponent("[With Great Power Comes Great Responsibility]");
                            textComponent.setColor(net.md_5.bungee.api.ChatColor.DARK_AQUA);

                            // Add hover and click events to the text component
                            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    new ComponentBuilder(ChatColor.DARK_PURPLE + "With Great Power Comes Great Responsibility \n Acquire a grappling hook through a Merchant Marketeer").create()));
                            Quest.questComplete(p, Quest.QuestName.ACQUIRE_GRAPPLING_HOOK, net.md_5.bungee.api.ChatColor.WHITE + p.getName() + " has completed the challenge " + textComponent);
                        }
                    }
                }
            }
        }.runTaskTimer(this, 20, 20);

         */

    }

    @Override
    public void onDisable() {
        instance = null;
        if (database.isConnected()) {
            for (Player p : Bukkit.getOnlinePlayers())
                p.kickPlayer("Server is shutting down!");
        }
        //Update world database
        //BankManager.onDisable();
        //database.runQueue();
        try {
            database.connect();
            if (database.isConnected()) {
                database.runQueue();

                if (database != null)
                    database.disconnect();
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Failed to close db");
        }

        //bankManager.onDisable();

    }

    public DateTimeFormatter getFormatter() {return formatter; }
    public Database getDatabase() { return database; }
    public PlayerManager getPlayerManager() { return playerManager; }
    public int getRandom () { return random.nextInt(); }
    public ArrayList<String> getToggledChatPlayers () { return toggled; }

    public static SMP5 getPlugin () {
        return instance;
    }

    public boolean isOpen () { return open; }
    public void setOpen (boolean b) { open = b; }
    @EventHandler
    public void onCitizensEnable(CitizensEnableEvent ev) {
        Bukkit.getConsoleSender().sendMessage("NV: Citizens Plugin enabled");
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e) {

        //Check if player is registered already
        Player p = e.getPlayer();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            int timeElapsed = 0;
            boolean inc = true;
            @Override
            public void run() {
                Bukkit.getConsoleSender().sendMessage("Spawning..." + timeElapsed);
                Bukkit.getWorld ("world").spawnParticle(Particle.VILLAGER_ANGRY,
                        new Location(p.getWorld(),
                                p.getLocation().getX() + Math.cos (Math.toRadians(timeElapsed * 10)),
                                p.getLocation().getY() + (timeElapsed * 0.025),
                                p.getLocation().getZ() + Math.sin (Math.toRadians(timeElapsed * 10))), 2, 0.1, 0.1, 0.1, null);
                if (inc) {
                    ++timeElapsed;
                    if (timeElapsed > 72)
                        inc = false;
                }
                else {
                    --timeElapsed;
                    if (timeElapsed <= 0)
                        inc = true;
                }
            }
        }, 0, 2);
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

        manager.registerEvents(new OnArmorStandInteract(this), this);
        //manager.registerEvents(this, this);
        //manager.registerEvents (new ConnectionListener(this), this);
        //Bukkit.getPluginManager().registerEvents (packetListener, this);
        //Bukkit.getPluginManager().registerEvents (new NPCListener(this), this);
        manager.registerEvents(new CitizensRightClickEvent(this), this);
        manager.registerEvents(new ElytraEventListener(this), this);
        //manager.registerEvents(new DungeonListener(this), this);
        manager.registerEvents(new MobKillListener(this), this);
        manager.registerEvents(new NetherListener(), this);
        manager.registerEvents(new KillMagmaBoss(this), this);

        //Accent's plugin stuff
        runArmorCheck armorCheck = new runArmorCheck(this);
        CustomCrafting cc = new CustomCrafting(this);
        Bukkit.getPluginManager().registerEvents(new Swords(), this);
        Bukkit.getPluginManager().registerEvents(new AllMiscEvents(), this);
        Bukkit.getPluginManager().registerEvents(new BoomBow(), this);
        Bukkit.getPluginManager().registerEvents(new GlowBow(), this);
        Bukkit.getPluginManager().registerEvents(new Pickaxes(), this);
        Bukkit.getPluginManager().registerEvents(new AnarchysWardrobe(), this);
        Bukkit.getPluginManager().registerEvents(new PeacesSymphony(), this);
        Bukkit.getPluginManager().registerEvents(new Entities(), this);
        Bukkit.getPluginManager().registerEvents(new EndEyeListener(), this);
        Bukkit.getPluginManager().registerEvents(new Pendant(this), this);
        Bukkit.getPluginManager().registerEvents(new MerchantListener(this), this);
        //manager.registerEvents(this, this);
        Bukkit.getScheduler().runTaskTimer(this, CustomSpawns::spawnWitherSkeleton, 20*30, 20 * 60);
        Bukkit.getPluginManager().registerEvents(new CurseListener(this), this);

        try {
            database.connect();
            if (database.isConnected()) {
                manager.registerEvents(new BountyListeners(this), this);
            }
        }
        catch (SQLException e) {

        }

    }

    /**
     * Loads all NPCs into the world and saves them as a StigglesNPC object.
     */
    public void createNPCs () {
        CitizensAPI.getNPCRegistry().deregisterAll();
        npcs = new ArrayList<>();
        //npcs.add (new Ned(this, "Ned", new Location(Bukkit.getWorld("world"), 0, 0, 0)));
        //c = new Convergence(new Location (Bukkit.getWorld ("world"), 0, 100, 0), 1);
        //particle dust 0.71 0.33 0.79 1 -1.90 71.00 -0.00 0.1 0.5 0.1 1 10

        World world = Bukkit.getWorld("world");
        World worldNether = Bukkit.getWorld("world_nether");
        World worldEnd = Bukkit.getWorld("world_the_end");

        npcs.add (new Starry (this, "Starry", new Location(world, -708.5, 67, -1110.5)));
        npcs.add (new EggDONTTake(this, "Francis Smurf", new Location(world, 82.5, 101, 755.5)));
        npcs.add (new DremBot (this, "Drem-Bot", new Location(world, 1154.5, 74, 127.5)));
        npcs.add (new DungeonKeeper(this, "Dungeon Keeper", new Location(world, 1135.5, 78, 156.5)));
        npcs.add (new Mister8Bit(this, "Luke the Fisherman", new Location(world, 774.5, 77, -596.5)));
        npcs.add (new Spiffy (this, "Spiffy", new Location(world, -709.5, 66, -1121)));
        npcs.add (new Astronomer(this, "The Astronomer", new Location(world, -900.5, 120, -1113.5)));
        npcs.add (new Inventor(this, "The Inventor", new Location(world, 1149.5, 74, 120.5)));
        npcs.add (new Philippe(this, "Sir Philippe Alfred", new Location(world, 1128.5, 71, 118.5)));
        npcs.add (new Baggins (this, "Mr. Orangeflips", new Location(world, 99.5, 92, 757.5)));
        npcs.add (new Drem (this, "Captain Beast", new Location(world, 1675.5, 107, -1133.5)));
        npcs.add (new Beachman (this, "Beach Man", new Location (world, -1480.5, 63, 1024.5)));
        npcs.add (new Chickens (this, "Gabe", new Location (world, 788.5, 83, -422.5)));
        npcs.add (new Bear (this, "BearSharken", new Location (world, 540.5, 92, -912.5)));
        npcs.add (new DrTrog (this, "Dr. Trog", new Location(world, 1489.5, 136, -1475.5)));
        npcs.add (new Morabito (this, "Mr. Morabito", new Location(world, -751.5, 66,-1427.5)));
        npcs.add (new Mole (this, "Mole 'a Quacks", new Location(world, 71.5, 111, 784.5)));
        npcs.add (new Tiger (this, "Tigerfist", new Location (world, 45.5, 93, 818.5)));
        npcs.add (new Alejandro(this, "Alejandro", new Location (world, 1252.5, 98, 1487.5)));
        npcs.add (new Ralph (this, "Ralph", new Location(world, 1250.5, 93, 1492)));
        npcs.add (new MaskedStranger(this, "Masked Stranger", new Location(world, -772.5, 157, 1381.5)));
        npcs.add (new Scubadiver(this, "Scuba Diver", new Location(world, 1505.5, 73, -1279.5)));
        npcs.add(new Shrek(this, "Shrek", new Location(world, 739.5, 66, 1162.5)));
        npcs.add(new MindlessGuy(this, "Mindless Guy", new Location(world, 28.5, 91, 855.5)));
        npcs.add(new NetherWizard(this, "Wondrous Wizard", new Location(world, -976.5, 67, -278.5)));
        npcs.add(new YetAnotherWanderer(this, "Weary Traveler", new Location(world, -828.5, 70, -726.5)));
        npcs.add(new AnotherWanderer(this, "Lost Wanderer", new Location(world, -831.5, 68, -736.5)));
        npcs.add(new leadWanderer(this, "Adventurous Explorer", new Location(world, -834.5, 68, -728.5)));
        npcs.add(new EndWizzard(this, "End Explorer", new Location(world, 14.5, 92, 781.5)));
        npcs.add(new AndEvenAnotherWanderer(this, "Confused Hiker", new Location(world, -824.5, 70, -735.5)));

        npcs.add (new Anarcho(this, "Anarcho", new Location(worldNether, 550.5, 221, 236.5)));

        npcs.add (new TheWanderer(this, "The Wanderer", new Location(Bukkit.getWorld("sanctuary"), 35, -60, 9)));
        npcs.add (new Nouveau(this, "Nouveau", new Location (Bukkit.getWorld("sanctuary"), 8.5, -59, 8.5)));
        //Nouveau 52, 132, 746
    }
    public void registerCommands () {
        //Bukkit.getPluginCommand("coins").setExecutor(new CoinCommand());
        //saveDefaultConfig()
        //stats = new PluginFile(this, "stats.yml", "stats.yml");
        //Load important database variables
        Bukkit.getPluginCommand ("loadcitizens").setExecutor (new NPCCommand (this));
        Bukkit.getPluginCommand ("world").setExecutor (new ChangeWorldCommand ());
        Bukkit.getPluginCommand("o").setExecutor(new OpenWorldCommand(this));
        Bukkit.getPluginCommand("start-dungeon").setExecutor (new DungeonStartCommand());
        Bukkit.getPluginCommand("start-dungeon").setExecutor (new DungeonStartCommand());
        Bukkit.getPluginCommand("coins").setExecutor(new CoinCommand(this));
        Bukkit.getPluginCommand("togglecoin").setExecutor(new ToggleCoinChat(this));
        Bukkit.getPluginCommand("smm").setExecutor(new SendMultiMessage (this));
        Bukkit.getPluginCommand("get-items").setExecutor(new GetItems());
        Bukkit.getPluginCommand("alert").setExecutor(new RestartAlertCommand(this));
        Bukkit.getPluginCommand("reset-merchants").setExecutor(new ResetMerchants());
        Bukkit.getPluginCommand("spawn-merchants").setExecutor(new SpawnMerchants());
        Bukkit.getPluginCommand("get-stats").setExecutor(new GetStatsCommand(this));
    }

    public static int rollNumber(int min, int max){
        Random rand = new Random();
        int randomNumber = rand.nextInt(max - min + 1) + min;

        return randomNumber;
    }

    @EventHandler
    public void interact(PlayerInteractEvent event){
        World world = Bukkit.getWorld("world");

        Location potLocation = new Location(world, -144, 43, 865);

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Block block = event.getClickedBlock();
            if (block.getType().equals(Material.SUSPICIOUS_SAND) && block.getX() == -144 && block.getY() == 43 && block.getZ() == 865){
                ItemStack wheel = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta skullMeta = (SkullMeta) wheel.getItemMeta();
                skullMeta.setDisplayName(ChatColor.WHITE + "Scubas Wheel");
                skullMeta.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + ChatColor.ITALIC.toString() + "A wheel that was once on a ship."));
                skullMeta.setLocalizedName("scuba_ship_wheel");

                PlayerProfile p = Bukkit.createPlayerProfile(UUID.randomUUID());
                try {
                    PlayerTextures pt = p.getTextures();
                    pt.setSkin(new URL("https://textures.minecraft.net/texture/6b60171a946b630f46b7b626f4a780bd1a2de9ae3b2bc8a67b6f5bd970eb7"
                    ));
                    p.setTextures(pt);
                }
                catch (MalformedURLException e) {

                }
                skullMeta.setOwnerProfile(p);
                wheel.setItemMeta(skullMeta);
                event.getPlayer().getInventory().addItem(wheel);
            }
        }
    }

    public void shutdownServer(){ Bukkit.shutdown(); }



}
