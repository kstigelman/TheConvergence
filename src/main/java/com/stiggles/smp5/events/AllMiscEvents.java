package com.stiggles.smp5.events;

import com.stiggles.smp5.items.Cooldown;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AllMiscEvents implements Listener {

    /***
    This event is for checking if they are trying to use the grappling
     hooks ability.
     */
    @EventHandler
    public void playerRod(PlayerFishEvent e){
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getItemMeta() != null){
            ItemMeta meta = item.getItemMeta();
            if (meta.getLocalizedName() != null && meta.getLocalizedName().equals("grappling_hook")){
                if (Cooldown.checkCooldown(p)) {
                    Location playerLocation = p.getLocation();
                    Location hookLocation = e.getHook().getLocation();
                    Location change = hookLocation.subtract(playerLocation);
                    p.setVelocity(change.toVector().multiply(0.3));
                    Cooldown.setCooldown(p, 2);
                } else {
                    p.sendMessage(ChatColor.RED +"Your grappling hook is currently on a cool down, please wait!");
                }
            }
        }
    }

}
