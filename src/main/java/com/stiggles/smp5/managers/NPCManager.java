package com.stiggles.smp5.managers;

import com.stiggles.smp5.entity.npc.StigglesNPC;

import java.util.ArrayList;
import java.util.HashMap;

public class NPCManager {

    private static ArrayList<Integer> npc_ids = new ArrayList<>();
    private static HashMap<Integer, StigglesNPC> npc_map = new HashMap<>();

    public static boolean checkId (int id) {
        if (npc_ids.contains(id))
            return true;
        return false;
    }

    public static boolean RegisterNewNPC (StigglesNPC npc) {
        if (npc_map.containsKey (npc.getId ()))
            return false;
        npc_ids.add (npc.getId());
        npc_map.put (npc.getId (), npc);
        return true;
    }

    public static StigglesNPC getNPC (int entityId) {
        return npc_map.get (entityId);
    }

}
