package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;


public class Nouveau extends StigglesNPC {
    private ArrayList<String> interacted = new ArrayList<>();

    public Nouveau (SMP5 main, String name, Location location) {
        super (main, name, location);
    }
    @Override
    public void interactDialogue(Player player) {
        if (interacted.contains(player.getName()))
            return;
        interacted.add (player.getName());
        int elapsedTime = 0;
        speak (player, "Hello " + player.getName() + ". I bet you didn't expect to see me.", Sound.ENTITY_SKELETON_AMBIENT);
        if (player.getName().contains ("YoDrem")) {
            elapsedTime = 300;
            player.getInventory().addItem(getMcChicken());
            player.getInventory().addItem(getSprite());
            speakLater(player, "It's been too long, Drem. Way too long.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime);
            speakLater(player, "You know... I thought I heard somewhere that you weren't going to show up this time. What changed your mind?", Sound.ENTITY_SKELETON_AMBIENT, 80);
            speakLater(player, "Wait...", Sound.ENTITY_SKELETON_AMBIENT, 140);
            speakLater(player, "IS THAT A MCCHICKEN???", Sound.ENTITY_SKELETON_AMBIENT, 160);
            speakLater(player, "WHERE-- HOW-- WHAT???", Sound.ENTITY_SKELETON_AMBIENT, 200);
            speakLater(player, "...", Sound.ENTITY_SKELETON_AMBIENT, 240);
            speakLater(player, "I'm so confused.", Sound.ENTITY_SKELETON_AMBIENT, 260);
            speakLater(player, "Anyways...", Sound.ENTITY_SKELETON_AMBIENT, 300);

        }
        speakLater (player, "Let's get some things sorted out: I am the reason you are here. Not Mr. Ego. Me. He is the villain. Not me.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 40);
        speakLater (player, "Ego's company is built upon a foundation of lies which I have tried to expose. All he cares about is advancement, without any regard for us.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 100);
        speakLater (player, "Once again, I am the reason you are here. I saved you from the destruction that Ego would have brought on us all. I made this world to make this our home...", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 160);
        speakLater (player, "...but Ego will not rest. Never. Not until he takes this world from us, the very last thing we have.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 200);
        speakLater (player, "So... will you join me in a vow to take down Ego no matter the cost?", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 260);
        //...
        //I sense hesitation in you. Do you not trust me??
        //I see.
        //YOU DO NOT TRUST ME.  SkeletonDeathSound or WitherSkeleton
        //No - I HAVE OFFERED YOU SECURITY, YET YOU DENY IT. //Yes - LIAR. YOU DO NOT TRUST ME. YOU ONLY SEEK PERSONAL GAIN.
        //YOU ARE cc.DarkRed POWERLESS AGAINST ME.
        //GO
        Bukkit.getScheduler().runTaskLater(main, () -> {
            player.teleport (Bukkit.getWorld ("world").getSpawnLocation());
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            player.setInvisible(false);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
        }, 1000);
    }

    public ItemStack getMcChicken () {
        ItemStack item = new ItemStack(Material.COOKED_CHICKEN);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.RED + "McChicken");
        im.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Part of the deal"));
        item.setItemMeta(im);
        return item;
    }
    public ItemStack getSprite () {
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta im = (PotionMeta) item.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "Sprite");
        im.setLore(Arrays.asList(ChatColor.DARK_GRAY + "One second of fruitiness guaranteed."));
        im.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 200, 5), false);
        im.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 20, 5), false);
        im.addCustomEffect(new PotionEffect(PotionEffectType.JUMP, 20, 5), false);
        item.setItemMeta(im);
        return item;
    }
}
