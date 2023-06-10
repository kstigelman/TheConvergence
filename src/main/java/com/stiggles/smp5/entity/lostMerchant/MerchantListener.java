package com.stiggles.smp5.entity.lostMerchant;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class MerchantListener implements Listener {
    private SMP5 plugin; // Add a reference to the plugin instance
    Map<UUID, Boolean> merchantCheckList = new HashMap<UUID, Boolean>();


    public MerchantListener(SMP5 plugin) {
        this.plugin = plugin;
    }
    InventoryManager inventoryManager = new InventoryManager();
    private Inventory inv;



    public Inventory getInventory(UUID id){  return inventoryManager.getInventoryFromMap(id);  }
    @EventHandler
    public void onInteraction(PlayerInteractAtEntityEvent e){
        if(e.getRightClicked().getType().equals(EntityType.VILLAGER)) {
            if (e.getRightClicked().isCustomNameVisible()) {
                Player p = e.getPlayer();
                if (e.getRightClicked().getCustomName().equals(ChatColor.AQUA + "Merchant Marketeer")) {
                    Villager m = (Villager) e.getRightClicked();
                    if (merchantCheckList.get(m.getUniqueId()) == null) {
                        inv = inventoryManager.makeInventory(p, 54, ChatColor.GRAY + "Lost Merchant Shop", inv);
                        inventoryManager.setInvMap(m.getUniqueId(), inv);
                        merchantCheckList.put(m.getUniqueId(), true);
                        p.openInventory(inv);

                    } else {
                        p.openInventory(inventoryManager.getInventoryFromMap(m.getUniqueId()));
                    }

                } else if (e.getRightClicked().getCustomName().equals(ChatColor.AQUA + "Merchant Representative")) {
                    Villager m = (Villager) e.getRightClicked();
                    p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                    p.sendMessage(ChatColor.WHITE + "<Merchant Representative> Greetings! I represent the Merchant Coalition, dedicated to helping players and acquiring resources. " +
                            "During your exploration, you'll encounter our many merchants. " +
                            "Don't forget to pay them a visit! Our trades refresh every 10 minutes, offering new opportunities. \n");
                    new BukkitRunnable() {
                        public void run() {
                            p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                            p.sendMessage(ChatColor.WHITE + "\n<Merchant Representative> Exciting surprises await you! " +
                                    "Our trades occasionally feature remarkable and exclusive items. Stay alert for our presence and make the most of our offerings.");
                        }
                    }.runTaskLater(plugin, 100);
                    new BukkitRunnable() {
                        public void run() {
                            p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                            p.sendMessage(ChatColor.WHITE + "\n<Merchant Representative> Here's a list of some of the items we offer: " +
                                    "\n [] Grappling Hook" +
                                    "\n [] Smurfs Handy Tool" +
                                    "\n [] Emerald Blade" +
                                    "\n [] Moon Shards" +
                                    "\n [] Bagels");
                        }
                    }.runTaskLater(plugin, 160);

                    new BukkitRunnable() {
                        public void run() {
                            p.playSound(p, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                            p.sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "Merchant Representative whispers to you: My workers also change their location every 10 minutes as well! They may not always be in the same spot!");
                        }
                    }.runTaskLater(plugin, 160);
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(ChatColor.GRAY + "Lost Merchant Shop") && e.getCurrentItem() != null) {
            e.setCancelled(true);

        }
    }
}

