package com.stiggles.smp5.entity.npc;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class Chickens extends StigglesNPC {
    public Chickens (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MjU0NDYxNjE5MCwKICAicHJvZmlsZUlkIiA6ICJmZDIwMGYwMDE4OTI0NzgxODI5OWIzZjE5Yzc4Y2E3MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0dXNnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I1MjMzYTA5MTYxMjYyZmEyYTRkZGUwOTc3OTllYjAyNWZlYzg0NTEzNzZiYTg5YjRlYzJjYTIzNTFkOWI4YTMiCiAgICB9CiAgfQp9",
                "UZaN9s4hZzLyeOQAoVXFul2ag6Y2uQIkwM3stXLRBAP66yVEYDKLhotbLAasbGLXBM45mH2eu8AVFi3mYAXTeTfbNPN5xeFRtCV0KG9eIZj+Hk9SBs3TbGho6/D5QKNXdP0GB2aRG6zMLDJWA94DSpdxIlw33svhIjAak6/97HVpeMlq5WEqUEx6ZSIQl1JrmduOthtmfHdRSufGpY/D67GWIT1aoLCOKj86EDzZEFjyImRdU09Zx/lkUV4fn4TwyF04g1u51LvQBnl+S7iSh6zA8px2TWDtjAEmhRJ6y9b6JGsN8pxwoW8huBmUkPNZs/XgJFz6mDyfLT9JFCecCQ1AWgAtJagbFYtxZuKZTlgfcduENgwzM7fH2eyuaPieVpP+upy4/X+61IjV7TI6O/rnUdQYbuK+tCYmRDEFuX0o15AAI/4xzabgpE5b6rii16llhJFTC2IC07cuv5rH02UgEAHXQOyv8+xB5Sbk/hGwxVdjTPwpTipUMmKm8chPTHLfFOCvPxfmAD3uV7WPpb+XPLbG30HdF/RFPhHPl9svttW+Bbhb5KT/fffukrNM592qXZE+1vArK2rhZah1kNgIF95GgN1I1SBQ8uAlZVU38hAE2Tr/Q7hY9e7y7PmpebqlvCiVFHdSgzB1dmTYmm0qwpF4/s8pSWNqs8hEiuE="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        int ni = main.getRandom() % 5;

        if (ni <= 1)
            sendMessage (player, "How did you find me?");
        else if (ni <= 3)
            sendMessage (player, "Don't tell anyone I am here.");
        else
            sendMessage (player, "I'm in hiding.");

        talk (player);
    }
}
