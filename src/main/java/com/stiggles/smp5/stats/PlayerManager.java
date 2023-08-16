package com.stiggles.smp5.stats;

import com.stiggles.smp5.player.StigglesPlayer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {

    private final HashMap<UUID, StigglesPlayer> players = new HashMap<>();

    public StigglesPlayer getStigglesPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public void addStigglesPlayer(UUID uuid, StigglesPlayer player) {
        players.put(uuid, player);
    }

    public void removeStigglesPlayer(UUID uuid) {
        players.remove(uuid);
    }
}