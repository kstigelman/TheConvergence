package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
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
        /*if (main.inDungeon) {
            if (e.getDrops().isEmpty ())
                return;

            DungeonKeeper dungeonKeeper = (DungeonKeeper) NPCManager.getNPCByName ("Dungeon Keeper");
            dungeonKeeper.GiveInventory(e.getEntity().getInventory());
            e.getEntity().sendMessage(ChatColor.AQUA + "<Dungeon Keeper> " + ChatColor.WHITE + "I can give you your items back... for a price.");

            e.getDrops().clear();

        }*/
    }
}
