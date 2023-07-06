package com.stiggles.smp5.listeners;

import com.stiggles.smp5.dungeons.DungeonManager;
import com.stiggles.smp5.entity.npc.shopnpcs.DungeonKeeper;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

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
        dungeonKeeper.giveInventory(e.getEntity());
        e.getEntity().sendMessage("<" + ChatColor.AQUA + "Dungeon Keeper" + ChatColor.WHITE + "> I can give you your items back... for a price. Come talk to me by tonight or I will sell your items.");
        e.getDrops().clear();


    }
}
