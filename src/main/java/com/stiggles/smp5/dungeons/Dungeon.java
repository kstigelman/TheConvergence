/**
 * Explain dungeons stuff here
 *
 * @author Kyler Stigelman, Kael Hufford
 */


package com.stiggles.smp5.dungeons;


import com.stiggles.smp5.dungeons.Cuboids.Cuboid;
import com.stiggles.smp5.entity.npc.shopnpcs.DungeonKeeper;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import com.stiggles.smp5.player.StigglesPlayer;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.loot.LootTables;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.time.LocalDateTime;
import java.util.*;

public class Dungeon implements Listener {
    public static final int MAX_PLAYER_COUNT = 4;
    //To be added
    private static final int BASE_DIFFICULTY = 1;
    //private List<Cuboid> dungeonCuboids;
    public static ArrayList<DungeonRoom> roomsFinished;
    private static int difficulty = 1;
    private final int dungeonID;
    //Stores player's main world location before joining the dungeon world.
    private final Map<UUID, Location> oldPlayerLocation;
    //Stores player's main world inventory before joining the dungeon world.
    private final Map<UUID, PlayerInventory> oldPlayerInventory;
    //Manages the games states, rooms, and timers
    //private DungeonStartManager dungeonManager;
    private final List<UUID> playerID;
    private final SMP5 main;
    private final World world;
    //DUNGEON START MANAGER VARS
    private final ArrayList<Player> players;
    private final ArrayList<Player> alivePlayers;
    private final int everySecondTaskID;
    public boolean timerActive = false;
    protected List<DungeonRoom> rooms;
    //World spawn-- This should not change.
    private Location worldSpawn;
    //Player spawn-- The current location where the player will respawn when they die. May not necessarily be world spawn,
    //But in many cases, it probably will be.
    private Location playerSpawn;
    //RECRUITING or STARTED
    private DungeonState state;
    private int currentRoom;
    private int nextRoom;
    private DungeonRoom current;
    private boolean active;
    private int countdown = 0;
    private int timer = 0;
    private boolean started;
    private boolean finished = false;
    private int timeLimit = 420;
    String beganAt;

    public Dungeon(SMP5 main, int id, Location world_spawn) {
        countdown = 60;
        players = new ArrayList<>();
        alivePlayers = new ArrayList<>();
        started = false;

        //END OF DUNGEON START MANAGER CONSTRUCTOR
        dungeonID = id;
        worldSpawn = world_spawn;
        world = worldSpawn.getWorld();
        //Note: By default, playerSpawn will be equal to worldSpawn. It should be like
        //      this to start. After the dungeon begins, this has potential to change,
        //      but unlikely.
        playerSpawn = worldSpawn;
        state = DungeonState.RECRUITING;
        playerID = new ArrayList<>();
        oldPlayerLocation = new HashMap<>();
        oldPlayerInventory = new HashMap<>();
        rooms = new ArrayList<>();
        active = false;

        roomsFinished = new ArrayList<>();
        //Initialize Rooms...
        setCurrentRoom(0);
        setNextRoom(1);

        this.main = main;
        /* this::everySecond() is equivalent to () -> everySecond() */
        everySecondTaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, this::everySecond, 0L, 20L);
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    public SMP5 getMain() {
        return main;
    }


    /**
     *  Cancel the task that runs the main dungeon logic.
     */
    public void cancelTask() {
        Bukkit.getScheduler().cancelTask(everySecondTaskID);
    }
    /**
     * Teleport the player to the world's player spawn point.
     *
     * @param player The player to be spawned.
     */
    public void spawnPlayer(Player player) {
        player.teleport(playerSpawn);
    }

    //From DUNGEON START MANAGER
    public void everySecond() {
        //if (alivePlayers.isEmpty())
        //    return;
        if (state == DungeonState.RECRUITING) {
            if (countdown > 0)
                --countdown;

            if (countdown % 10 == 0 || countdown <= 5)
                sendPlayersMessage(ChatColor.YELLOW + "Dungeon begins in " + countdown);

            if (countdown == 0)
                start();
        } else if (state.equals(DungeonState.STARTED)) {
            if (timerActive) {
                if (timer == 0)
                    started = true;
                ++timer;

                if (timer == timeLimit - 60)
                    sendPlayersMessage(ChatColor.RED + "[Warning] You have 60 seconds left to complete this dungeon.");
                if (timer >= timeLimit)
                    failed();
            }
            Bukkit.getConsoleSender().sendMessage("Time active: " + timer);
            if (alivePlayers.isEmpty()) {
                reset();
                timerActive = false;

                this.getPlayers().clear();
                this.getAlivePlayers().clear();
            }
            //Update dungeon logic
            update();
        }
        else if (state.equals(DungeonState.END) || state.equals(DungeonState.FAILED)) {
            if (countdown > 0) {
                --countdown;
                return;
            }
            //put dungeon keeper stuff here?
            ArrayList<Player> temp_players = new ArrayList<>(getPlayers());
            for (Player p : temp_players) {
                StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(p.getUniqueId());
                leave(p);
                if (sp != null)
                    sp.addDungeonComplete(world.getName(), beganAt, timer, 0, state.equals(DungeonState.END));
            }
            DungeonManager.removeDungeon("testdungeon");
            Bukkit.getScheduler().cancelTask(everySecondTaskID);

        }
    }

    //public void resetPlayer (Player player) {
        //leave (player);
        //player.setGameMode(GameMode.SURVIVAL);
        //player.setInvisible(false);
        //Location location = player.getBedSpawnLocation();
        //player.teleport(Objects.requireNonNullElseGet(location, () -> Bukkit.getWorld("world").getSpawnLocation()));
    //}
    public void end () {
        state = DungeonState.END;
        countdown = 10;
        timerActive = false;

        int minutes = timer / 60;
        int seconds = timer % 60;
        String timeString = minutes + "m" + seconds + "s";
        sendPlayersMessage(ChatColor.GOLD + "Dungeon completed in " + timeString + ". Teleporting you in 10 seconds.");
    }
    /**
     * Updates the player count and executes join functions
     *
     * @param e The player join server event
     */
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        if (true)
            return;
        Player p = e.getPlayer();
        //Player is joining the dungeon world
        if (p.getWorld().getName().equals(world.getName()))
            join(p);
            //Player is leaving the dungeon world
        else if (e.getFrom().getName().equals(world.getName()))
            leave(p);
    }

    public void join(Player p) {
        //dungeon.playerJoin(p);
        Bukkit.getServer().getConsoleSender().sendMessage("Dungeon: " + p.getName() + " has joined!");
        oldPlayerLocation.put(p.getUniqueId(), p.getLocation());
        //oldPlayerInventory.put(p.getUniqueId(), p.getInventory());

        this.addPlayerID(p);
        this.spawnPlayer(p);

        p.setGameMode(GameMode.ADVENTURE);
        p.setInvisible(false);
        p.setAllowFlight(false);
        p.setFlying(false);

        if (state.equals(DungeonState.RECRUITING)) {
            if (players.isEmpty())
                countdown = 60;

            if (getPlayerCount() == getMaxPlayerCount() && countdown >= 0)
                countdown = 10;

            Bukkit.getConsoleSender().sendMessage("Dungeon is starting in " + countdown + ". Other players may join during this time.");
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20000000, 100, true));
        }
        this.players.add(p);
        this.alivePlayers.add(p);

        for (Player thisPlayer : players) {
            p.sendMessage(ChatColor.GREEN + p.getName() + " has joined the dungeon (" + players.size() + "/4)");
        }
    }

    public void leave(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
        p.setInvisible(false);
        p.setAllowFlight(false);
        p.setFlying(false);
        p.removePotionEffect(PotionEffectType.REGENERATION);

        if (getState().equals(DungeonState.RECRUITING)) {
            //If a player leaves, allow for 30 more seconds for players to join.
            if (countdown >= 0)
                countdown = 30;
        }
        this.getPlayers().remove(p);
        this.getAlivePlayers().remove(p);

        this.removePlayerID(p);
        Location oldLocation = oldPlayerLocation.remove(p.getUniqueId());
        //PlayerInventory inv = oldPlayerInventory.remove(p.getUniqueId());
        //Clear current inventory
        //p.getInventory().clear();

        //Set old inventory
        //p.getInventory().setContents(inv.getContents());
        //p.getInventory().setArmorContents(inv.getArmorContents());
        //p.getInventory().setExtraContents(inv.getExtraContents());

        //TP player back to their previous location
        p.teleport(oldLocation);
        //dungeon.playerQuit(p);
    }

    @EventHandler
    public void onRespawn (PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (!p.getWorld().getName().equals(getWorld().getName()))
            return;

        e.setRespawnLocation(worldSpawn);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (!p.getWorld().getName().equals(getWorld().getName()))
            return;

        if (!e.getDrops().isEmpty ()) {
            DungeonKeeper dungeonKeeper = (DungeonKeeper) NPCManager.getNPCByName ("Dungeon Keeper");
            if (e.getEntity().getInventory().isEmpty() || dungeonKeeper == null)
                return;
            //dungeonKeeper.GiveInventory(e.getEntity().getInventory());
            //dungeonKeeper.giveInventory(e.getEntity());
            dungeonKeeper.giveDrops(e.getEntity().getUniqueId(), e.getEntity().getInventory().getContents());
            dungeonKeeper.sendMessage(e.getEntity(), "I can give you your items back... for a price. Come talk to me by tonight or I will sell your items.");

            e.getDrops().clear();
        }
        alivePlayers.remove(p);

        p.setAllowFlight(true);
        p.setFlying(true);
        p.setInvisible(true);
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20000000, 100, true));

        if (this.getAlivePlayers().isEmpty())
            failed();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!p.getWorld().getName().equals(world.getName()))
            return;

        //if (isStarted())
        //playerQuit(p);
        leave(p);
    }

    public void sendPlayersMessage(String msg) {
        for (Player p : players)
            p.sendMessage(msg);
    }

    public void sendPlayersSound(Sound sound, float volume, float pitch) {
        for (Player p : players)
            p.playSound(p, sound, 1, 1);
    }

    public int getTime() {
        return timer;
    }

    public int getCountdown() {
        return countdown;
    }

    public boolean isStarted() {
        return started;
    }
    //END OF DUNGEON START MANAGER


    /**
     * Retrieve the dungeon's current difficulty level.
     *
     * @return The difficulty.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Set the dungeon's difficulty to a specified amount.
     *
     * @param amount The new difficulty.
     */
    public void setDifficulty(int amount) {
        difficulty = amount;
    }

    /**
     * Set the dungeon's difficulty based on the fixed formula.
     *
     * @return The new difficulty.
     */
    public int setDifficulty() {
        return difficulty = ((BASE_DIFFICULTY * getPlayerCount() * 3) / 4) + 1;
    }

    public void update() {
        int room = currentRoom;
        for (int index = currentRoom; index < rooms.size(); ++index) {
            if (rooms.get(index).checkPlayer(this.getAlivePlayers().get(0), rooms.get(index).getBoundary())) {
                if (room > currentRoom) {
                    rooms.get(currentRoom).closeExit();
                    currentRoom = room;
                    current = rooms.get(currentRoom);
                    Bukkit.getConsoleSender().sendMessage("Player has advanced to the next room!");
                }
                rooms.get(index).update();
                Bukkit.getConsoleSender().sendMessage("Current: " + current.type.toString());
                return;
            }
            ++room;
        }
    }


    public void goToNextRoom() {
        ++currentRoom;
        nextRoom = currentRoom + 1;

        if (this.getCurrentRoomIndex() >= rooms.size() - 1) {
            currentRoom = rooms.size() - 1;
            nextRoom = rooms.size() - 1;
        }
        current = getNextRoom();

    }

    /**
     * Retrieve the player spawn location.
     *
     * @return The location object of playerSpawn
     */
    public Location getPlayerSpawn() {
        return playerSpawn;
    }

    /**
     * Retrieve the world spawn location.
     *
     * @return The location object of worldSpawn.
     */
    public Location getWorldSpawn() {
        return worldSpawn;
    }

    /**
     * Set the world's spawn location based on the world and given coordinates.
     *
     * @param location A location object containing the dungeon world and x, y, z coordinates.
     */
    public void setWorldSpawn(Location location) {
        worldSpawn = location;
    }

    /**
     * Retrieve the world the dungeon is located in.
     *
     * @return The world object
     */
    public World getWorld() {
        return worldSpawn.getWorld();
    }

    /**
     * Returns how many players are in the dungeon.
     *
     * @return The size of the players ArrayList.
     */
    public int getPlayerCount() {
        return players.size();
    }

    /**
     * Return the maximum number of players that may join the dungeon.
     *
     * @return MAX_PLAYER_COUNT
     */
    public int getMaxPlayerCount() {
        return MAX_PLAYER_COUNT;
    }

    /**
     * Retrieve the ArrayList index of the room the players are currently in.
     *
     * @return integer index
     */
    public int getCurrentRoomIndex() {
        return currentRoom;
    }

    public Material getBlockBelowPlayer(Player p) {
        return getWorld().getBlockAt((int) p.getLocation().getX(), (int) p.getLocation().getZ() - 1, (int) p.getLocation().getZ()).getType();
    }

    public RoomType getPlayerRoom() {
        return getRoomType(getBlockBelowPlayer(this.getAlivePlayers().get(0)));
    }

    public boolean isSameRoom() {
        if (playerID.isEmpty())
            return false;

        return getPlayerRoom().equals(current.type);
    }

    public void createRoomFromType() {

    }

    public void checkCurrentRoom() {
        ArrayList<Player> alive = this.getAlivePlayers();
        if (alive.isEmpty())
            return;

        getBlockBelowPlayer(alive.get(0));
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    //public ArrayList<Player> getPlayers () { return dungeonManager.getPlayers (); }

    public ArrayList<Player> getAlivePlayers() {
        return alivePlayers;
    }

    public RoomType getRoomType(Material m) {
        if (m == Material.LIME_CONCRETE)
            return RoomType.LOBBY;
        if (m == Material.BLUE_CONCRETE)
            return RoomType.FILLER;
        if (m == Material.LIGHT_BLUE_CONCRETE)
            return RoomType.FIND;
        if (m == Material.CYAN_CONCRETE)
            return RoomType.PARKOUR;
        if (m == Material.YELLOW_CONCRETE)
            return RoomType.COLLECTION;
        if (m == Material.GRAY_CONCRETE)
            return RoomType.STAGE_CONNECTION;
        if (m == Material.PINK_CONCRETE)
            return RoomType.TARGET;
        if (m == Material.RED_CONCRETE)
            return RoomType.MOB;
        if (m == Material.MAGENTA_CONCRETE)
            return RoomType.WAVE;
        if (m == Material.BLACK_CONCRETE)
            return RoomType.BOSS;
        if (m == Material.ORANGE_CONCRETE)
            return RoomType.FINAL;
        return RoomType.NULL;
    }

    /**
     * Retrieve the ArrayList index of the room the players will go to next.
     *
     * @return integer index
     */
    public int getNextRoomIndex() {
        return nextRoom;
    }

    /**
     * Retrieve the current room the players are in.
     *
     * @return DungeonRoom that the players are in
     */
    public DungeonRoom getCurrentRoom() {
        return rooms.get(currentRoom);
    }

    /**
     * Set the room index of the room the players are currently in.
     *
     * @param index integer value
     */
    public void setCurrentRoom(int index) {
        currentRoom = index;
    }

    /**
     * Retrieve the next room the players will go to.
     *
     * @return DungeonRoom that players will advance to next.
     */
    public DungeonRoom getNextRoom() {
        return rooms.get(nextRoom);
    }

    /**
     * Set the room index of the room the players are going to next.
     *
     * @param index integer value
     */
    public void setNextRoom(int index) {
        nextRoom = index;

        if (nextRoom > rooms.size())
            nextRoom = currentRoom;
    }

    /**
     * Retrieve all the rooms in a dungeon.
     *
     * @return A list of all the DungeonRooms
     */
    public List<DungeonRoom> getRooms() {
        return rooms;
    }

    /**
     * Set player's next spawn location with given coordinates
     *
     * @param x coordinate
     * @param y coordinate
     * @param z coordinate
     */
    public void setPlayerSpawn(float x, float y, float z) {
        playerSpawn = new Location(worldSpawn.getWorld(), x, y, z);
    }

    /**
     * Set world spawn location with given coordinates within the same world.
     *
     * @param x coordinate
     * @param y coordinate
     * @param z coordinate
     */
    public void setWorldSpawn(float x, float y, float z) {
        worldSpawn = new Location(worldSpawn.getWorld(), x, y, z);
    }

    /**
     * Retrieve the list of players active in the dungeon.
     *
     * @return ArrayList of Players.
     */
    public List<UUID> getPlayerIDs() {
        return playerID;
    }

    /**
     * Add a new player to the Player ArrayList.
     *
     * @param p The player to be added.
     */
    public void addPlayerID(Player p) {
        playerID.add(p.getUniqueId());
    }

    /**
     * Remove a player from the Player ArrayList
     *
     * @param p The player to be removed.
     */
    public void removePlayerID(Player p) {
        playerID.remove(p.getUniqueId());
    }

    /**
     * Prepare the player for joining the dungeon. Will back up the player's information and
     * set a new location and inventory.
     *
     * @param p The player joining the dungeon.
     */
    public void playerJoin(Player p) {
        Bukkit.getServer().getConsoleSender().sendMessage("Dungeon: " + p.getName() + " has joined!");
        oldPlayerLocation.put(p.getUniqueId(), p.getLocation());
        oldPlayerInventory.put(p.getUniqueId(), p.getInventory());

        this.addPlayerID(p);
        this.spawnPlayer(p);

        //dungeonManager.join(p);
    }

    /**
     * Check if the dungeon is marked as active or not
     *
     * @return The boolean active
     */
    public boolean isActive() {
        return active;
    }

    //Make a checking system for if player quits during a dungeon.

    /**
     * Prepare the player for returning to the main world. Restores their information
     * to its original state, then teleports them back.
     *
     * @param p The player that is leaving the dungeon.
     */
    public void playerQuit(Player p) {
        //dungeonManager.leave (p);

        this.removePlayerID(p);
        Location oldLocation = oldPlayerLocation.remove(p.getUniqueId());
        PlayerInventory inv = oldPlayerInventory.remove(p.getUniqueId());
        //Clear current inventory
        p.getInventory().clear();

        //Set old inventory
        p.getInventory().setContents(inv.getContents());
        p.getInventory().setArmorContents(inv.getArmorContents());
        p.getInventory().setExtraContents(inv.getExtraContents());

        //TP player back to their previous location
        p.teleport(oldLocation);
    }

    /**
     * Retrieve the Dungeon Manager, which holds and operates important dungeon related events.
     *
     * @return Dungeon manager.
     */
    public DungeonStartManager getDungeonManager() {
        return null;//dungeonManager;
    }

    /**
     * Get the current state of the dungeon:
     * (RECRUITING, STARTED).
     *
     * @return state
     */
    public DungeonState getState() {
        return state;
    }

    public void setState(DungeonState state) {
        this.state = state;
    }

    public boolean isFull() {
        return playerID.size() == MAX_PLAYER_COUNT;
    }

    public void killMobs() {
        List<Entity> entities = getWorldEntities();
        if (entities == null || entities.isEmpty())
            return;

        for (Entity entity : entities) {
            //Add other entities that should not be killed
            if (!(entity instanceof Player) && !(entity instanceof ItemFrame) && !(entity instanceof ArmorStand))
                entity.remove();
        }
    }

    public List<Entity> getWorldEntities() {
        if (worldSpawn.getWorld() == null)
            return null;
        return worldSpawn.getWorld().getEntities();
    }

    public void start() {
        active = true;
        currentRoom = 0;
        nextRoom = 1;
        state = DungeonState.STARTED;
        current = rooms.get(0);
        current.openExit();
        timerActive = true;
        setDifficulty();
        for (Player p : alivePlayers) {
            p.teleport(new Location(world, 43.5, -42, 190.5));
            p.sendMessage(ChatColor.GRAY + "Good luck. You have " + timeLimit / 60 + " minutes to complete the dungeon.");
            p.removePotionEffect(PotionEffectType.REGENERATION);
        }
        beganAt = LocalDateTime.now().format(main.getFormatter());
        //rooms.get(currentRoom).update();
        //Remove barriers at entrance
    }

    /**
     * Resets the dungeon for a new run.
     */
    public void reset() {
        for (Player p : getPlayers())
            leave (p);

        active = false;

        killMobs();

        if (rooms != null && !rooms.isEmpty()) {
            for (DungeonRoom room : rooms) {
                room.resetRoom();
            }
        }
        state = DungeonState.END;
        //Bukkit.broadcastMessage(ChatColor.YELLOW + "The dungeon has been reset! Do /start-dungeon to begin!");

    }

    public void failed() {
        for (Player p : getPlayers()) {
            p.setAllowFlight(true);
            p.setFlying(true);
            p.setInvisible(true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20000000, 100, true));
        }
        state = DungeonState.FAILED;
        countdown = 10;
        timerActive = false;
        sendPlayersMessage(ChatColor.DARK_RED + "Dungeon failed. Teleporting you in 10 seconds.");
            //playerQuit(p);
    }

    /**
     * Describes the variety of different types of rooms there can be.
     */
    protected enum RoomType {
        LOBBY,
        FILLER,
        FIND,
        PARKOUR,
        COLLECTION,
        STAGE_CONNECTION,
        TARGET,
        LEVEL_CONNECTION,
        MOB,
        WAVE,
        BOSS,
        FINAL,
        NULL
    }

    /* Begin DungeonRoom class definitions. Each one corresponds to a different RoomType and has unique
     * properties. Fundamentally, each one checks that the user is inside the room, controls opening and
     * closing the entrance and exits, and spawning mobs.
     */
    protected abstract class DungeonRoom {
        private final Material TRIGGER_BLOCK = Material.BROWN_GLAZED_TERRACOTTA;
        private final Material ENTRANCE_BLOCK = Material.WHITE_GLAZED_TERRACOTTA;
        private final Material EXIT_BLOCK = Material.BLACK_GLAZED_TERRACOTTA;
        private final Cuboid boundary;
        private final Cuboid entrance;
        private final Cuboid exit;
        private final Cuboid trigger;
        protected Material fillType = Material.POLISHED_DEEPSLATE;
        protected byte doorOffset = 3;
        protected boolean enteredRoom = false;
        protected boolean triggered = false;
        protected DungeonRoom next;
        private Location spawnLocation;
        private boolean finished = false;
        private boolean roomActive;
        private RoomType type;

        //Constructors
        public DungeonRoom(Cuboid boundary) {
            this(boundary, boundary.getCenter());
        }

        public DungeonRoom(Cuboid boundary, Location spawn) {
            this.boundary = boundary;
            this.spawnLocation = spawn;


            trigger = findTrigger();
            entrance = findEntrance();
            exit = findExit();


            Location l = new Location(getWorld(), 0.5, 0.5, 0.5);
            if (trigger != null)
                l = trigger.getCenter();

            this.spawnLocation = new Location(getWorld(), l.getBlockX() + 0.5, l.getBlockY() - 0.5, l.getBlockZ() + 0.5);
        }

        public DungeonRoom(Cuboid boundary, Cuboid entrance, Location spawn) {
            //Sometimes, the trigger may be the entire room. Also, there may be only 1 entrance/exit (For example, lobby room).
            this.boundary = boundary;
            this.trigger = boundary;
            this.spawnLocation = spawn;
            this.entrance = entrance;
            this.exit = entrance;
        }

        public DungeonRoom(Cuboid boundary, Cuboid trigger, Cuboid entrance, Cuboid exit, Location spawn) {
            this.boundary = boundary;
            this.spawnLocation = spawn;
            this.trigger = trigger;
            this.entrance = entrance;
            this.exit = exit;
        }


        public Cuboid getBoundary() {
            return boundary;
        }

        /**
         * Locates the defined trigger (COMMAND_BLOCK) in the cuboid boundary.
         *
         * @return The 3x3 cuboid trigger
         */
        public Cuboid findTrigger() {
            for (Block b : boundary) {
                if (b.getType().equals(TRIGGER_BLOCK)) {
                    return new Cuboid(getWorld(), b.getLocation().getBlockX() - 1, b.getLocation().getBlockY() + 2, b.getLocation().getBlockZ() - 1,
                            b.getLocation().getBlockX() + 1, b.getLocation().getBlockY() + 5, b.getLocation().getBlockZ() + 1);
                }
            }
            return null;
        }

        /**
         * Locates the defined entrance (WHITE_GLAZED_TERRACOTTA) in the cuboid boundary.
         *
         * @return The 3x1 cuboid
         */
        public Cuboid findEntrance() {
            ArrayList<Location> blocks = new ArrayList<>();
            for (Block b : boundary) {
                if (b.getType().equals(ENTRANCE_BLOCK)) {
                    blocks.add(b.getLocation());
                    //return new Cuboid (getWorld(), b.getLocation().getBlockX(), b.getLocation().getBlockY() + 2, b.getLocation().getBlockZ(),
                    //        b.getLocation().getBlockX(), b.getLocation().getBlockY() + 5, b.getLocation().getBlockZ());
                }
            }

            if (blocks.size() == 1) {
                Location l = blocks.get(0);
                return new Cuboid(getWorld(), l.getBlockX(), l.getBlockY() + 2, l.getBlockZ(),
                        l.getBlockX(), l.getBlockY() + 2 + doorOffset, l.getBlockZ());
            } else if (blocks.size() == 2) {
                Location l1 = blocks.get(0);
                Location l2 = blocks.get(1);

                int dX = l1.getBlockX() - l2.getBlockX();
                int dZ = l1.getBlockZ() - l2.getBlockZ();

                return new Cuboid(getWorld(), l1.getBlockX(), l1.getBlockY() + 2, l1.getBlockZ(),
                        l1.getBlockX() - dX, l1.getBlockY() + 2 + doorOffset, l1.getBlockZ() - dZ);
            }

            return null;
        }

        /**
         * Locates the defined exit (BLACK_GLAZED_TERRACOTTA) in the cuboid boundary.
         *
         * @return The 3x1 cuboid
         */
        public Cuboid findExit() {
            ArrayList<Location> blocks = new ArrayList<>();
            for (Block b : boundary) {
                if (b.getType().equals(EXIT_BLOCK)) {
                    blocks.add(b.getLocation());
                    //return new Cuboid (getWorld(), b.getLocation().getBlockX(), b.getLocation().getBlockY() + 2, b.getLocation().getBlockZ(),
                    //        b.getLocation().getBlockX(), b.getLocation().getBlockY() + 5, b.getLocation().getBlockZ());
                }
            }
            if (blocks.size() == 1) {
                Location l = blocks.get(0);
                return new Cuboid(getWorld(), l.getBlockX(), l.getBlockY() + 2, l.getBlockZ(),
                        l.getBlockX(), l.getBlockY() + 2 + doorOffset, l.getBlockZ());
            } else if (blocks.size() == 2) {
                Location l1 = blocks.get(0);
                Location l2 = blocks.get(1);

                int dX = l1.getBlockX() - l2.getBlockX();
                int dZ = l1.getBlockZ() - l2.getBlockZ();

                return new Cuboid(getWorld(), l1.getBlockX(), l1.getBlockY() + 2, l1.getBlockZ(),
                        l1.getBlockX() - dX, l1.getBlockY() + 2 + doorOffset, l1.getBlockZ() - dZ);
            }
            return null;
        }

        public Cuboid getEntrance() {
            return entrance;
        }

        public Cuboid getExit() {
            return exit;
        }

        public Material getFillType() {
            return fillType;
        }

        public Cuboid getTrigger() {
            return trigger;
        }

        public RoomType getType() {
            return type;
        }

        public void setType(RoomType type) {
            this.type = type;
        }

        public void setNextRoom(DungeonRoom room) {
            next = room;
        }

        public void openEntrance() {
            if (entrance == null)
                return;
            for (Block block : entrance)
                block.setType(Material.AIR);
        }

        public void closeEntrance() {
            if (entrance == null)
                return;
            for (Block block : entrance)
                block.setType(fillType);
        }

        public void openExit() {
            if (exit == null)
                return;
            for (Block block : exit)
                block.setType(Material.AIR);
        }

        public void closeExit() {
            if (exit == null)
                return;
            for (Block block : exit)
                block.setType(fillType);
        }

        public void update() {
            if (finished)
                return;
            if (!enteredRoom) {
                if (checkAllPlayers(boundary)) {
                    onPlayerEnter(alivePlayers.get(0));
                    enteredRoom = true;
                }
                return;
            }
            if (trigger == null)
                return;
            if (!triggered)
                if (checkPlayer(alivePlayers.get(0), trigger))
                    onTrigger();

            //if(checkAllPlayers(trigger));
            /*if (checkAllPlayers(next.trigger)) { //Next time you work on this, you are gonna work here. The way oyu implemented this
                                                //Does not require using next. So, figure out how to check only current trigger. (Look in dungeon.update)
                active = false;
                finished = true;
                next.onTrigger();
            }*/
        }

        public boolean containsAllPlayers() {
            for (Player p : alivePlayers) {
                if (!boundary.contains(p.getLocation()))
                    return false;
            }
            return true;
        }

        public abstract void onPlayerEnter(Player player);

        public void onTrigger() {
            if (checkAllPlayers(trigger)) {
                roomActive = true;
                closeEntrance();
                closeExit();
                triggered = true;
            }
        }

        public boolean checkPlayer(Player p, Cuboid container) {
            return container.contains(p.getLocation());
        }

        public boolean checkAllPlayers(Cuboid container) {
            for (Player player : alivePlayers)
                if (!container.contains(player.getLocation()))
                    return false;
            return true;
        }

        public Location getSpawnLocation() {
            return spawnLocation;
        }

        public void setSpawnLocation(Location l) {
            spawnLocation = l;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean val) {
            finished = val;
        }

        public boolean isRoomActive() {
            return roomActive;
        }

        public void setRoomActive(boolean val) {
            roomActive = val;
        }

        public boolean hasEnteredRoom() {
            return enteredRoom;
        }

        public boolean isTriggered() {
            return triggered;
        }

        public boolean contains(Player p) {
            return getBoundary().contains(p.getLocation());
        }

        public void teleportRoomSpawn(Player p) {
            p.teleport(spawnLocation);
        }

        public abstract void resetRoom();

    }

    protected abstract class SpawnerDungeonRoom extends DungeonRoom {
        private final Map<Block, String> spawnerBlocks;

        protected boolean spawned;
        protected int waveCount = 5;
        protected int timeBetweenWaves = 8;
        protected int countdown = 0;
        protected int currentWave = 0;


        public SpawnerDungeonRoom(Cuboid boundary) {
            super(boundary);
            spawnerBlocks = new HashMap<>();
            addSpawnerBlocks();
        }

        public SpawnerDungeonRoom(Cuboid boundary, Cuboid trigger, Cuboid entrance, Cuboid exit, Location spawn) {
            super(boundary, trigger, entrance, exit, spawn);
            spawnerBlocks = new HashMap<>();
            addSpawnerBlocks();
        }

        public boolean hasSpawned() {
            return spawned;
        }

        public void addSpawnerBlocks() {
            for (Block block : getBoundary()) {
                if (block.getType().equals(Material.LIGHT_GRAY_CANDLE))
                    spawnerBlocks.put(block, "zombie");
                else if (block.getType().equals(Material.GRAY_CANDLE))
                    spawnerBlocks.put(block, "skeleton");
                else if (block.getType().equals(Material.CYAN_CANDLE))
                    spawnerBlocks.put(block, "creeper");
            }
        }

        public void spawnMobs() {
            if (spawnerBlocks == null || spawnerBlocks.isEmpty())
                return;
            for (Block block : spawnerBlocks.keySet())
                DungeonMobs.spawnDungeonMobs(block.getLocation().add(0, 1, 0), spawnerBlocks.get(block));
            spawned = true;
        }
    }

    protected abstract class MultiRoom extends DungeonRoom {
        public MultiRoom(Cuboid boundary, Cuboid trigger, Cuboid entrance, Cuboid exit, Location spawn) {
            super(boundary, trigger, entrance, exit, spawn);
        }
    }

    protected class LobbyRoom extends DungeonRoom {
        private boolean start;

        public LobbyRoom(Cuboid boundary) {
            super(boundary);
            setType(RoomType.LOBBY);
            fillType = Material.BARRIER;
            doorOffset = 6;
            closeExit();
            start = false;
        }

        @Override
        public void onPlayerEnter(Player player) {
        }

        @Override
        public void onTrigger() {
            openExit();
            triggered = true;
        }

        @Override
        public void resetRoom() {
            closeExit();
            start = false;
            triggered = false;
        }

        @Override
        public void update() {
            if (!started)
                return;
            if (!containsAllPlayers())
                return;
            /*if (!triggered)
                onTrigger();*/
            super.update();
        }
    }

    protected class FillerRoom extends DungeonRoom {
        public FillerRoom(Cuboid boundary) {
            super(boundary);
            setType(RoomType.FILLER);
            openExit();
        }

        @Override
        public void onPlayerEnter(Player player) {
        }


        @Override
        public void resetRoom() {
            openEntrance();
            openExit();
        }
    }

    protected class FindRoom extends DungeonRoom {
        //public Key;
        public FindRoom(Cuboid boundary) {
            super(boundary);
            setType(RoomType.FIND);
        }

        @Override
        public void onPlayerEnter(Player player) {
        }

        @Override
        public void resetRoom() {
        }

        @Override
        public void update() {
        }
    }

    protected class ParkourRoom extends DungeonRoom {
        //public Key;

        public ParkourRoom(Cuboid boundary) {
            super(boundary);
            setType(RoomType.PARKOUR);
        }

        @Override
        public void onPlayerEnter(Player player) {
        }

        @Override
        public void resetRoom() {
        }

        @Override
        public void update() {
            if (!containsAllPlayers()) {
            }
        }
    }

    protected class CollectionRoom extends WaveRoom {



        public void getChests() {
            int count = 0;
            for (Block block : getBoundary()) {
                if (block.getType().equals(Material.CHEST)) {
                    Chest chest = (Chest) block.getState();
                    chest.setLootTable(LootTables.ABANDONED_MINESHAFT.getLootTable());
                    chest.update();
                    ++count;
                }
            }
            if (count != 0)
                Bukkit.getConsoleSender().sendMessage("Found " + count + " chests");

        }
        public CollectionRoom(Cuboid boundary) {
            this(boundary, 5, 8);
        }

        public CollectionRoom(Cuboid boundary, int count, int timeBetween) {
            super(boundary);
            setType(RoomType.COLLECTION);

            getChests();

        }

        @Override
        public void onPlayerEnter(Player player) {
            closeEntrance();
            closeExit();
        }

        @Override
        public void resetRoom() {
            openEntrance();
            closeExit();
        }

        @Override
        public void update() {
            super.update();
            if (currentWave != waveCount) {
                if (countdown <= 0) {
                    spawnNextWave();
                    countdown = timeBetweenWaves;
                } else
                    --countdown;
                return;
            }
            openExit();
        }

        public void spawnNextWave() {
            //This could be different if the mobs are different each wave.
            spawnMobs();
            ++currentWave;
        }
    }

    protected class TargetRoom extends SpawnerDungeonRoom {
        public HashMap<Block, Boolean> targetBlocks;
        private int count = 0;
        private int startTime = 0;
        private boolean open = false;

        public TargetRoom(Cuboid boundary) {
            super(boundary);
            setType(RoomType.TARGET);
            targetBlocks = new HashMap<>();
            getTargetBlocks();
            openEntrance();
            closeExit();
        }

        @Override
        public void onPlayerEnter(Player player) {
            startTime = timer;
            for (Player p : getAlivePlayers()) {
                p.getInventory().addItem(new ItemStack(Material.BOW));
                p.getInventory().addItem(new ItemStack(Material.ARROW, 5));
            }
        }

        @Override
        public void resetRoom() {
            for (Block block : targetBlocks.keySet())
                block.setType(Material.TARGET);
            openEntrance();
            closeExit();
        }

        public void getTargetBlocks() {
            for (Block block : getBoundary())
                if (block.getType().equals(Material.TARGET))
                    targetBlocks.put(block, false);
            Bukkit.getConsoleSender().sendMessage("Found " + targetBlocks.size() + " blocks");
        }

        public boolean checkTargets() {
            for (Block block : targetBlocks.keySet()) {
                if (targetBlocks.get(block))
                    continue;

                Collection<Entity> c = world.getNearbyEntities(block.getLocation(), 0.8, 0.8, 0.8);
                if (c.isEmpty())
                    continue;

                for (Entity e : c) {
                    if (e instanceof Arrow) {
                        targetBlocks.put(block, true);
                        ++count;
                        sendPlayersMessage("Hit a target! " + count + "/" + targetBlocks.size());
                        sendPlayersSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                        break;
                    }
                }
            }
            return count == targetBlocks.size();
        }

        public void onHitAllTargets() {
            targetBlocks.clear();
            openExit();
            sendPlayersMessage(ChatColor.GREEN + "An entrance has opened!");
            sendPlayersSound(Sound.ENTITY_PLAYER_LEVELUP, 1, 1.5f);
            open = true;
        }

        @Override
        public void update() {
            super.update();
            if (!targetBlocks.isEmpty() && checkTargets())
                onHitAllTargets();
            if (!open && (timer - startTime) % 20 == 0)
                spawnMobs();
        }
    }

    protected class MobRoom extends SpawnerDungeonRoom {
        public MobRoom(Cuboid boundary) {
            super(boundary);
            setType(RoomType.MOB);

            waveCount = 1;
            timeBetweenWaves = 0;

            openEntrance();
            closeExit();
        }

        @Override
        public void onPlayerEnter(Player player) {
            if (!hasSpawned())
                spawnMobs();
            openExit();
        }


        @Override
        public void resetRoom() {
            openEntrance();
            closeExit();
        }
    }

    protected class WaveRoom extends SpawnerDungeonRoom {
        public WaveRoom(Cuboid boundary) {
            this(boundary, 5, 8);
        }

        public WaveRoom(Cuboid boundary, int count, int timeBetween) {
            super(boundary);
            setType(RoomType.WAVE);
            waveCount = count;
            timeBetweenWaves = timeBetween;
            openEntrance();

            closeExit ();
        }

        @Override
        public void onPlayerEnter(Player player) {
            closeEntrance();
            closeExit();
        }

        @Override
        public void resetRoom() {
            openEntrance();
            closeExit();
        }

        @Override
        public void update() {
            super.update();
            if (currentWave != waveCount) {
                if (countdown <= 0) {
                    spawnNextWave();
                    countdown = timeBetweenWaves;
                } else
                    --countdown;
                return;
            }
            openExit();
        }

        public void spawnNextWave() {
            //This could be different if the mobs are different each wave.
            spawnMobs();
            ++currentWave;
        }

    }

    protected class BossRoom extends SpawnerDungeonRoom {

        private final Cuboid bossTrigger;
        private final Cuboid bossExit;
        private Entity boss;
        private boolean bossSpawned = false;
        private boolean killed = false;
        Cuboid escape = new Cuboid (world, 53, -57, 101, 53, -54, 101);

        public BossRoom(Cuboid boundary, Cuboid bossTrigger, Cuboid bossExit) {
            super(boundary);
            setType(RoomType.BOSS);
            this.bossTrigger = bossTrigger;
            doorOffset = 8;
            findEntrance();
            findExit();
            openEntrance();
            closeExit();
            this.bossExit = bossExit;
            killed = false;
            for (Block block : escape) {
                block.setType(Material.AIR);
            }
        }

        public void openBossExit() {
            for (Block block : bossExit)
                block.setType(Material.LADDER);
        }

        public void closeBossExit() {
            for (Block block : bossExit)
                block.setType(Material.AIR);
        }

        public void playerTriggerEnter() {
            closeEntrance();
            closeExit();
            //spawnBoss ();
        }

        public Location findBossTrigger() {
            for (Block b : getBoundary())
                if (b.getType().equals(Material.MELON))
                    return b.getLocation();

            return null;
        }

        public void onBossKill() {
            if (!killed) {
                openExit();
                Bukkit.getConsoleSender().sendMessage("I am dead!");
                for (Block block : escape) {
                    block.setType(Material.SCAFFOLDING);

                }
                killed = true;
                timerActive = false;
            }
            //Run end-game code
        }

        @Override
        public void onPlayerEnter(Player player) {
            closeEntrance();
            closeExit();
            for (Player p : getPlayers()) {
                p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1, 1);
            }
        }

        public void onBossTriggerEnter(Player p) {
            spawnBoss();
            closeEntrance();
            bossSpawned = true;

        }

        @Override
        public void resetRoom() {
            openEntrance();
            closeExit();
            killed = false;
            for (Block block : escape) {
                block.setType(Material.AIR);
            }
        }

        public void spawnBoss() {
            boss = DungeonMobs.spawnKnightBoss(bossTrigger.getCenter());
            for (Player p : alivePlayers)
                p.sendTitle("Knight of the Dungeon", null, 10, 60, 10);
        }

        @Override
        public void update() {
            super.update();
            if (!bossSpawned) {
                for (Player p : getAlivePlayers()) {
                    if (!containsAllPlayers())
                        return;
                    if (bossTrigger.contains(p.getLocation())) {
                        onBossTriggerEnter(p);
                        return;
                    }
                }
                return;
            }
            if (boss == null || boss.isDead())
                onBossKill();
        }
    }

    protected class FinalRoom extends DungeonRoom {
        private final Cuboid endDungeonTrigger;
        private boolean lootSpawned = false;
        public FinalRoom(Cuboid boundary, Cuboid endDungeonTrigger) {
            super(boundary);
            setType(RoomType.FINAL);
            this.endDungeonTrigger = endDungeonTrigger;

            for (Block block : getBoundary()) {
                if (block.getType().equals(Material.CHEST)) {
                    Chest chest = (Chest) block.getState();
                    chest.getBlockInventory().clear();
                    chest.update();
                }
            }
        }

        public void playerTriggerEnter() {
        }

        @Override
        public void onPlayerEnter(Player player) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("DUNGEON COMPLETED"));

            if (!lootSpawned)
                spawnLoot();

        }

        public void onEndTriggerEnter(Player p) {
            p.sendTitle("The End", null, 20, 60, 20);

            if (!endDungeonTrigger.contains(p.getLocation()))
                return;
            //End dungeon
            end ();
        }

        public void spawnLoot () {
            lootSpawned = true;
            int count = 0;
            int limit = getAlivePlayers().size();
            for (Block block : getBoundary()) {
                if (count >= limit)
                    break;
                if (block.getType().equals(Material.CHEST)) {
                    Chest chest = (Chest) block.getState();
                    chest.setLootTable(LootTables.END_CITY_TREASURE.getLootTable());
                    chest.update();
                    ++count;
                }
            }
            if (count != 0)
                Bukkit.getConsoleSender().sendMessage("Spawned " + count + " loot chests");

        }

        @Override
        public void resetRoom() {
            openEntrance();
            openExit();
        }

        @Override
        public void update() {
            super.update();
            for (Player p : alivePlayers)
                if (endDungeonTrigger.contains(p.getLocation()))
                    onEndTriggerEnter(p);
        }
    }
}