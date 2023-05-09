package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class Beachman extends StigglesNPC {

    public Beachman (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY4MjUzODUxODU0MiwKICAicHJvZmlsZUlkIiA6ICI3NTA5NzZmODRmMDE0NWFhYTc0MzAwYWJhMzc5MTIzNCIsCiAgInByb2ZpbGVOYW1lIiA6ICIwY2hlYXRzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFmNGM5YWU2YThlNTM0MDJiNTcwOTA5NzRjMzVlYWIyNzc1MDBkMjEzOTk0ZGIyNjdkMjc2MjE0MmNiYzZjMjQiCiAgICB9CiAgfQp9",
                "Sdzs1OhEhSoyIKax0ndEKkbtyAjyUoGhhNUG/e8eLuFFS4h9Mv0DroXDMvANu2eo58c0stUZ2QJogJ2U1FqSZLDlqVzHobFgKK4Fh94AFOwTze/PkkWTxbQq9/emFvHz1UBsWQYQsadNjeS1EHkCphEkK15VUOmfUESLCEtKTcqMtZ1lv5ITWKYjXcRosJZZH9fpUp2oVQMMl+0eQl2CFjuMNR3Ycje+PCaVGXrYJyvvbdLQyPDuqy2fIBgee8LTZH47fdVrYyI7GjNRu5+yx4tVyUW/CIxFQ1aTPvmpOp3RFHOGWYqDkd846cfTvm1pK0lI8UNbP0AS7lFFncBXn1zZZCDjTfWJV3QAjiQN8WgidR6cH2i82o86PE02SQbPPgHR7UE+4d1wiMJNkQKtTFRsGkchUL5D+iYDJ5/4inh73k4ZnqcqkLxUaOPWO+IrrM9ufgacQdwNhhg6Q61MmJSSW5F8Anb6VjJ4ifgbGanqx18y9eQKcGcNjRoV9Ylk0+I9sN8HUzZBUY84V4MMirkNQNA2zG1su1AwWIRW72b1OWTVTnJgJX/OCDkO1ELbZ7unX4vfb8hO+dogDz9ntCiqrWcm3Os1tQjZgxI+FmG8ysnNHX/o5OXmgDshqC+vwK1CNKZIO96CGfwkLAxGoy1URP1adRipmjJSrsJr6xg="
        );
    }

    @Override
    public void onInteract(Player p) {
        interactDialogue(p);
    }

    @Override
    public void interactDialogue(Player player) {
        int ni =  main.getRandom() % 5;

        if (ni <= 1)
            sendMessage(player,"HELLO.");
        else if (ni <= 3)
            sendMessage(player,"I AM BEACH MAN.");
        else
            sendMessage(player,"OHOHOHOHEEHEE");

        player.sendMessage (ChatColor.GRAY + ChatColor.ITALIC.toString() + "<" + player.getName () + "> ...this guy is weird.");
    }
}
