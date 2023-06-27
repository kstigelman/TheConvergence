package com.stiggles.smp5.listeners;


import com.stiggles.smp5.events.NPCRightClickEvent;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCListener implements Listener {

    private final SMP5 main;

    public NPCListener(SMP5 main) {
        this.main = main;
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent e) {
        e.getNPC().onInteract(e.getPlayer());
        e.getPlayer().playSound(e.getPlayer(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.5f);
    }
}
