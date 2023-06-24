package com.stiggles.smp5.listeners;

import com.stiggles.smp5.items.NetheriteQuestItems;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;

public class NetheriteUpgrade implements Listener {

    NetheriteQuestItem netheriteQuestItem = new NetheriteQuestItem();

    @EventHandler
    public void onUseOfTemplate(PrepareSmithingEvent e){
        SmithingInventory sInv = e.getInventory();
        if (sInv.contains(NetheriteQuestItems.questTemplate())) {
            return;
        } else if (sInv.contains(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE)){
            e.setResult(new ItemStack(Material.CAVE_AIR, 5));
        }
    }



}
