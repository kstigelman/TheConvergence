package com.stiggles.smp5.listeners;

import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CitizensRightClickEvent implements Listener {
    private SMP5 main;

    public CitizensRightClickEvent (SMP5 main) {
        this.main = main;
    }
    @EventHandler
    public void onRightClick (NPCRightClickEvent e) {
        e.getClicker().playSound(e.getClicker(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.5f);
        NPCManager.getNPC(e.getNPC ().getId()).OnInteract(e.getClicker());
    }
}
