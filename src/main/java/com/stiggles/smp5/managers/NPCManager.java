package com.stiggles.smp5.managers;

import com.stiggles.smp5.entity.npc.StigglesNPC;

import java.util.HashMap;

public class NPCManager {

    private final static HashMap<Integer, StigglesNPC> npc_map = new HashMap<>();

    public static boolean checkId (int id) {
        if (npc_map.containsKey(id))
            return true;
        return false;
    }

    public static boolean registerNewNPC (StigglesNPC npc) {
        if (npc_map.containsKey (npc.getId ()))
            return false;
        npc_map.put (npc.getId (), npc);
        return true;
    }

    public static StigglesNPC getNPC (int entityId) {
        return npc_map.get (entityId);
    }

    public static HashMap<Integer, StigglesNPC> getHashMap () {
        return npc_map;
    }

    public static StigglesNPC getNPCByName (String name) {
        for (StigglesNPC npc : npc_map.values())
            if (npc.getName ().equals(name))
                return npc;
        return null;
    }

}
