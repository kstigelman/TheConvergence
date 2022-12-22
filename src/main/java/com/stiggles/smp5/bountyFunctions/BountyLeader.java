package com.stiggles.smp5.bountyFunctions;

import com.stiggles.smp5.player.StigglesPlayer;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BountyLeader {

    private StigglesPlayer leader;

    public BountyLeader () {
        leader = null;
    }

    public BountyLeader (StigglesPlayer player) {
        leader = player;
    }

    public StigglesPlayer getLeader () {
        return leader;
    }

    public void applyLeadership () {
        leader.getPlayer ().addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1000000, 1));
    }

    @Override
    public String toString () {
        return leader.getName() + " is the bounty leader!\n";
    }


}
