package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class ProjectileListener implements Listener {

    SMP5 main;
    private static NamespacedKey boomKey;
    private static NamespacedKey glowKey;
    private UUID uuidShot;

    public ProjectileListener (SMP5 main) {
        this.main = main;
        boomKey = new NamespacedKey(main, "boom_arrow");
        glowKey = new NamespacedKey(main, "glow_arrow");
    }

    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        //Might be able to replace condition with isBow()
        if (e.getBow().getItemMeta().getLocalizedName().equals("boom_bow")) {
            e.getProjectile().getPersistentDataContainer().set(boomKey, PersistentDataType.STRING, "boom_arrow");
        }
        else if (e.getBow().getItemMeta().getLocalizedName().equals("glow_bow")){
            e.getProjectile().getPersistentDataContainer().set(glowKey, PersistentDataType.STRING, "glow_arrow");
            uuidShot = e.getEntity().getUniqueId();
        }
    }

    @EventHandler
    public void onArrowDamage (ProjectileHitEvent e) {
        if (e.getHitEntity() == null)
            return;

        PersistentDataContainer container = e.getEntity().getPersistentDataContainer();

        if (container.has(boomKey, PersistentDataType.STRING) && container.get(boomKey, PersistentDataType.STRING).equals("boom_arrow")) {
            e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 1, false, false);
            e.getEntity().remove();
        }
        else if (container.has(glowKey, PersistentDataType.STRING) && container.get(glowKey, PersistentDataType.STRING).equals("glow_arrow")) {
            for (Entity entity : e.getEntity().getNearbyEntities(20,20,20)){
                if (!entity.getUniqueId().equals(uuidShot)) {
                    if (entity instanceof Mob) {
                        ((Mob) entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 10, true, false, true));
                    }
                    else if (entity instanceof Player) {
                        ((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 10, true, false, true));
                    }
                }
            }
        }
    }
    private boolean isBow(ItemStack item){
        return(item.hasItemMeta()
                && item.getItemMeta().hasLocalizedName()
                && item.getItemMeta().getLocalizedName().equals("boom_bow"));
    }
}