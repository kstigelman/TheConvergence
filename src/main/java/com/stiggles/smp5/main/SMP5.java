/**
 * Plugin for Stiggles SMP Season 5, the biggest,
 * most ambitious project yet.
 *
 * @author Kyler Stigelman
 * @version 0.0.0
 * @since 2022-08-28
 */


package com.stiggles.smp5.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SMP5 extends JavaPlugin {

    private static SMP5 instance;

    @Override
    public void onEnable() {
        instance = this;
        //saveDefaultConfig()
        //stats = new PluginFile(this, "stats.yml", "stats.yml");


        if (Bukkit.getWorld("smp5") == null);

        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        instance = null;
    }


    public static SMP5 getInstance() {
        return instance;
    }
}
