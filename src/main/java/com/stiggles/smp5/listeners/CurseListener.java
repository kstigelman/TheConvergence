package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CurseListener implements Listener {

    SMP5 main;
    public CurseListener (SMP5 main) {
        this.main = main;
    }
    @EventHandler
    public void playerRespawn (PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());

        if (!sp.isCursed())
            return;

        if (player.getAttribute(Attribute.GENERIC_MAX_HEALTH) == null)
            return;
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
    }
    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent e) {

    }

    @EventHandler
    public void onPlayerDamage (EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player))
            return;

        Player player = (Player) e.getEntity();
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());

        if (!sp.isCursed())
            return;

        if (player.getHealth() <= 4) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000 * 20, 1, true));
        }
    }
    @EventHandler
    public void onPlayerHeal (EntityRegainHealthEvent e) {
        if (!(e.getEntity() instanceof Player))
            return;

        Player player = (Player) e.getEntity();
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());

        if (!sp.isCursed())
            return;

        if (player.getHealth() > 4)
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
    }
}
// ooga booga