package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.entity.Player;

import java.util.Random;

public class Ned extends StigglesNPC {

    public Ned (SMP5 main) {

        super (main, "Ned");

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTU4ODQ3NDY3NDkxNiwKICAicHJvZmlsZUlkIiA6ICJlNzkzYjJjYTdhMmY0MTI2YTA5ODA5MmQ3Yzk5NDE3YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGVfSG9zdGVyX01hbiIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iZjU4MDNhNjBjYzY2MGNiNjkyOTcwMzUzZTk1MmMwMWI2MTNmYWJiOTE2MWUzNDIxMTQ3MTkzNjE3OWQ4MjJkIgogICAgfQogIH0KfQ==",
                "VIk2MwInYyRJn0+02HBb5Bvbd+pIo/9XRXTxn8iXeGovn3pV79kETJwFvLRwMSbtNax9sgF10+ox/kVYVQbikFMHMe1VdjBu2MH6BqdP81HJiFnuG4l6ZLySmjqMcEm6ez9bAv3Seou7cFfWPYRrPrjkSfSvZp2nPiAyJmtutpREBDYd96O1bnshldFUahsU7nVGBmhd9Ggavcwd8LBsNiMelGM+Aj45UhuEb17z8GfjtbEEn1XtNUuuSqnvPIwWuPlnhMeUr0w3y31kn5uZlaqi+1VOne77a+xQ5UD33tKbrJkbakgeoFJyfWXeBtZCdVsBw4vv/IzOBqdQm2YS53x3vaPmH8tbw+d/Qlqdh95YfcCYWgkTY01u6XDbsMcjB25Q4cKvgTGqOLQg5E4jedvyIp+bHNWnmceqTQHb0VuvJmqPMrXl/JDy6LQQf1MRBkLJLQBWXlqtfTjZ1qjfU345jdqzgPYcbwwbsa+IS6nLiOwDRcrSPGCLS+OFyPtf6B3/bPdxu+EJ/rOivCUdGmAAL2suuZ2LJLMXUZKgQAKh8bhcZ6TXfiph44U4qFte1S3sfa2K9S3jM025ujSqbLtAzedzqXa2H7nAomr66uU0L/fdNOfz5rpUy5SoJVQsb5PhKVjxwqc/+nVeHbcXDtSlkZ5Iuz4tbGyz5dk2oDo="
        );

        setPos (-6.5, -59, 3.5);
    }

    @Override
    public void interactDialogue (Player p) {
        String msg = "";

        Random rand = new Random();
        int ni = rand.nextInt () % 10;

        if (ni <= 3)
            msg = "Sippin' on straight chlorine";
        else if (ni <= 6)
            msg = "Let the vibes slide over me";
        else if (ni < 9)
            msg = "This beat is a chemical";
        else
            msg = "So deep ned bayou";

        p.sendMessage ("<" + getName () + "> " + msg);
    }

    @Override
    public void onInteract (Player p) {
        interactDialogue(p);
    }
}
