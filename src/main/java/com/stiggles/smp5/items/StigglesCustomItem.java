package com.stiggles.smp5.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class StigglesCustomItem {

    private final String name;
    private final String tag;

    public StigglesCustomItem(String name) {
        this(name, null);
    }

    public StigglesCustomItem(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public abstract ItemStack getItem();

    public String getName() {
        return name;
    }

    public void givePlayerItem(Player p) {
        p.getInventory().addItem(getItem());
    }
}