package com.stiggles.smp5.listeners;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import com.stiggles.smp5.player.StigglesPlayer;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CitizensRightClickEvent implements Listener {
    private final SMP5 main;

    public CitizensRightClickEvent(SMP5 main) {
        this.main = main;
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent e) {
        e.getClicker().playSound(e.getClicker(), Sound.ENTITY_VILLAGER_AMBIENT, 1, 1.5f);
        StigglesNPC npc = NPCManager.getNPC(e.getNPC().getId());
        npc.onInteract(e.getClicker());
        StigglesPlayer stigglesPlayer = main.getPlayerManager().getStigglesPlayer(e.getClicker().getUniqueId());
        if (stigglesPlayer != null)
            stigglesPlayer.addNPC(npc.getName());
    }
}
