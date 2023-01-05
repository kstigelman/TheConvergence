package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class QuestNPC extends StigglesNPC {



    public QuestNPC (SMP5 main, String name) {
        super (main, name);
    }
    public QuestNPC (SMP5 main, String name, Location location) {
        super (main, name, location);
    }
    @Override
    public void OnInteract(Player player) {

    }

    public abstract boolean giveQuest (Player player);

}
