package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.HuntQuestItems;
import com.stiggles.smp5.items.NetheriteQuestItems;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Cryptorg extends StigglesNPC {
    public Cryptorg (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY4NzY0Mzc1NDkzNiwKICAicHJvZmlsZUlkIiA6ICJmZjQ3NzI5YmQwZDI0YWYwOThiMTFjMGE3ZTFiMGVlZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYXRzY2FuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y2ZjY5Y2MwYTI4OTZjYmE1Mzk3MTRjYzBhOWJjNTRlZWU3ZjY4MGVmODZlMWMxM2U3ZDE1MzU0ZjE3ZmZiZDkiCiAgICB9CiAgfQp9",
                "wyFvQB96r2TGluia/ajf+c99BWGgKroTOA+FGGwRThEXJ1vo0CWSC5r5VWhNTC9lhCabmYGkkV7wpfFH2VpShpOHHo4NDim8Y6/DSkr9C0Kq5R6VM54nHXkANvZp5cCnqh3AWceBM6KnFID33QZVGIpOapeRy4sKo9Prpzh3zgWtytqxC74tQNfWEhP2oCxoO5yv0tie55ojC8mg3ld5O9zQipAc8NPmNvd/Yqwvtyesypa/Ncqd4Zs2paKNuyqtel1oJcTAWq9uL4jsR+jDhwTDo8fP5LUKdTVqVQJTadwsjmN2+Iea6lESuerCNOSi1busOaajA7NBCjOAGrQiU603o/zI/Xa8ILbVRnAPf/s0R9DKK2A6fMyAzS8zPybrecfSekb1hf2U2D47HCbFlN9gFK/Hno5kaJzFxJPsZWhzkqK2AICZFQcC6TKizVx/0bPISnDwPwjPDN+AnL1TfxRfPPEiDDnOX0GR9l8X0hjrrDS5mDGNF47I7VaVYlk0fr6xuOLpIKrJt6pqCXdVj1EDQyoMPj6JQIG8oyAuMn+W0QI7n9Dv2x34jDxNlloDv2ylKvePSsa5gN89SP+7wfZ4r2+q06pp5vKWhGlFlPWm2vgcn3tl+mGn/9HK4qTWV/jrIhKN0g55bkmPlfBhVkDV7iYK/zqOYDxPPlCcL/0="
                );
    }

    @Override
    public void onInteract(Player p) {
        speak(p, "I have no interest in talking to you- overworld being.", Sound.BLOCK_SHROOMLIGHT_BREAK);
        if (p.getInventory().getItemInMainHand().equals(HuntQuestItems.getNetherArtifact())) {
            speakLater(p, "However... you have something very interesting...", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 4);
            speakLater(p, "Say... where did you get that?", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 6);
            speakLater(p, "A traveler? ... So, how about you hand that over.", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 8);
            speakLater(p, "No? How dare you!", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 9);
            new BukkitRunnable() { public void run() {  p.getInventory().removeItem(HuntQuestItems.getNetherArtifact());  } }.runTaskLater(main, 20 * (140));

            speakLater(p, "Looks like you can't handle the power of it...", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 11);
            speakLater(p, "Well, if you want to help my cause, find the rest of those artifacts.", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 13);
            speakLater(p, "When you gather the other 5, come back to me, then we can speak again...", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 17);

        } else {
            speakLater(p, "Show me what I seek, then we can continue to talk.", Sound.BLOCK_SHROOMLIGHT_BREAK, 20 * 3);
        }
    }

    @Override
    public void interactDialogue(Player player) { return; }
}
