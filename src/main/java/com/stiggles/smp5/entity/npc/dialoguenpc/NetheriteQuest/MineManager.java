package com.stiggles.smp5.entity.npc.dialoguenpc.NetheriteQuest;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MineManager extends StigglesNPC {

    Pickaxes pickaxes = new Pickaxes();

    String signature = "jlO8vD0Vq9kb1CAljQRVhpfktSvlUOVqcQ0qXFh0xcZ1uT8z/PcDC29cXsVQ+GmHgiIElwXkgvJ0G1GIbkpm+2nBSAqOEXiKcaJvw41gusha0whJ9AQ4HRzcuSpX0eU/MjDH7/iB4z3cN13GKCa7fg7bp4o0rcqAsAvoOeKJK9ThHdaDoKPs7IsdCppIU237Q4B7yfD8hzcZSptd29dFkjvFXfw85IU2vj7ZbInWjmFB3QctoMHCMt8ncn/oW0zWf/l6SEegIJcHYSCNzCy8a66OBddL3MtKb57X/Yw6+ipSqE4PxUqLM/HxfML9BsX+BusER/bsf4Unp4JIDMn3JQzbS4tARRahB3WPfYoJn7Xt/uPiFFgxH58x5xP7jwNnbgNTvt7m87D0tqdu5VeM9KUDOqM5yAWwMdothENX8L4NNpdu1VeJ3I0XesP7DVsGU049SiqiKCuZVFhItoNIdH655ceOvDWsvdpYd/BJRaLkC9BYm8KbbG8YRjyzugeFVm+c3lIxXf6Hwe5omvLbiI6cefErtXBoADXRHBd76eQuxB7pz6swa6Iy2jQxw2YoAuL1dPugwqskQ5nrusf9+T09OIIzag36nXTGSHtL1MJ3MN1t1/NTLHhgcp2adTfEGkgh2Qjo5TaFYfh8jfDS6hnUflwknXkaW8WP3gOicaE=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjUxNjM2MTgwNiwKICAicHJvZmlsZUlkIiA6ICI1ZWQ4OTJiN2UyZGU0ZjYyYjIyNmFjNjQwZDA0YmJiOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJmcm9zdGVkc3Vuc2hpbmUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5MmI2OTk3ZDczOWY1MzViZWVkM2FiMWQ0YWVhZGZhNzY3NzdiZjhlMzhhNjY2ZjU0ZjgyZmY5Zjg1ODE4NiIKICAgIH0KICB9Cn0=";
    public MineManager(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin (value, signature);
    }

    @Override
    public void onInteract(Player p) {
        if (p.getInventory().contains(pickaxes.hardenedPickaxe())){
            sendMessage(p, "I've already given you what you need! Now go...");
        } else {
            sendMessage(p, "Here, take this, you'll need down there depending on what you plan on mining.");
            p.getInventory().addItem(pickaxes.hardenedPickaxe());
        }
    }

    @Override
    public void interactDialogue(Player player) {
        return;
    }


}
