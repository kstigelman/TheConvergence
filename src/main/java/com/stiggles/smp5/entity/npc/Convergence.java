package com.stiggles.smp5.entity.npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.ArmorStandTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class Convergence {
    private final NPC npc;

    public Convergence(Location loc, int id) {
        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.ARMOR_STAND, "convergence_" + id);
        npc.getOrAddTrait(ArmorStandTrait.class).setGravity(false);
        npc.getOrAddTrait(ArmorStandTrait.class).setVisible(false);

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        PlayerProfile p = Bukkit.createPlayerProfile(UUID.randomUUID());
        try {
            PlayerTextures pt = p.getTextures();
            pt.setSkin(new URL("http://textures.minecraft.net/texture/f709cf21d593c2b5714c86fd475ee788f27d882c1a4da3337ce8e38566c4e582"
            ));
            p.setTextures(pt);
        } catch (MalformedURLException e) {

        }
        meta.setOwnerProfile(p);
        skull.setItemMeta(meta);
        npc.getOrAddTrait(Equipment.class).set(Equipment.EquipmentSlot.HELMET, skull);

        npc.spawn(loc);
    }
}