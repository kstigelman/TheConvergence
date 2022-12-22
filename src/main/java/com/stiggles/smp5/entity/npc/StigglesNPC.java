package com.stiggles.smp5.entity.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import com.stiggles.smp5.managers.NPCManager;
import com.stiggles.smp5.main.SMP5;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;

import org.bukkit.craftbukkit.v1_19_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;


public abstract class StigglesNPC {

    private ServerPlayer npc;
    private SMP5 main;
    private GameProfile profile;
    private String name;

    ServerGamePacketListenerImpl packetListener;

    private Location spawnLocation = new Location (Bukkit.getWorld ("world"), 0.0, 69, 0, 180, 0);
    //private Plugin plugin = Main.getPlugin(SMP5.class);

    private float yaw;
    private float pitch;

    public StigglesNPC (SMP5 main, String name) {

        this (main, name, new Location (Bukkit.getWorlds().get(0), 0, 0, 0));

    }
    public StigglesNPC (SMP5 main, String name, Location location) {

        this.main = main;
        SetName (name);

        yaw = 1f;
        pitch = 1f;

        profile = new GameProfile(UUID.randomUUID (), name);



        ServerLevel handle = ((CraftWorld) Bukkit.getWorlds ().get (0)).getHandle();

        npc = new ServerPlayer (handle.getServer(), handle.getLevel (), profile, null);
        SetPos (location);

        NPCManager.RegisterNewNPC(this);


    }

    /** Retrives the name of the NPC
     *
     * @return the string name
     */
    public String getName () {
        return name;
    }

    public void SetRotation (float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        npc.setXRot(yaw);
        npc.setYRot(pitch);
    }

    public ServerPlayer getServerPlayer () {
        return npc;
    }

    /** Set a new internal name for the npc. Will not change the display name.
     *
     * @param name the new name.
     */
    public void SetName (String name) {
        this.name = name;
    }

    /** Change the location of the NPC.
     *
     * @param location A minecraft location to be set.
     */
    public void SetPos (Location location) {
        npc.setPos (location.getX(), location.getY(), location.getZ());
    }

    /** Change the location of the NPC.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     * @param z The z-coordinate of the location.
     */
    public void SetPos (double x, double y, double z) {
        npc.setPos (x, y, z);
    }


    public void SetSkin (String value, String signature) {
        profile.getProperties ().put ("textures", new Property("textures", value, signature));
    }

    public void sendMessage (String msg) {
        Bukkit.getServer().broadcastMessage("<" + name + "> " + msg);
    }
    public void showToPlayer (Player player) {
        Bukkit.getConsoleSender().sendMessage ("Showing " + npc.getName () + " to " + player.getName ());

        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerPlayer serverPlayer = craftPlayer.getHandle();

        ServerGamePacketListenerImpl connection = serverPlayer.connection;

        packetListener = connection;

        basePackets (connection);
    }
    public abstract void OnInteract (Player player);
    public abstract void InteractDialogue (Player player);

    public int getId () {
        return npc.getId ();
    }

    private void basePackets (ServerGamePacketListenerImpl connection) {
        connection.send (new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
        connection.send (new ClientboundAddPlayerPacket(npc));

        SynchedEntityData data = npc.getEntityData ();
        byte bitmask = (byte) (0x01 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40);
        data.set (new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), bitmask);
        connection.send (new ClientboundSetEntityDataPacket(npc.getId(), data, true));

        connection.send (new ClientboundRotateHeadPacket(npc, (byte) ((yaw % 360) * 256 / 360)));
        connection.send(new ClientboundMoveEntityPacket.Rot(
                npc.getBukkitEntity().getEntityId(),
                (byte) ((yaw % 360) * 256 / 360),
                (byte) ((pitch % 360) * 256 / 360),
                true));

        Bukkit.getScheduler().runTaskLater (main, () -> {
            connection.send (new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER, npc));
        }, 2000);
    }
    public void SetHolding (Material item) {
        if (packetListener == null)
            return;

        packetListener.send (new ClientboundSetEquipmentPacket (npc.getBukkitEntity().getEntityId(),
                    List.of (new Pair<>(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(new ItemStack(item, 1))))
                ));
    }
}
