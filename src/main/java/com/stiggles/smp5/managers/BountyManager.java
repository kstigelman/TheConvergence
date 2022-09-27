package com.stiggles.smp5.managers;

import com.stiggles.smp5.StigglesPlayer;
import com.stiggles.smp5.bountyFunctions.BountyLeader;
import org.bukkit.entity.Player;

public class BountyManager {

    private BountyLeader leader;

    public BountyManager () {

    }

    public StigglesPlayer getLeader () {
        return leader.getLeader ();
    }

    public void setLeader (StigglesPlayer p) {
        leader = new BountyLeader (p);
    }

}
