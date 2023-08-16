package com.stiggles.smp5.entity.npc;
import com.stiggles.smp5.managers.NPCManager;
import com.stiggles.smp5.main.SMP5;


import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;

/**
 * Represent a Stiggles NPC using Citizens API.
 *
 * @author Kyler Stigelman
 */
public abstract class StigglesNPC {
    protected int ri;

    private NPC npc;
    private ChatColor nameColorPrefix = ChatColor.WHITE;
    private ChatColor chatColor = ChatColor.WHITE;
    protected SMP5 main;

    private String name;


    private String worldName = "world";

    private final Location spawnLocation;

    private float voice = 1.0f;
    private float yaw;

    public StigglesNPC (SMP5 main, String name) {
        this (main, name, new Location (Bukkit.getWorlds().get(0), 0, 0, 0));
    }
    public StigglesNPC (SMP5 main, String name, Location location, int id) {

        this.main = main;

        /* Set a new random integer "seed" on every instance.
         * Server is planned to restart daily, so for certain NPC's
         * that have a shop, the shop will update daily.
         */
        ri = main.getRandom();

        //if (npc == null) {
        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
          // Bukkit.getConsoleSender().sendMessage("Created NPC " + name + " with id " + npc.getId());
        //}
        //else
        //  Bukkit.getConsoleSender().sendMessage("Found NPC " + name + " in registry with id " + npc.getId());

        NPCManager.registerNewNPC(this);
        setName (name);

        if (location.getWorld() != null)
            worldName = location.getWorld().getName();

        spawnLocation = location;
        yaw = 1f;

        /*
        try {
            if (new File ("plugins/smp5/text/" + name + ".txt").createNewFile())
                Bukkit.getConsoleSender().sendMessage("File created: " + name + ".txt");
        }
        catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("Error: Could not create file " + name + ".txt");
        }*/
        npc.getOrAddTrait (LookClose.class).lookClose (true);
        npc.getOrAddTrait (LookClose.class).setRange (5.0);

        npc.spawn (spawnLocation);

    }
    public StigglesNPC (SMP5 main, String name, Location location) {
        this (main, name, location, -1);
    }

    /**
     * Retrives the name of the NPC
     *
     * @return the string name
     */
    public String getName () {
        return name;
    }

    /**
     * Set the rotation of the NPC.
     *
     * @param yaw The horizontal rotation.
     */
    public void setRotation (float yaw) {
        this.yaw = yaw;
        npc.faceLocation(new Location (Bukkit.getWorld (worldName),
                      npc.getStoredLocation().getX() + Math.cos(yaw * 3.14 / 180),
                      npc.getStoredLocation().getY(),
                      npc.getStoredLocation().getZ () + Math.sin(yaw * 3.14 / 180)));
    }
    /**
     * Retrieve the Citizens NPC object.
     *
     * @return The NPC.
     */
    public NPC getNPC () {
        return npc;
    }

    /**
     * Set a new internal name for the npc. Will not change the display name.
     *
     * @param name the new name.
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Change the location of the NPC.
     *
     * @param location A minecraft location to be set.
     */
    public void setPos (Location location) {
        npc.despawn();
        npc.spawn(location);
    }

    /**
     * Change the location of the NPC.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     * @param z The z-coordinate of the location.
     */
    public void setPos (double x, double y, double z) {
        npc.despawn();
        npc.spawn(new Location (Bukkit.getWorld (worldName), x, y, z));
    }

    /**
     * Set the skin of the NPC. Get fields from mineskin.org.
     *
     * @param value The skin value string.
     * @param signature The skin signature string.
     */
    public void setSkin (String value, String signature) {
        npc.getOrAddTrait (SkinTrait.class).setSkinPersistent(name, signature, value);
    }

    /**
     *  Broadcast message from the NPC to all players online.
     *
     * @param msg The message to be sent.
     */
    public void broadcastMessage (String msg) {
        Bukkit.getServer().broadcastMessage(chatColor + "<" + nameColorPrefix + name + ChatColor.WHITE + chatColor + "> " + msg);
    }

    /**
     * Send a message from the NPC to a specific player.
     *
     * @param p The player to send the message to.
     * @param msg The message to be sent.
     */
    public void sendMessage (Player p, String msg) {
        p.sendMessage(chatColor + "<" + nameColorPrefix + name + ChatColor.WHITE + chatColor + "> " + msg);
    }

    /**
     * Send a message from the NPC to a specific player after a delay.
     *
     * @param p The player to send the message to.
     * @param msg The message to be sent.
     * @param delay The time duration in ticks to wait before sending.
     */
    public void sendMessageLater (Player p, String msg, int delay) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            sendMessage(p, msg);
        }, delay);

    }

    /**
     * Event that occurs when the player clicks on the NPC.
     *
     * @param player The player that interacted with the NPC.
     */

    public void onInteract (Player player) {
        if (checkQuestItems(player))
            return;
        interactDialogue(player);
        talk (player);
    }

    /**
     * Send dialogue to player when the NPC is interacted with.
     *
     * @param player The player that interacted with the NPC.
     */
    public abstract void interactDialogue (Player player);

    /**
     * Selects a line of extra dialogue prompted by the player from the NPC's
     *    dialogue file.
     *
     * @return The dialogue selected from the file.
     */
    public String getDialogue () {
        String dialogue;
        try {
            FileReader fileReader = new FileReader("plugins/smp5/text/" + name + ".txt");
            BufferedReader br = new BufferedReader(fileReader);

            ArrayList<String> dialogueList = new ArrayList<>();
            while ((dialogue = br.readLine ()) != null)
                dialogueList.add (dialogue);

            int n = Math.abs(main.getRandom()) % dialogueList.size ();
            dialogue = dialogueList.get (n);
        }
        catch (Exception e) {
            return "";
        }

        return "<" + getName () + "> " + dialogue;
    }

    /**
     * Retrieves dialogue lines from a text file.
     *   Precondition: hasDialogue returns true.
     *
     * @return An array of dialogue lines.
     */
    public ArrayList<String> getDialogues () {
        String dialogue;
        try {
            FileReader fileReader = new FileReader("plugins/smp5/text/" + name + ".txt");
            BufferedReader br = new BufferedReader(fileReader);

            ArrayList<String> dialogueList = new ArrayList<>();
            while ((dialogue = br.readLine ()) != null)
                dialogueList.add (dialogue);

            return dialogueList;
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Checks if the NPC has a non-empty text file whose name
     *   matches the NPC's name.
     *
     * @return Whether or not the file has been found.
     */
    public boolean hasDialogue () {
        try {
            FileReader fileReader = new FileReader("plugins/smp5/text/" + name + ".txt");
            BufferedReader br = new BufferedReader(fileReader);

            if (br.readLine () == null) {
                br.close();
                return false;
            }
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Prompts player in chat with a clickable message so player can talk to the NPC
     *    more if they wish.
     *
     * @param p The player that interacted with the NPC.
     */
    public void talk (Player p) {
        //String dialogue = getDialogue();

        //if (dialogue == null || dialogue.equals(""))
        //    return;

        if (!hasDialogue())
            return;

        TextComponent clickable = new TextComponent("§6§l<<CLICK TO TALK MORE>>");
        //clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tellraw " + p.getName() + " \"" + dialogue + "\""));
        clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/smm " + p.getName() + " " + getId ()));

        p.spigot().sendMessage(new BaseComponent[]{clickable});
    }

    /**
     * Get the NPC's entity ID.
     *
     * @return The ID.
     */
    public int getId () {
        return npc.getId();
    }

    /**
     * Set the NPC to hold a certain item.
     *
     * @param item The item material the NPC should hold.
     */
    public void setHolding (Material item) {
        if (item == null)
            return;
        npc.getOrAddTrait(Equipment.class).set (Equipment.EquipmentSlot.HAND, new ItemStack(item));
    }

    /**
     * Play a sound from the NPC's location.
     *
     * @param p The player that will hear the sound.
     * @param sound The sound effect to be played.
     */
    public void playSound (Player p, Sound sound) {
        p.playSound(p.getLocation(), sound, 1.f, voice);
    }

    /**
     * Change the pitch of any sound that will be played from the NPC.
     *
     * @param voice The new pitch.
     */
    public void setVoice (float voice) {
        this.voice = voice;
    }

    /**
     * Send a message and play a sound directed to a player.
     *
     * @param p The player to send the message and sound.
     * @param message The message to be sent.
     * @param sound The sound effect to be played.
     */
    public void speak (Player p, String message, Sound sound) {
        sendMessage(p, message);
        playSound(p, sound);
    }

    /**
     * Send a message and play a sound directed to a player
     *   after a time delay.
     *
     * @param p The player to send the message and sound.
     * @param message The message to be sent.
     * @param sound The sound effect to be played.
     * @param delay The time to wait before executing.
     */
    public void speakLater (Player p, String message, Sound sound, int delay) {
        Bukkit.getScheduler().runTaskLater(main, () -> {
            speak (p, message, sound);
        }, delay);
    }
    /**
     * Set the NPC to hold a certain item.
     *
     * @param item The item stack the NPC should hold.
     */
    public void setHolding (ItemStack item) {
        if (item == null)
            return;
        npc.getOrAddTrait(Equipment.class).set (Equipment.EquipmentSlot.HAND, item);
    }

    /**
     * Set the color of the NPC's name to be displayed in chat and above head.
     *
     * @param color The color to be set.
     */
    public void setNameColor (ChatColor color) {
        nameColorPrefix = color;
    }

    /**
     * Set the color of the NPC's chat messages.
     *
     * @param color
     */
    public void setChatColor (ChatColor color) {
        chatColor = color;
    }

    /**
     * Get the name of the world the NPC is located in.
     *
     * @return The string name of the world.
     */
    public String getWorldName () {
        return worldName;
    }

    /**
     * Checks a players inventory for certain quest-related
     *   items upon interaction. By default, always return false
     *   if unimplemented. For NPCs that do have items, this
     *   method will be overridden in the subclass.
     *
     * @param player The player invovled in the interaction.
     * @return Whether or not the player has the quest items.
     */
    public boolean checkQuestItems (Player player) {
        return false;
    }
}
