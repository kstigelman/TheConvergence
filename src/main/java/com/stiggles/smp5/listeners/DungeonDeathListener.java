package com.stiggles.smp5.listeners;

import com.stiggles.smp5.dungeons.DungeonManager;
import com.stiggles.smp5.entity.npc.shopnpcs.DungeonKeeper;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Listen to player death event. If the player's world of death is a dungeon, then the Dungeon Keeper gets the items.
 */
public class DungeonDeathListener implements Listener {
    private final SMP5 main;

    public DungeonDeathListener(SMP5 main) {
        this.main = main;
    }

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent e) {
        if (!e.getEntity().getWorld().getName().equals("testdungeon"))
            return;

        if (e.getDrops().isEmpty ())
            return;

        DungeonKeeper dungeonKeeper = (DungeonKeeper) NPCManager.getNPCByName ("Dungeon Keeper");
        if (e.getEntity().getInventory().isEmpty() || dungeonKeeper == null)
            return;
        //dungeonKeeper.GiveInventory(e.getEntity().getInventory());
        dungeonKeeper.giveDrops(e.getEntity().getUniqueId(), e.getEntity().getInventory().getContents());
        dungeonKeeper.sendMessage(e.getEntity(), "I can give you your items back... for a price. Come talk to me by tonight or I will sell your items.");

        e.getDrops().clear();
    }
    /*@EventHandler
    public void OnChestOpen (PlayerInteractEvent e) {
        if (e.getClickedBlock() == null)
            return;

        if (e.getClickedBlock().getType() != Material.CHEST)
            return;

        Chest chest = (Chest) e.getClickedBlock();
        if (!chest.getCustomName().equals("Dungeon Keeper's Chest"))
            return;

        DungeonKeeper dungeonKeeper = (DungeonKeeper) NPCManager.getNPCByName ("Dungeon Keeper");
        UUID uuid = e.getPlayer().getUniqueId();
        if (dungeonKeeper == null)
            return;
        if (!dungeonKeeper.containsPlayer(uuid)
            return;

        //e.setCancelled(true);

        //Create new gui
        for (ItemStack i : dungeonKeeper.getPlayerInventory(uuid))
            chest.getBlockInventory().addItem(i);
    }*/
}
