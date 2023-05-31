package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import net.citizensnpcs.Citizens;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class Bounty implements Listener {

    private static SMP5 main;

    public Bounty (SMP5 main) {
        this.main = main;
    }
    public static int calculateBounty (Player p) {
        int total = 0;
        Database db = main.getDatabase();

        try {
            db.query("select * from kills where killer = '" + p.getUniqueId() + "';");
        }
        catch (SQLException e) {

        }
        return total;
    }

    @EventHandler
    public void OnPlayerKill (PlayerDeathEvent e) {
        if (e.getEntity().getKiller() == null)
            return;
        if (e.getEntity().equals (e.getEntity().getKiller()))
            return;

        Player victim = e.getEntity();
        try {
            main.getDatabase().execute("INSERT INTO kills VALUES ('" +
                    victim.getKiller().getUniqueId() + "', '" +
                    victim.getUniqueId() + "', '" +
                    LocalDateTime.now().format(main.getFormatter()) + "');"
            );
        }
        catch (SQLException x) {

        }
    }



}
