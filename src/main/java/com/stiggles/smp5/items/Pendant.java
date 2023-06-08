package com.stiggles.smp5.items;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class Pendant {

    SMP5 main;
    private static final String tag = "pendant";
    private static final String pendantName = ChatColor.LIGHT_PURPLE + "Natalie's Pendant";
    private static ItemStack pendantItem = new ItemStack(Material.CHARCOAL);

    public Pendant (SMP5 main) {
        this.main = main;
        createItem ();
    }

    private void createItem () {
        ItemStack is = pendantItem;
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(pendantName);
        //im.getPersistentDataContainer().set(main.getTagKey(), PersistentDataType.STRING, tag);
        //im.getPersistentDataContainer().set(main.getClassKey(), PersistentDataType.STRING, "vehicle");
        im.setLocalizedName("pendant");
        is.setItemMeta(im);
        pendantItem = is;
    }

    public static String getName () {
        return pendantName;
    }
    public static void givePlayerItem (Player p) {
        if (pendantItem == null)
            return;
        p.getInventory().addItem(pendantItem);
    }


    public boolean listen (PlayerInteractEvent e) {
        return true;
    }

    public static void onPlayerInteract (PlayerInteractEvent e) {

        Player p = e.getPlayer();

        if (e.getItem() == null)
            return;

        ItemMeta im = e.getItem().getItemMeta();
        if (im == null)
            return;

        if (!im.getLocalizedName().equals("pendant"))
            return;

        Horse natalie = p.getWorld().spawn(p.getLocation(), Horse.class);
        natalie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        natalie.setJumpStrength(0.7);
        natalie.setOwner(p);
        natalie.setBreed(false);
        natalie.setAgeLock(true);
        natalie.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        natalie.addPassenger(p);

        if (p.getUniqueId().toString().equals ("5b695380-7377-4843-8d00-2494d92257ea")) {
            natalie.setStyle(Horse.Style.WHITE_DOTS);
            natalie.setColor(Horse.Color.GRAY);
            natalie.setCustomName(ChatColor.DARK_PURPLE + "Natalie");
            natalie.setCustomNameVisible(true);
            //natalie.getPersistentDataContainer().set(main.getTagKey(), PersistentDataType.STRING, tag);
        }

        Bukkit.getConsoleSender().sendMessage(p.getName () + " used Natalie's Pendant!");

        e.getItem().setAmount(e.getItem().getAmount() - 1);
    }

    /*
    @EventHandler
    public void onDismountEvent (EntityDismountEvent e) {
        if (!(e.getEntity() instanceof Player))
            return;

        if (!(e.getDismounted() instanceof Horse))
            return;

        Horse dismounted = (Horse) e.getDismounted();
        if (!dismounted.getPersistentDataContainer().has (main.getTagKey(), PersistentDataType.STRING))
            return;

        String thisTag = dismounted.getPersistentDataContainer().get(main.getTagKey(), PersistentDataType.STRING);
        if (!tag.equals (thisTag))
            return;

        dismounted.remove();
        Bukkit.getConsoleSender().sendMessage("Removed " + dismounted.getCustomName());

    }*/
}