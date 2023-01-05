package com.stiggles.smp5.main;

import com.stiggles.smp5.listeners.CustomPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private HashMap<UUID, CustomPlayer> customPlayers = new HashMap<>();


    public CustomPlayer getCustomPlayer(UUID uuid) {
        return customPlayers.get(uuid);
    }

    public void addCustomPlayer(UUID uuid, CustomPlayer player){
        customPlayers.put(uuid, player);
    }


    public void removeCustomPlayer(UUID uuid) {
        customPlayers.remove(uuid);
    }

}