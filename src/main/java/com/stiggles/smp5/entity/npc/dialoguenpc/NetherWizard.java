package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.HuntQuestItems;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Queue;

public class NetherWizard extends StigglesNPC {

    String signature = "LibBHIf+o7MntafOppZonT6KbYE9AYo5Aus+D9NjTZ8SXmIkUrwdTgBb8aMrYLjB1pm97z77II5yDZLe/oBZT/lqWISL8xEju1S1TJ1NdzUomz4x/Ki/BRBsZLxzY8RaBeh/oT8/pUfvOFsCw+aNq43bAJf0oEbFVcVFsiUl+3hICNPYv8uCXJS4AnrITusXoxI9ytemSmva7TW39lphtDTSVui43G7dS4Gt0CJKcjohlyCIMEWpshjJ4SuxkID7r0+d+pMqkOlo5eHwmDz+M+oBp1ZZli3QS2PJSd2McZ+WRXz34mXIeoaN+4VCN55MqL/1h7YFp3AVcwtrDNYyOY2W2+HFz7CR4ah04o0s4Cha09/oE1fmrt8Qo3qoOe02CSyRBkzf0XAWeS7DddI4sstO/SqrwOxLQ+X6rC4cmFSaeJACObREAwsEhHvrWkgdr+R4nG36LGNrQSbDSZ9jXYOY3rKbCN6hkefVb2CFDwW2PWbmRUECf5a/5sMSZNHl4Ne1SsRKzmu56ONaQG0frvOTN9PGid9OpOhrgJ2tHWzk4/TU3AoWuxIpbHeoL96Xv3q78Al+9eqZxqsyaK2o2h0jeqsDlxhC7l7IkcSDCLmLlisHEgLe8tI/pxAny7x3QNpUC2mfKT1Cuv1E9CYlI/civNk78RBVEMe3Km6YE4I=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY1MDI4ODI5MDAyMSwKICAicHJvZmlsZUlkIiA6ICJmNThkZWJkNTlmNTA0MjIyOGY2MDIyMjExZDRjMTQwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJ1bnZlbnRpdmV0YWxlbnQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmFhYTQ0OWQ4YjZmYmM1NTY0ODRmNjdhNzRkNWI0YTYxNTNiM2VjZjU3OTM0M2NkNjFjMjllZjlkYWU3NGJjYyIKICAgIH0KICB9Cn0=";

    public NetherWizard(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value,signature);
    }

    @Override
    public void interactDialogue(Player player) {

        if (player.getInventory().getItemInMainHand().equals(HuntQuestItems.getNetherArtifact())){
            sendMessage(player, "Well hello there traveler.");
            speakLater(player, "Seems like someone was on quite a hunt!", Sound.ENTITY_VILLAGER_CELEBRATE, 20*2);
            speakLater(player, "Well, good for you my friend, you've come to the end... for now.", Sound.ENTITY_VILLAGER_CELEBRATE, 20*4);
            speakLater(player, "Now that the nether is open, that artifact will open numerous opportunities for you.", Sound.ENTITY_VILLAGER_CELEBRATE, 20*7);
            speakLater(player, "When you enter the nether, go talk to Cryptorg, he'll have a quest for you.", Sound.ENTITY_VILLAGER_CELEBRATE, 20*10);
            speakLater(player, "Remember that friend. Now go! Enjoy the world as it is, and have a wonderful life.", Sound.ENTITY_VILLAGER_CELEBRATE, 20*14);
            new BukkitRunnable() { public void run() {
                Quest.questComplete(player, Quest.QuestName.THE_GOOD_HUNT, ChatColor.LIGHT_PURPLE + "[THE GOOD HUNT]", 500);
            }
            }.runTaskLater(main, 20*16);
        } else {
            int ni = main.getRandom() % 4;

            if (ni <= 1) {
                sendMessage(player, "Hello!");
            } else if (ni <= 2){
                sendMessage(player, "What brings you to this portal?");
            } else {
                sendMessage(player, "Got anything in your hands for me?");
            }
        }
    }
}
