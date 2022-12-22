package com.stiggles.smp5.entity.npc;

import com.stiggles.smp5.dungeons.Dungeon;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.entity.Player;

public class DungeonKeeper extends StigglesNPC {
    public DungeonKeeper (SMP5 main) {

        super (main, "Dungeon Keeper");

        //SetPos (-6.5, -60, 24.5);
        //SetRotation (215f, 1f);
    }

    @Override
    public void OnInteract(Player p) {

    }

    @Override
    public void InteractDialogue(Player p) {

    }
}
