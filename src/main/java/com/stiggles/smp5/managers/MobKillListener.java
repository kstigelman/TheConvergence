package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Rewards player with coins after they kill a mob. Reward amounts are stored in config file.
 * Listener: EntityDeathEvent
 */
public class MobKillListener implements Listener {

    private static HashMap<String, Integer> coinAmounts;
    private final SMP5 main;

    public MobKillListener(SMP5 main) {
        this.main = main;

        coinAmounts = new HashMap<>();

        FileConfiguration coins = main.getConfig();
        //Grab all the coin amounts for config file and save in hashmap.
        for (String key : coins.getKeys(true))
            coinAmounts.put(key, coins.getInt(key));
    }

    public static Integer getAmount(String key) {
        return coinAmounts.get(key);
    }

    @EventHandler
    public void OnEntityDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player)
            return;

        Player killer = e.getEntity().getKiller();

        if (killer == null)
            return;

        StigglesPlayer sKiller = main.getPlayerManager().getStigglesPlayer(killer.getUniqueId());

        String killedEntity = e.getEntity().getClass().getName();
        String[] parts = killedEntity.split("entity.Craft");

        //Integer reward = BankManager.getAmount(parts[1]);
        Integer reward = getAmount(parts[1]);
        //Bukkit.getConsoleSender().sendMessage("Reward " + reward);
        if (reward == null || reward == 0)
            return;
        //reward = BankManager.getAmount ("Default");

        if (parts[1].equals("Warden")) {
            ItemStack mainHand = killer.getInventory().getItemInMainHand();
            if (mainHand.hasItemMeta() && mainHand.getItemMeta().hasLocalizedName() && mainHand.getItemMeta().getLocalizedName().equals("warden_weakness")) {
                reward = 20;
            }
        }

        sKiller.deposit(reward);
        /*
        if  (!BankManager.deposit(killer, reward))
            return;
*/
        if (!sKiller.hasChatToggledOn())
            return;
        /*if (main.getToggledChatPlayers().contains(killer.getName()))
            return;*/

        killer.sendMessage(ChatColor.GOLD + "+" + reward + " coins");
        killer.playSound(killer, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.f, 2.0f);
    }
}
