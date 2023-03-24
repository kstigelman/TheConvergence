package com.stiggles.smp5.listeners;
/*
import com.stiggles.smp5.events.NPCRightClickEvent;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;

public class PacketListener implements Listener {

    private SMP5 main;

    public PacketListener (SMP5 main) {
        this.main = main;
    }

    public void inject (Player player)  {
        ChannelDuplexHandler channelHandler = new ChannelDuplexHandler() {


            @Override
            public void channelRead(ChannelHandlerContext ctx, Object rawPacket) throws Exception {

                if (rawPacket instanceof ServerboundInteractPacket) {
                    ServerboundInteractPacket packet = (ServerboundInteractPacket) rawPacket;

                    Field type = packet.getClass().getDeclaredField("b");
                    type.setAccessible(true);
                    Object typeData = type.get(packet);

                    if (typeData.toString ().split ("\\$")[1].charAt(0) == 'e')
                        return;

                    try {
                        Field hand = typeData.getClass().getDeclaredField("a");
                        hand.setAccessible(true);
                        if (!hand.get (typeData).toString ().equals ("MAIN_HAND"))
                            return;
                    } catch (NoSuchFieldException x) {

                    }

                    Field id = packet.getClass ().getDeclaredField ("a");
                    id.setAccessible(true);
                    int entityId = id.getInt (packet);

                    if (NPCManager.checkId (entityId)) {
                        NPCRightClickEvent event = new NPCRightClickEvent (player, NPCManager.getNPC (entityId));
                        Bukkit.getPluginManager().callEvent(event);

                    }

                    //npcManager.checkId then complete action.
                }

                //Must leave this line here
                super.channelRead(ctx, rawPacket);
            }
        };

        //Pipeline between player and server connection
        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().connection.getConnection().channel.pipeline();

        //Before server deals with reading/writing a packet, we run this
        pipeline.addBefore("packet_handler", player.getName(), channelHandler);

    }


    public void stop (Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().connection.getConnection().channel;

        channel.eventLoop().submit( () -> {
            channel.pipeline().remove (player.getName());
            return null;
        });
    }


}*/
