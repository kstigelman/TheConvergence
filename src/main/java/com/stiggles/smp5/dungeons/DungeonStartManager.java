package com.stiggles.smp5.dungeons;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class DungeonStartManager implements Listener {
    private final Dungeon dungeon;
    private final World world;
    private final ArrayList<Player> players;
    private final ArrayList<Player> alivePlayers;

    //private String actionBar = "⬜⬜⬜⬜⬜⬜⬜⬜⬜";
    //private char c1 = '⬛';
    //private char c2 = '⬜';
    private final int everySecondTaskID;
    public boolean timerActive = false;
    private int countdown = 0;
    private int timer = 0;
    private boolean started;

    public DungeonStartManager(Dungeon dungeon, int countdown) {
        this.countdown = countdown;
        this.dungeon = dungeon;
        this.world = dungeon.getWorld();

        players = new ArrayList<>();
        alivePlayers = new ArrayList<>();

        started = false;

        everySecondTaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(dungeon.getMain(), () -> everySecond(), 0L, 20L);
        Bukkit.getServer().getConsoleSender().sendMessage("DungeonStartManager: active!");
    }

    public int getPlayerCount() {
        return players.size();
    }

    //This will need to be a Bukkit runnable set for every second.
    //We could also add the Cuboid code to this.
    public void everySecond() {
        if (alivePlayers.isEmpty())
            return;

        if (dungeon.getState() == DungeonState.RECRUITING) {
            if (countdown > 0)
                --countdown;
            Bukkit.getConsoleSender().sendMessage("Time until start: " + countdown);
            if (countdown == 0) {
                dungeon.start();
            }
        } else if (dungeon.getState().equals(DungeonState.STARTED)) {

            if (timerActive) {
                if (timer == 0) {
                    started = true;
                }
                ++timer;
            }
            Bukkit.getConsoleSender().sendMessage("Time active: " + timer);
            if (alivePlayers.isEmpty()) {
                dungeon.reset();
                timerActive = false;

                this.getPlayers().clear();
                this.getAlivePlayers().clear();
            }
            //Update dungeon logic
            dungeon.update();

            //This code likely needs to be moved to Dungeon
            /*if (dungeon.getCurrentRoomIndex() != dungeon.getNextRoomIndex()) {
                if (dungeon.getNextRoom().isTriggered()) {
                    int next = dungeon.getNextRoomIndex() + 1;
                    dungeon.setCurrentRoom(dungeon.getNextRoomIndex());
                    dungeon.setNextRoom(next);


                    actionBar = "";

                    for (int i = 0; i < dungeon.getCurrentRoomIndex(); ++i) {
                        actionBar += c1;
                    }
                    for (int j = dungeon.getCurrentRoomIndex(); j < dungeon.getRooms().size(); ++j) {
                        actionBar += c2;
                    }

                }
            }
            for (Player player : dungeon.getDungeonManager().getAlivePlayers()) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionBar));
            }*/

        } else if (dungeon.getState() == DungeonState.FAILED) {
            Bukkit.getScheduler().cancelTask(everySecondTaskID);

        }
    }

    public boolean isStarted() {
        return started;
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
        if (p.getWorld().getName().equals(dungeon.getWorld().getName())) {
            join(p);
        }
        //Player is leaving the dungeon world
        else if (e.getFrom().getName().equals(dungeon.getWorld().getName())) {
            leave(p);
        }

    }

    public void join(Player p) {
        //dungeon.playerJoin(p);
        Bukkit.getServer().getConsoleSender().sendMessage("DungeonStartManager: " + p.getName() + " has joined!");
        p.setGameMode(GameMode.ADVENTURE);
        p.setInvisible(false);
        p.setAllowFlight(false);
        p.setFlying(false);

        if (dungeon.getState().equals(DungeonState.RECRUITING)) {
            if (players.isEmpty())
                countdown = 30;

            if (dungeon.getPlayerCount() == dungeon.getMaxPlayerCount() && countdown >= 0)
                countdown = 10;
        }
        this.players.add(p);
        this.alivePlayers.add(p);
    }

    public void leave(Player p) {

        Bukkit.getServer().getConsoleSender().sendMessage("DungeonStartManager: " + p.getName() + " has left!");

        p.setGameMode(GameMode.SURVIVAL);
        p.setInvisible(false);
        p.setAllowFlight(false);
        p.setFlying(false);


        if (dungeon.getState().equals(DungeonState.RECRUITING)) {
            //If a player leaves, allow for 30 more seconds for players to join.
            if (countdown >= 0)
                countdown = 30;
        }
        this.getPlayers().remove(p);
        this.getAlivePlayers().remove(p);

        //dungeon.playerQuit(p);
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (!p.getWorld().getName().equals(dungeon.getWorld().getName()))
            return;

        alivePlayers.remove(p);

        if (this.getPlayers().contains(p)) {
            p.setAllowFlight(true);
            p.setFlying(true);
            p.setInvisible(true);
        }
        this.getAlivePlayers().remove(p);

        if (this.getAlivePlayers().isEmpty()) {
            dungeon.failed();
        }


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (!p.getWorld().getName().equals(dungeon.getWorld().getName()))
            return;

        //if (isStarted())
        dungeon.playerQuit(p);
    }

    public void sendPlayersMessage(String msg) {
        for (Player p : players) {
            p.sendMessage(msg);
        }
    }

    public ArrayList<Player> getAlivePlayers() {
        return alivePlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getTime() {
        return timer;
    }

    public int getCountdown() {
        return countdown;
    }

}
