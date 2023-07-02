package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashSet;

public class DrTrog extends StigglesNPC {
    public DrTrog(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY3MTA0Mzg3MzkwNiwKICAicHJvZmlsZUlkIiA6ICI0ZTA4M2ZmNzExOGU0ODMxODcyMTFjYzA5M2QxNzNkNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJEclRyb2ciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWMyNTczMDEwNjkyYzg1NTU4YWYwMTA5NjQ5NjNmYTcxNWU5NGM1M2U1YjU4M2M0NmY5YTAxMzU1ZWEyYzk3ZCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9",
                "FpPKWl4pZRukqNUNIDKNLarURywjAdfiWvBnylH25LuCYTlQtqV6K3jnpswyWnCH81n8eURkBNNqKUvsJZfWhf7EK9uUn9UPtJa9xfUtDeYB/pOJdG5mcju+sgV/oPLUD55rfMauZXUAFwN2M1UG2iWTaAzUWKke7dc2yMz3s5jtRtuBIHIUVHIpKRn6sK90oZykmu5BR7U4UJOK5rmJ2T5RjCFxPza0UtXGXbqCD7t5cukMVdYmqVX2NBO5OCcASDSs+yzVxXIPUGOpgbm8pVeh7gStCfoq4ygU2SIi1B3vP9QVTkQ3CpZHF5Qscad9/tOqcl6RSglkMjCL1xYtpcxAm1Za/U3W4vEuWgAatoedqZQuLHxyrQ3/HqEdcN6gAvLkL/04rre2X4VDp/jWDkJaf48vGBEqJ7nHRfdG2qoZ39A0LtKRUrDDDjRStTAydNVX12N/pBZ0ivhcgwuRxnklsS2aR/C7U7l6kg+Abnzjw/1Puct4Ov/Vz0LuNOITgx7uratO3fMsmeCp2bayPLqLjNP2lhqJ594TRcATd0LMPfJcKMYUPDtukgkfRC06y6f7HZBCuOEB4bz9YwxWiUQhXIJgfFVByBlS/QFqKLNq/l2aUr5R9GM5i0Z7wOQtrkEVNe7x0JvGHQxLnTlYnXrAHGuTy/GiViIyNCZb4MA="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());
        if (!sp.hasTalkedTo(getName()))
            sendMessage(player, "Uh, hello?");
        else
            sendMessage(player, "Hi there.");

    }

    /*
        public void hasConvergence (Player player) {
            for (ItemStack i : player.getInventory()) {
                if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                    ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
                    if (im != null && im.hasDisplayName() && im.getLocalizedName().equals("moon_dust")) {

                    }
                }
            }
        }*/
    public boolean checkQuestItems(Player player) {
        if (!Quest.isQuestComplete(player, Quest.QuestName.SMALL_STEP)) {
            if (player.getInventory().getItemInMainHand().hasItemMeta()) {
                ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
                if (im != null && im.hasDisplayName() && im.getLocalizedName().equals("moon_dust")) {
                    player.getInventory().getItemInMainHand().setAmount(0);
                    sendMessage(player, "Oh! Was this from The Astronomer? Let me take a look.");
                    sendMessageLater(player, "Wow! This is amazing. This will be useful for what I am building.", 80);
                    sendMessageLater(player, "The machine will need to be able to communicate with 4 other machines that will later be located around the world.", 140);
                    sendMessageLater(player, "Until now, I was worried that the mountainous terrain here would weaken the signals between them.", 200);
                    sendMessageLater(player, "With these samples, I'm sure I can come up with a way to launch my machines into the sky to avoid that problem!", 260);
                    Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.SMALL_STEP, "One Small Step...", 150), 300);
                    return true;
                }
            }
        }

        if (!Quest.isQuestComplete(player, Quest.QuestName.COLLECT_CONVERGENCE)) {
            int requiredAmount = 8;

            StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(player.getUniqueId());
            if (sp.totalConvergenceFound() > 0) {
                if (!hasSufficientConvergence(player, 8)) {
                    sendMessage(player, "I see you have come across some Convergence Crystals.");
                    sendMessage(player, "If you find more, could you bring me " + ChatColor.LIGHT_PURPLE + requiredAmount + ChatColor.WHITE + " different crystals? I am working on a certain project that requires them...");
                    sendMessage(player, "I can also explain more about my project if you are interested");
                    return false;
                } else {
                    //Remove all Convergence from inventory
                    for (ItemStack i : player.getInventory()) {
                        if (i == null || !i.hasItemMeta())
                            continue;
                        ItemMeta im = i.getItemMeta();
                        if (im.getLocalizedName().contains("convergence_"))
                            i.setAmount(0);
                    }
                    sendMessage(player, "Thank you so much! Now I can begin testing this generator prototype.");
                    //Maybe put this in click to talk more?
                    //sendMessageLater(player, "Convergence is a very powerful substance we discovered at EGO Labs.", 80);
                    //sendMessageLater(player, "In fact, I am building a power generator that runs on it, as you can probably see the prototype here.", 160);
                    //sendMessageLater(player, "After many, many tests with the Convergence, we discovered it can be used for inter-dimensional travel.", 240);
                    //sendMessageLater(player, "One thing we didn't know, however, was the negative effects it had. Since our research was still new, we did not understand how unstable the substance is.", 160);
                    //sendMessageLater(player, "In order to prevent the Convergence from destroying our home, we had to collapse the portals, destroying the other worlds in the process.", 160);
                    //sendMessageLater(player, "Hence, this is why Nouveau is so furious at Mr. Ego. His home was destroyed under Mr. Ego's supervision. I couldn't support Mr. Ego morally. I began to take pity on Nouveau, and all the others who's homes were annihilated.", 160);
                    //sendMessageLater(player, "But now, I can't Nouveau either. He betrayed me, but what's worse is that he's forcing all these people to live here under his rule. It's oppression.", 160);
                    //sendMessageLater(player,"This power generator will supercharge the portal we used to get here, and destroy the world from the inside just like all the others.", 160);
                    //sendMessageLater(player, "This will take time, ");


                    Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.COLLECT_CONVERGENCE, "Powered Up", 500), 300);
                    sendMessageLater(player, "There is still more work to be done. There will need to be a central power generator at Community City, and 4 additional towers around the world.", 380);
                    sendMessageLater(player, "I need someone to build these generators for me. It needs to be a builder that Nouveau would overlook...", 380);
                    sendMessageLater(player, "If you happen to find a trustworthy and builder, or maybe even some construction company, could you send them my way?", 440);
                    sendMessageLater(player, "Also-- I apologize I have to a lot to ask for, but I'm in a risky situation. If Nouveau finds me, it's all over.", 500);
                    sendMessageLater(player, "I've been told about a woman named Starry. I've heard rumors she's faced Nouveau before, and I don't think we can win without her help. Can you take her this letter from me? Thank you!", 200);
                    Bukkit.getScheduler().runTaskLater(main, () -> player.getInventory().addItem(getLetter()), 200);
                    return true;
                }

            }
        }
        else {
            if (player.getInventory().firstEmpty() == -1)
                return false;
            for (ItemStack i : player.getInventory()) {
                if (i == null || !i.hasItemMeta())
                    continue;

                ItemMeta im = i.getItemMeta();
                if (im.getLocalizedName().contains("trog_letter"))
                    return false;
            }
            player.getInventory().addItem(getLetter());
        }
        return false;
    }
    public boolean hasSufficientConvergence (Player player, int amount) {
        int count = 0;
        HashSet<String> hashes = new HashSet<>();

        for (ItemStack i : player.getInventory()) {
            if (i == null || !i.hasItemMeta())
                continue;

            ItemMeta im = i.getItemMeta();
            if (!im.getLocalizedName().contains("convergence_"))
                continue;

            String id = im.getLocalizedName().split("_")[1];
            if (hashes.contains(id))
                continue;

            ++count;
            if (count >= amount)
                return true;
        }
        return count >= amount;
    }

    public ItemStack getLetter() {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "Dr. Trog's Letter");
        im.setLocalizedName("trog_letter");
        im.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + "Addressed to Starry",
                ChatColor.GRAY + "Dr. Trog needs help in the fight against Nouveau.",
                ChatColor.GRAY + "Since she knows Nouveau, she is the best candidate to help organize the attack."
                ));
        im.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(im);
        return item;
    }
}
