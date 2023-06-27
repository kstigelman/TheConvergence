package com.stiggles.smp5.events;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event when an NPC is right-clicked
 */
public class NPCRightClickEvent extends Event implements Cancellable {

    //Mandatory list to return
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final StigglesNPC npc;
    private boolean cancelled;

    public NPCRightClickEvent(Player player, StigglesNPC npc) {
        super(true);
        cancelled = false;
        this.player = player;
        this.npc = npc;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public StigglesNPC getNPC() {
        return npc;
    }

    //Mandatory for extending Event
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
