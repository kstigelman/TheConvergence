package com.stiggles.smp5.managers;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

/** Rewards player with coins after they kill a mob. Reward amounts are stored in config file.
 *    Listener: EntityDeathEvent
 */
public class MobKillListener implements Listener {
    @EventHandler
    public void OnEntityDeath (EntityDeathEvent e) {
        Player killer = e.getEntity().getKiller();

        if (killer == null)
            return;

        String killedEntity = e.getEntity().getClass().getName();
        String[] parts = killedEntity.split ("entity.Craft");

        Integer reward = BankManager.getAmount(parts[1]);
        if (reward == null || reward == 0)
            return;
            //reward = BankManager.getAmount ("Default");

        killer.sendMessage(ChatColor.GOLD + "+" + reward + " coins");
        BankManager.deposit(killer, reward);
        killer.playSound(killer, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.f, 2.0f);
    }
}
