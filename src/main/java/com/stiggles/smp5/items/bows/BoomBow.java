package com.stiggles.smp5.items.bows;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class BoomBow implements Listener {

    private static final SMP5 plugin = SMP5.getPlugin(SMP5.class);
    private static final NamespacedKey boomKey = new NamespacedKey(plugin, "boom_arrow");


    private ItemStack getBoomBow(){
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName((ChatColor.WHITE + "Boom Boom Bow"));
        meta.setLore(Arrays.asList(
                ChatColor.GRAY +  "",
                ChatColor.GOLD +  "-- SPECIAL ITEM --",
                ChatColor.GRAY + "The arrows shot from this" ,
                ChatColor.GRAY + "bow explode on impact!"));
        meta.setLocalizedName("boom_bow");
        bow.setItemMeta(meta);
        return bow;
    }
    @EventHandler
    public void onShoot(EntityShootBowEvent e){
        if (!e.getBow().hasItemMeta()) {
            return;
        }
        if (e.getBow().getItemMeta().getLocalizedName().equals("boom_bow")){
            e.getProjectile().getPersistentDataContainer().set(boomKey, PersistentDataType.STRING, "boom_arrow");
        }
        
        
    }

    @EventHandler
    public void onDamage(ProjectileHitEvent e){
        PersistentDataContainer container = e.getEntity().getPersistentDataContainer();
        if (!container.has(boomKey, PersistentDataType.STRING)) return;
        if (container.get(boomKey, PersistentDataType.STRING).equals("boom_arrow")) {
            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 2.25f, false, false);
            e.getEntity().remove();
        }
    }

    public ItemStack getBoomBowPlayer(Player p){
        p.getInventory().addItem(getBoomBow());
        return getBoomBow();
    }

}
