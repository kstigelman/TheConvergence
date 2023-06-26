package com.stiggles.smp5.entity.npc.dialoguenpc.NetheriteQuest;

import com.stiggles.smp5.entity.lostMerchant.InventoryManager;
import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.NetheriteQuestItems;
import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class NetheriteMaster extends StigglesNPC {

    HashMap<UUID, Boolean> playersWhomDid = new HashMap<>();

    Pickaxes pickaxes = new Pickaxes();

    String signature = "jlO8vD0Vq9kb1CAljQRVhpfktSvlUOVqcQ0qXFh0xcZ1uT8z/PcDC29cXsVQ+GmHgiIElwXkgvJ0G1GIbkpm+2nBSAqOEXiKcaJvw41gusha0whJ9AQ4HRzcuSpX0eU/MjDH7/iB4z3cN13GKCa7fg7bp4o0rcqAsAvoOeKJK9ThHdaDoKPs7IsdCppIU237Q4B7yfD8hzcZSptd29dFkjvFXfw85IU2vj7ZbInWjmFB3QctoMHCMt8ncn/oW0zWf/l6SEegIJcHYSCNzCy8a66OBddL3MtKb57X/Yw6+ipSqE4PxUqLM/HxfML9BsX+BusER/bsf4Unp4JIDMn3JQzbS4tARRahB3WPfYoJn7Xt/uPiFFgxH58x5xP7jwNnbgNTvt7m87D0tqdu5VeM9KUDOqM5yAWwMdothENX8L4NNpdu1VeJ3I0XesP7DVsGU049SiqiKCuZVFhItoNIdH655ceOvDWsvdpYd/BJRaLkC9BYm8KbbG8YRjyzugeFVm+c3lIxXf6Hwe5omvLbiI6cefErtXBoADXRHBd76eQuxB7pz6swa6Iy2jQxw2YoAuL1dPugwqskQ5nrusf9+T09OIIzag36nXTGSHtL1MJ3MN1t1/NTLHhgcp2adTfEGkgh2Qjo5TaFYfh8jfDS6hnUflwknXkaW8WP3gOicaE=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjUxNjM2MTgwNiwKICAicHJvZmlsZUlkIiA6ICI1ZWQ4OTJiN2UyZGU0ZjYyYjIyNmFjNjQwZDA0YmJiOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJmcm9zdGVkc3Vuc2hpbmUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5MmI2OTk3ZDczOWY1MzViZWVkM2FiMWQ0YWVhZGZhNzY3NzdiZjhlMzhhNjY2ZjU0ZjgyZmY5Zjg1ODE4NiIKICAgIH0KICB9Cn0=";
    public NetheriteMaster(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin (value, signature);
    }

    @Override
    public void onInteract(Player p) {
        if (playersWhomDid.get(p.getUniqueId()) == null){
            Inventory inv = p.getInventory();
            if (p.getInventory().getItemInMainHand().equals(pickaxes.hardenedPickaxe())){
                speak(p, "Looks like you have what it takes. Well, what are you waiting for?", Sound.ENTITY_PIGLIN_ADMIRING_ITEM);
                speakLater(p, "Scared of a little mining? Don't be, just don't hurt anyone..." , Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 20*4);
                speakLater(p, "Bring me back this exact list, and dont forget or add anything, otherwise I'll take it." , Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 20*6);
                speakLater(p, "8 Reinforced Ancient Debris and Hardened Gold" , Sound.ENTITY_PIGLIN_AMBIENT, 20*9);
                speakLater(p, "4 Hardened Diamonds" , Sound.ENTITY_PIGLIN_AMBIENT, 20*10);
                speakLater(p, "2 Toughened Obsidian" , Sound.ENTITY_PIGLIN_AMBIENT, 20*11);
            } else if (inv.containsAtLeast(NetheriteQuestItems.hardenedGold(), 8) && inv.containsAtLeast(NetheriteQuestItems.hardenedGold(), 8) &&
                    inv.containsAtLeast(NetheriteQuestItems.hardenedDiamond(), 4) && inv.containsAtLeast(NetheriteQuestItems.toughenedObsidian(), 2)) {
                speak(p, "Thanks for helping us out.", Sound.ENTITY_PIGLIN_CELEBRATE);
                speakLater(p, "In return, allow me to give you one of our newly forged Crystallized Upgrade Templates", Sound.ENTITY_PIGLIN_ADMIRING_ITEM, 20*2);
                new BukkitRunnable() { public void run() {
                    p.getInventory().remove(NetheriteQuestItems.theEightDebris());
                    p.getInventory().remove(NetheriteQuestItems.theEightGold());
                    p.getInventory().remove(NetheriteQuestItems.theFourDiamond());
                    p.getInventory().remove(NetheriteQuestItems.theTwoOby());
                    p.getInventory().addItem(NetheriteQuestItems.questTemplate());
                    playersWhomDid.put(p.getUniqueId(), true);

                    for(ItemStack itemStack : p.getInventory().getContents()){
                        if (itemStack != null) {
                            if (itemStack.getType() != null) {
                                Material material = itemStack.getType();
                                if (material.equals(Material.ANCIENT_DEBRIS) || material.equals(Material.GOLD_INGOT) || material.equals(Material.DIAMOND)
                                        || material.equals(Material.OBSIDIAN)) {
                                    if (itemStack.isSimilar(NetheriteQuestItems.reinforcedAncientDebris())) {
                                        p.getInventory().remove(itemStack);
                                    } else if (itemStack.isSimilar(NetheriteQuestItems.hardenedGold())) {
                                        p.getInventory().remove(itemStack);
                                    } else if (itemStack.isSimilar(NetheriteQuestItems.hardenedDiamond())) {
                                        p.getInventory().remove(itemStack);
                                    } else if (itemStack.isSimilar(NetheriteQuestItems.toughenedObsidian())) {
                                        p.getInventory().remove(itemStack);
                                    }
                                }
                            }
                        }
                    }

                }
                }.runTaskLater(main, 20*8);
                speakLater(p, "There you go, for now that's all I have for you, now, leave the mines!", Sound.ENTITY_PIGLIN_BRUTE_ANGRY, 20*7);

            } else {
                speak(p, "You don't look ready to mine, come back when you are.", Sound.ENTITY_PIGLIN_ANGRY);
            }
        } else {
            p.sendMessage(ChatColor.RED + "You've already done this quest, you may not do it again.");
        }
    }

    @Override
    public void interactDialogue(Player player) {
        return;
    }



}
