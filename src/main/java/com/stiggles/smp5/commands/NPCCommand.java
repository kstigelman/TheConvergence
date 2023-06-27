package com.stiggles.smp5.commands;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * CURRENTLY UNUSED, MAY NEED LATER
 */


/*
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.stiggles.smp5.main.SMP5;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;
*/
public class NPCCommand implements CommandExecutor {

    private final SMP5 main;

    public NPCCommand(SMP5 main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.isOp()) {
            main.registerEvents();
            main.createNPCs();
        }
        return true;

    }
}
    /*
    @Override
    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer serverPlayer = craftPlayer.getHandle();

            GameProfile profile = new GameProfile(UUID.randomUUID (), "Ned");

            profile.getProperties ().put ("textures", new Property (
                    "textures",
                    "ewogICJ0aW1lc3RhbXAiIDogMTU4ODQ3NDY3NDkxNiwKICAicHJvZmlsZUlkIiA6ICJlNzkzYjJjYTdhMmY0MTI2YTA5ODA5MmQ3Yzk5NDE3YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVfSG9zdGVyX01hbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iZjU4MDNhNjBjYzY2MGNiNjkyOTcwMzUzZTk1MmMwMWI2MTNmYWJiOTE2MWUzNDIxMTQ3MTkzNjE3OWQ4MjJkIgogICAgfQogIH0KfQ==",
                    "VIk2MwInYyRJn0+02HBb5Bvbd+pIo/9XRXTxn8iXeGovn3pV79kETJwFvLRwMSbtNax9sgF10+ox/kVYVQbikFMHMe1VdjBu2MH6BqdP81HJiFnuG4l6ZLySmjqMcEm6ez9bAv3Seou7cFfWPYRrPrjkSfSvZp2nPiAyJmtutpREBDYd96O1bnshldFUahsU7nVGBmhd9Ggavcwd8LBsNiMelGM+Aj45UhuEb17z8GfjtbEEn1XtNUuuSqnvPIwWuPlnhMeUr0w3y31kn5uZlaqi+1VOne77a+xQ5UD33tKbrJkbakgeoFJyfWXeBtZCdVsBw4vv/IzOBqdQm2YS53x3vaPmH8tbw+d/Qlqdh95YfcCYWgkTY01u6XDbsMcjB25Q4cKvgTGqOLQg5E4jedvyIp+bHNWnmceqTQHb0VuvJmqPMrXl/JDy6LQQf1MRBkLJLQBWXlqtfTjZ1qjfU345jdqzgPYcbwwbsa+IS6nLiOwDRcrSPGCLS+OFyPtf6B3/bPdxu+EJ/rOivCUdGmAAL2suuZ2LJLMXUZKgQAKh8bhcZ6TXfiph44U4qFte1S3sfa2K9S3jM025ujSqbLtAzedzqXa2H7nAomr66uU0L/fdNOfz5rpUy5SoJVQsb5PhKVjxwqc/+nVeHbcXDtSlkZ5Iuz4tbGyz5dk2oDo="
            ));


            ServerPlayer npc = new ServerPlayer (serverPlayer.getServer (), serverPlayer.getLevel (), profile, null);
            npc.setPos (-7, -60, 24);

            ServerGamePacketListenerImpl connection = serverPlayer.connection;
            connection.send (new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
            connection.send (new ClientboundAddPlayerPacket(npc));

            SynchedEntityData data = npc.getEntityData ();
            byte bitmask = (byte) (0x01 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40);
            data.set (new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), bitmask);
            connection.send (new ClientboundSetEntityDataPacket(npc.getId(), data, true));


            float yaw = 1f;
            float pitch = 1f;
            connection.send (new ClientboundRotateHeadPacket(npc, (byte) ((yaw % 360) * 256 / 360)));
            connection.send(new ClientboundMoveEntityPacket.Rot(
                    npc.getBukkitEntity().getEntityId(),
                    (byte) ((yaw % 360) * 256 / 360),
                    (byte) ((pitch % 360) * 256 / 360),
                    true));

            Bukkit.getScheduler().runTaskLater (main, () -> {
                connection.send (new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER, npc));
            }, 20);
        }

        return true;
    }

}
*/