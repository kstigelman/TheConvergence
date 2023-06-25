package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.HuntQuestItems;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class Shrek extends StigglesNPC {

    String signature = "wAgUuH7Vba1PAeOq4ZLbT60OcxZtEHhTfiK8cTIrz9rMv1TImdfO4JKyjvwlLJzX/UspTh0nul/6h5PEPolhAjob1FzNQL8SBHLSyG4kSEx7Mj+YbdtBbTWUesP9ftl2tgESeLu4bumtPdupDJJEt9sQORbvVar8kFlemhreOJBIMt5AX4n7x0RNsxlpCpRv768DwWUCDjYUVORdjIOB5kPQJti/JXfSpzsVIm9wzhj/z8JA/hAGJ/gce9rMdsrpB9KVTV1WZgLp4ynOBJdwog+MoprL7Ou6OIH3OfvF2I8UDm33CYK9+R+fPearZv5LuKCbnRCuSqpDM+Te0PtKmyiL7OYxDCqgezC0ikZgugbxEeP5KmiykhtugcI0hZWep9wZkiKRur6S1bIEqOWvqG/1iSU061lQESqFkwMt2iqy/IIVryX1UTzcmZ83lll0Xjs0XeL5ciTzpqqONu8/x8t1ESZDJEFAJklqdmplond8tsUcV6gxZTWRwGKbX317xQgYXjMnSvoOPjLM02BHztCym7/WnqSv9BdPECoyPF2FZLERLQhbhQMlBKhitlAcoPDdu9OQsY/eTeUFx7Wn8c7GkRPOzSmO3Hs1hDrnMLyk4v6TEkdumdQCYog4t32yj6VHFP1Yci1RyoPVg9dQBKhor5J7uJubvAkeC2/wBYc=";
    String value = "eyJ0aW1lc3RhbXAiOjE1ODcwNjE2OTA1NzMsInByb2ZpbGVJZCI6IjNlODFjZWFiNTQ5ZDQ1NjU5NzY5YWI5OTFlNjYzODAxIiwicHJvZmlsZU5hbWUiOiJTaHJlayIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NlZGEyZTQxY2U2MDBjYTUzZTczYTZmZWUyMGRhNDZmYjJjYWExMzljY2FlYzVhNzU3ZjNkY2JiNTdiOTRlNiJ9fX0=";
    public Shrek(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value,signature);
    }

    @Override
    public void interactDialogue(Player player) {

        if (player.getInventory().getItemInMainHand().equals(HuntQuestItems.theDiversWheel())){
            speak(player, "Hmm, I ain't know what to do with that", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
            speakLater(player, "But I found this cool artifact lookin thing, wanna see it?", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT, 20*3);
            speakLater(player, "Wait, never mind this mindless guy rambling his mind came by and took it, I think he was heading towards a town of sorts, I do know that everyone knows of it though!", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT, 20*6);
            speakLater(player, "He gave me rock in return for that artifact, here- take it maybe he'll give it back to you.", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT, 20*8);
            new BukkitRunnable() { public void run() {
                player.getInventory().addItem(HuntQuestItems.petRock());
            }
            }.runTaskLater(main, 20*8);

            player.getInventory().addItem(HuntQuestItems.petRock());
        } else if (player.getInventory().getItemInMainHand().equals(HuntQuestItems.petRock())) {
            int ni = main.getRandom() % 4;

            if (ni <= 1) {
                speak(player, "What are you doin in my swamp?!", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
            } else if (ni <= 2){
                speak(player, "Get off my swamp!", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
            } else {
                speak(player, "Wait a minute, I just gave you that...", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
            }
        } else {
            int ni = main.getRandom() % 4;

            if (ni <= 1) {
                speak(player, "What are you doin in my swamp?!", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
            } else {
                speak(player, "Get off my swamp!", Sound.ENTITY_ZOMBIE_VILLAGER_AMBIENT);
            }
        }

    }
}
