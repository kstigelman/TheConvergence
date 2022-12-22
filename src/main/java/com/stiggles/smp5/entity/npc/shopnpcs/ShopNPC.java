package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import de.studiocode.invui.gui.builder.GUIBuilder;
import de.studiocode.invui.gui.builder.guitype.GUIType;
import de.studiocode.invui.gui.impl.SimpleGUI;
import de.studiocode.invui.item.builder.ItemBuilder;
import de.studiocode.invui.item.impl.SimpleItem;
import de.studiocode.invui.window.impl.single.SimpleWindow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class ShopNPC extends StigglesNPC {

    protected SimpleGUI gui;

    public ShopNPC (SMP5 main, String name) {
        super (main, name);
    }
    public ShopNPC (SMP5 main, String name, Location location) {
        super (main, name, location);
    }

    @Override
    public void OnInteract (Player player) {
        InteractDialogue (player);
        createGUI ();
        showGUI (player);
    }

    public abstract void createGUI ();

    public void showGUI (Player player) {
        new BukkitRunnable() {
            @Override
            public void run () {
                new SimpleWindow (player, getName(), gui).show();
            }

        }.runTaskLater(SMP5.getPlugin (), 4);

    }
}
