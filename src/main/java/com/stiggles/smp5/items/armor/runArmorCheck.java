package com.stiggles.smp5.items.armor;

import com.stiggles.smp5.entity.monsters.CustomSpawns;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.scheduler.BukkitRunnable;

public class runArmorCheck {
    PeacesSymphony peacesSymphony = new PeacesSymphony();
    LunarArmor lunarArmor = new LunarArmor();

    SMP5 main;
    public runArmorCheck (SMP5 main) {
        this.main = main;

        new BukkitRunnable() { public void run() { peacesSymphony.checkForPeaceArmor(); }  }.runTaskTimer(main, 0, 40);
        new BukkitRunnable() { public void run() { lunarArmor.checkForLunarArmor();  }  }.runTaskTimer(main, 0, 40);

        new BukkitRunnable() {
            public void run() {
                CustomSpawns.snowySpawn();
            }
        }.runTaskTimer(main, 200, 20 * (60 * 15));
    }
}
