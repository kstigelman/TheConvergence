package com.stiggles.smp5.listeners;

import com.stiggles.smp5.dungeons.DungeonManager;
import com.stiggles.smp5.dungeons.locations.GateCuboids;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemListener implements Listener {

    SMP5 main;

    public ItemListener(SMP5 main) {
        this.main = main;
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getItem() == null)
            return;

        ItemMeta im = e.getItem().getItemMeta();
        if (im == null)
            return;

        Player p = e.getPlayer();
        String localName = im.getLocalizedName();

        if (localName.equals("pendant")) {
            Bukkit.getConsoleSender().sendMessage(p.getName() + " used Natalie's Pendant!");
            e.getItem().setAmount(e.getItem().getAmount() - 1);

            Horse natalie = p.getWorld().spawn(p.getLocation(), Horse.class);
            natalie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
            natalie.setJumpStrength(0.7);
            natalie.setOwner(p);
            natalie.setBreed(false);
            natalie.setAgeLock(true);
            natalie.getInventory().setSaddle(new ItemStack(Material.SADDLE));
            natalie.addPassenger(p);

            if (p.getUniqueId().toString().equals("5b695380-7377-4843-8d00-2494d92257ea")) {
                natalie.setStyle(Horse.Style.WHITE_DOTS);
                natalie.setColor(Horse.Color.GRAY);
                natalie.setCustomName(ChatColor.DARK_PURPLE + "Natalie");
                natalie.setCustomNameVisible(true);
                //natalie.getPersistentDataContainer().set(main.getTagKey(), PersistentDataType.STRING, tag);
            }
        } else if (localName.equals("boom_bow")) {

        } else if (localName.equals("glow_bow")) {

        } else if (localName.equals("cave_key")) {
            e.setCancelled(true);
            e.getItem().setAmount(0);
            e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "The gate to the Cave Dungeon begins to open...");
            e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 0);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                DungeonManager.addPlayer(e.getPlayer(), "testdungeon");
            }, 40);
            /*
            if (GateCuboids.getCaveGate().contains(e.getPlayer().getLocation())) {
                e.getItem().setAmount(0);
                e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "The gate to the Cave Dungeon begins to open...");
                e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1, 0);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    DungeonManager.addPlayer(e.getPlayer(), "testdungeon");
                }, 40);
            }
            else {
                e.getPlayer().sendMessage(ChatColor.DARK_GRAY + "You can't use that here...");
            }*/
        }


        /*PersistentDataContainer meta = e.getItem ().getItemMeta ().getPersistentDataContainer ();

        if (!meta.has (main.getClassKey(), PersistentDataType.STRING) || !meta.has (main.getTagKey(), PersistentDataType.STRING))
            return;

        String class_type = meta.get (main.getClassKey(), PersistentDataType.STRING);
        Player p = e.getPlayer();

        if (class_type.equals("vehicle")) {
            if (p.isInsideVehicle())
                return;
        }*/


    }
}
