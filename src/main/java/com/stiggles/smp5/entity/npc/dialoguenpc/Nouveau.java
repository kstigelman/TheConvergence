package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;


public class Nouveau extends StigglesNPC {
    private ArrayList<String> interacted = new ArrayList<>();

    public Nouveau (SMP5 main, String name, Location location) {
        super (main, name, location);
        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4NTc5OTE4NDkyMiwKICAicHJvZmlsZUlkIiA6ICJlN2E1OWU2Yzk1NTY0M2IxYTYxZGI2NzNkNTA3ZjE5OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJZSmNhdCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82MmU4MjdlOTE2ZmYyMGRkZWI5NGMxOTg3NjIzODhiZGY1MTAxNDcyOTNkMDgxMTI5NjFmNjZjOTI4YTA5YWViIgogICAgfQogIH0KfQ==",
                "mjRahcbWZUC+cMYk7J3fnHJd5zHR3h5mbdsKGIJi8FuYrKDbx/zrrgKOWRHMHok61lEwcdN/eBY0yfZNxP9KxmmyZzgogLOqezPQcu/4iHJA7nw6EnzBxcq2CfcnGGJBdcBQcgFNGI3I+JCqh/pp9YzmmZqTrf4vLV0LQWszs2O8Rooe9TaS4ho2qf0awlMVDjTyzO5c81fRTeEl/9n/Z2uWvgwbCaQP1eqTeLnyGxZqMHB3k+NlvlOLE3jGGBX3HosbFtZ3SUxIwpm5N6HAB9c/gAEyTiO2OD6zSmT8CJ+hPNxuvprKJk6wHASX0m/b6aCnAYTNOdxu7O4jBoJQwpp0wcpSSGI5IvhDL4Jvk5XcfF31OpqvIc/BBr5ZqIvBaHQLWBo4EEv7yEv8HWFxePfEnJbl2gzN+QyKa3rHtk+BV28KeZFmKeL7OF6hvDvYpzYdfPhil9/ZB2HQw+o5dWk4dsssJ40ey/nXmB3QtEQCFeJ8XsUdXm3pTykulSAtQZKeAEEK1Fb+/xP1jt322eHqoB6WMhdL8Okc7LeYczfFUv3wJO9QumO8F+WtKm4vjy3gMLEl2VpEMqh9V0wXo/ry45AbtDCgjgm63eB0TMTks4GdxUmjw84IWdTLfrgf0gYeTeCKUBS9uQHis2Fnq/L6lawpO4B7AsjXxedS7j8="
        );
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
        speakLater (player, "Let's get some things sorted out: I am the reason you are here. Not Mr. Ego. Me. He is the villain. Not me.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime +80);
        speakLater (player, "Ego's company is built upon a foundation of lies which I have tried to expose. All he cares about is advancement, without any regard for us.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 200);
        speakLater (player, "Once again, I am the reason you are here. I saved you from the destruction that Ego would have brought on us all. I made this world to make this our home...", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 280);
        speakLater (player, "...but Ego will not rest. Never. Not until he takes this world from us, the very last thing we have.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 400);
        speakLater (player, "So... will you join me in a vow to take down Ego no matter the cost?", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 560);
        speakLater (player, "...", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 620);
        speakLater (player, "I sense hesitation in you. Do you not trust me?", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 680);
        speakLater (player, "I see.", Sound.ENTITY_SKELETON_AMBIENT, elapsedTime + 740);
        speakLater (player, ChatColor.BOLD + "YOU DO NOT TRUST ME", Sound.ENTITY_WITHER_SKELETON_HURT, elapsedTime + 840);
        speakLater (player, ChatColor.BOLD + "I HAVE OFFERED YOU SECURITY, YET YOU DENY IT.", Sound.ENTITY_WITHER_SKELETON_HURT, elapsedTime + 900);
        speakLater (player, ChatColor.BOLD + "DON'T EVER FORGET, YOU ARE "
                + ChatColor.DARK_RED + ChatColor.BOLD + "POWERLESS "
                + ChatColor.WHITE + ChatColor.BOLD + "AGAINST ME.", Sound.ENTITY_WITHER_SKELETON_HURT, elapsedTime + 1000);
        speakLater (player, ChatColor.BOLD + "GO.", Sound.ENTITY_WITHER_SKELETON_HURT, elapsedTime + 1060);
        Bukkit.getScheduler().runTaskLater(main, () -> {
            player.teleport (Bukkit.getWorld ("world").getSpawnLocation());
            //player.setInvisible(false);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            player.removePotionEffect(PotionEffectType.BLINDNESS);
            player.removePotionEffect(PotionEffectType.SLOW);
        }, elapsedTime + 1200);
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


    /*
     /give @p armor_stand{EntityTag:{Pose:{LeftArm:[20f,0f,160f],RightArm:[0f,90f,180f]},ShowArms:1b,Invisible:1b,NoBasePlate:1b,
     DisabledSlots:3092271,CustomName:'[{"text":"convergence"}]',Invulnerable:1b,NoGravity:1b,PersistenceRequired:1b,HandItems:
     [{id:player_head,tag:{SkullOwner:{Id:[I;-12353,14579,23832,-29158],Properties:{textures:[{
     Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRjM2U5ODM1ZGFlYjIyOGRmNjI2YzU3NDU3YTMwYjNlYmQxNDU3ZWFkNjNmZGNkN2FiN2U4MTViZmEyNWYyIn19fQ=="}]}}},Count:1},
     {id:player_head,tag:{SkullOwner:{Id:[I;-12353,14579,23832,-29158],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRjM2U5ODM1ZGFlYjIyOGRmNjI2YzU3NDU3YTMwYjNlYmQxNDU3ZWFkNjNmZGNkN2FiN2U4MTViZmEyNWYyIn19fQ=="}]}}},Count:1}],
     ArmorItems:[{tag:{SkullOwner:{Id:[I;-12353,14579,23832,-29158],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRjM2U5ODM1ZGFlYjIyOGRmNjI2YzU3NDU3YTMwYjNlYmQxNDU3ZWFkNjNmZGNkN2FiN2U4MTViZmEyNWYyIn19fQ=="}]}}}},
     {tag:{SkullOwner:{Id:[I;-12353,14579,23832,-29158],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRjM2U5ODM1ZGFlYjIyOGRmNjI2YzU3NDU3YTMwYjNlYmQxNDU3ZWFkNjNmZGNkN2FiN2U4MTViZmEyNWYyIn19fQ=="}]}}}},{},
     {id:player_head,tag:{SkullOwner:{Id:[I;-12353,14579,23832,-29158],Properties:{textures:[{Value:"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRjM2U5ODM1ZGFlYjIyOGRmNjI2YzU3NDU3YTMwYjNlYmQxNDU3ZWFkNjNmZGNkN2FiN2U4MTViZmEyNWYyIn19fQ=="}]}}},Count:1}],
     HandDropChances:[0f,0f],ArmorDropChances:[0f,0f,0f,0f]}} 1
     */
}
