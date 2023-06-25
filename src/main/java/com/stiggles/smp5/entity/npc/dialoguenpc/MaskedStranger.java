package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.HuntQuestItems;
import com.stiggles.smp5.items.NetheriteQuestItems;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;

public class MaskedStranger extends StigglesNPC {
    public MaskedStranger (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4Mzk4MzQ4MjE2MiwKICAicHJvZmlsZUlkIiA6ICJiMjdjMjlkZWZiNWU0OTEyYjFlYmQ5NDVkMmI2NzE0YSIsCiAgInByb2ZpbGVOYW1lIiA6ICJIRUtUMCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kZTdhYmFiNWYxMTU5OWE1ODcxYmNjYTYwYmY1YjZjYTZlNzJhOWZmNjRhN2I3NzI4YjgwN2FlZDFkZTkyMzQ5IgogICAgfQogIH0KfQ==",
                "DQGyjByn/PIBEXpMViJu05F5XKzG9vbNrAPC2yA7VDpqErYDLcIkTjjUDgKgT/M6htXTmki60/iLpZJoptnZgV2pTywHm/JbDPmOLo1azq8k255CXH6gYRg81+vz5IxYut1yhGFLRYNFA7vYECMBzG7SwC5d5Ut9FGLvl66WyaBS0ejY50W7f6m+JQn4c29irDy8hmTrzIpMYUytI5rdPNf0v6sDCyl6jtmQXVrvtrhYC9mxCJ5qIopJpC1DqzMRYCjhsJzIH3Wu7A6vgLDQmi4K8O7sRjunfxU6LTeVsdrFpH5sYX/vPRyS8VUpCX6BOX2RzNiwyJ2NhWZE54Fd2jWguJlJY3R2VqIGWZkjS+QqQhpyPyCzG9bP6E8NJqNK+cIHAqVWVfesaETTTAkJddKFB79kzjx6t7PzHRdQc9E9MpSGNfXvDd7GcoWMjSkfoiF8gl5p/fEEBVc7/Ei0zww59zXDvB4ZTtrekGV+TXOeBgQKbpS4fpuZOGuXGM/oFFNcMurFzl8pxTmURB2BRmBwr6zs2czt6xpFZqH02E553ZxYZq85bhqXcDopN8/fKJjSFfQXMRvWcYYhxhHG4tiX/0ll64dLzEEXinj0bSd5oVL85zf5ZCWf/UxFtKaWbOqXbLV5ZZ+t++i3GsfWlI/j225OsgBrGUCFgpY9ncA="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        int ni = main.getRandom() % 5;

        if (ni <= 1) {
            sendMessage(player, "What do you want?");
        } else if (ni <= 3) {
            sendMessage(player, "Leave me alone- just go.");
        } else {
            sendMessage(player, "Fine, ill give in...");
            speakLater(player, "I understand that you may be seeking a hunting experience.", Sound.ENTITY_VILLAGER_TRADE, 20*2);
            speakLater(player, "I myself have not pursued this activity in quite some time, but I do recall the terrain being quite barren and dangerous.", Sound.ENTITY_VILLAGER_TRADE, 20*5);
            speakLater(player, "Last I heard, my friend went scuba diving.", Sound.ENTITY_VILLAGER_TRADE, 20*9);
            speakLater(player, "If your daring enough to continue the hunt, you may want to check in with him...", Sound.ENTITY_VILLAGER_TRADE, 20*12);
            speakLater(player, "You'll need this to assure him your safe to talk to- now scram!", Sound.ENTITY_VILLAGER_TRADE, 20*16);
            new BukkitRunnable() { public void run() {
                player.getInventory().addItem(HuntQuestItems.theFriendsPendant());
            }
            }.runTaskLater(main, 20*1);
        }
    }

}
