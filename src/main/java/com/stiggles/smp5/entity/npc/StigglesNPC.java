package com.stiggles.smp5.entity.npc;
import com.stiggles.smp5.managers.NPCManager;
import com.stiggles.smp5.main.SMP5;

import com.stiggles.smp5.worlds.WorldType;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.SkinTrait;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public abstract class StigglesNPC {


    private NPC npc;
    private ChatColor nameColorPrefix = ChatColor.WHITE;
    private ChatColor chatColor = ChatColor.WHITE;
    protected SMP5 main;

    private String name;

    private com.stiggles.smp5.worlds.WorldType worldType;
    private String worldName;

    private Location spawnLocation;
    //private Plugin plugin = Main.getPlugin(SMP5.class);

    private float yaw;
    private float pitch;

    public StigglesNPC (SMP5 main, String name) {

        this (main, name, new Location (Bukkit.getWorlds().get(0), 0, 0, 0));

    }
    public StigglesNPC (SMP5 main, String name, Location location) {

        this.main = main;

        
        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, name);
        Bukkit.getConsoleSender().sendMessage("Created NPC " + name + " with id " + npc.getId());
        NPCManager.registerNewNPC(this);
        setName (name);

        worldName = location.getWorld().getName();

        worldType = com.stiggles.smp5.worlds.WorldType.SMP;
        for (com.stiggles.smp5.worlds.WorldType w : WorldType.values())
            if (worldName.contains(w.toString()))
                worldType = w;

        spawnLocation = location;
        yaw = 1f;
        pitch = 1f;



        npc.spawn(spawnLocation);
    }

    /** Retrives the name of the NPC
     *
     * @return the string name
     */
    public String getName () {
        return name;
    }

    /** Set the rotation of the NPC.
     *
     * @param yaw The horizontal rotation.
     * @param pitch The vertical rotation.
     */
    public void setRotation (float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        npc.faceLocation(new Location (Bukkit.getWorld (worldName),
                      npc.getStoredLocation().getX() + Math.cos(yaw),
                         npc.getStoredLocation().getY(),
                      npc.getStoredLocation().getZ () + Math.sin(yaw)));
    }

    /** Retrieve the Citizens NPC object.
     *
     * @return The NPC.
     */
    public NPC getNPC () {
        return npc;
    }

    /** Set a new internal name for the npc. Will not change the display name.
     *
     * @param name the new name.
     */
    public void setName (String name) {
        this.name = name;
    }

    /** Change the location of the NPC.
     *
     * @param location A minecraft location to be set.
     */
    public void setPos (Location location) {
        npc.despawn();
        npc.spawn(location);
    }

    /** Change the location of the NPC.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     * @param z The z-coordinate of the location.
     */
    public void setPos (double x, double y, double z) {
        npc.despawn();
        npc.spawn(new Location (Bukkit.getWorld (worldName), x, y, z));
    }

    /** Set the skin of the NPC. Get fields from mineskin.org.
     *
     * @param value The skin value string.
     * @param signature The skin signature string.
     */
    public void setSkin (String value, String signature) {
        npc.getOrAddTrait(SkinTrait.class).setSkinPersistent(name, signature, value);
    }

    /** Broadcast message from the NPC to all players online.
     *
     * @param msg The message to be sent.
     */
    public void broadcastMessage (String msg) {
        Bukkit.getServer().broadcastMessage(chatColor + "<" + nameColorPrefix + name + ChatColor.WHITE + chatColor + "> " + msg);
    }

    /** Send a message from the NPC to a specific player.
     *
     * @param p The player to send the message to.
     * @param msg The message to be sent.
     */
    public void sendMessage (Player p, String msg) {
        p.sendMessage(chatColor + "<" + name + ChatColor.WHITE + chatColor + "> " + msg);
    }

    /** Event that occurs when the player clicks on the NPC.
     *
     * @param player The player that interacted with the NPC.
     */
    public abstract void onInteract (Player player);

    /** Send dialogue to player when the NPC is interacted with.
     *
     * @param player The player that interacted with the NPC.
     */
    public abstract void interactDialogue (Player player);

    /** Get the NPC's entity ID.
     *
     * @return The ID.
     */
    public int getId () {
        return npc.getId();
    }

    /** Set the NPC to hold a certain item.
     *
     * @param item The item material the NPC should hold.
     */
    public void setHolding (Material item) {
        if (item == null)
            return;
        npc.getOrAddTrait(Equipment.class).set (Equipment.EquipmentSlot.HAND, new ItemStack(item));
    }

    /** Set the NPC to hold a certain item.
     *
     * @param item The item stack the NPC should hold.
     */
    public void setHolding (ItemStack item) {
        if (item == null)
            return;
        npc.getOrAddTrait(Equipment.class).set (Equipment.EquipmentSlot.HAND, item);
    }

    /** Set the color of the NPC's name to be displayed in chat and above head.
     *
     * @param color The color to be set.
     */
    public void setNameColor (ChatColor color) {
        nameColorPrefix = color;
    }

    /** Set the color of the NPC's chat messages.
     *
     * @param color
     */
    public void setChatColor (ChatColor color) {
        chatColor = color;
    }

    /** Get the name of the world the NPC is located in.
     *
     * @return The string name of the world.
     */
    public String getWorldName () {
        return worldName;
    }
}
