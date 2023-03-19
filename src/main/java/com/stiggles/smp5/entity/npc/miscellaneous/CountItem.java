package com.stiggles.smp5.entity.npc.miscellaneous;

import de.studiocode.invui.item.ItemProvider;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.BaseItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class CountItem extends BaseItem {

    private int count;

    @Override
    public ItemProvider getItemProvider() {
        return new ItemBuilder(Material.DIAMOND).setDisplayName("Count: " + count);
    }

    @Override
    public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
        if (clickType.isLeftClick()) {
            count++; // increment if left click
        } else {
            count--; // else decrement
        }

        notifyWindows(); // this will update the ItemStack that is displayed to the player
    }

}
