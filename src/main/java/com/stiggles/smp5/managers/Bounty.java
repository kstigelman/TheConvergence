package com.stiggles.smp5.managers;

import com.stiggles.smp5.main.Database;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Bounty {

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


}
