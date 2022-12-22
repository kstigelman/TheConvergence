package com.stiggles.smp5.entity.npc;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.entity.Player;

import java.util.Random;

public class EggDONTTake extends StigglesNPC {


    SMP5 main;

    public EggDONTTake (SMP5 main) {
        super (main, "Francis Smurf");

        SetSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY3MTY2NjczODk0NCwKICAicHJvZmlsZUlkIiA6ICI5NWRmN2I5ZDVhM2M0OWM3OTNlY2VmMzZiNDNmZjQ1NSIsCiAgInByb2ZpbGVOYW1lIiA6ICJFZ2dEb250VGFrZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84YTAwNDEyZDM3YTU2N2NkMTE5NWU1ZTg5NzA5YzZhMjA5ZTdiYTdlM2E4Y2QxNDI4MjlkNTljNjgxZDQ1NDQxIgogICAgfQogIH0KfQ==",
                "ROJH9yFIr4Xoj/59T1tVF06q8oGHFblkNiI9w8jhs4sqFTBncJoUxssyqe50yNlg7yvYtetJp3fZ5cG22FiSJ1aGCxYLsten8xy8u9o2vobYOvg1M02xYn1da/LKzF4BtT7rn6sh7jV4brPoV/TtQaawj89Hk+2OzSYZZV8TYqrUSz4GKltinL7x0i/qH7vJHtsJzybTiAHZ19AdLCEkSRmDabR3bZ0tFUGAyi7UGlO9PSu7YbeN4llogmMJyuQq0iqRCJixsktUSx+53Iw1RmenkIOrodEf56G8pEZw6lo12T/GXJH0ZJmoTDLptxIaVxmRF58+Z+A02t7wvon8Aw9dSe7vO879FAFHZrALmgstHCzCRqI+s3so1isjZWe/HjrbYxto0yW4JcoS3+STwjrkY4IuHhULbTixl0awu/Jzj0bp6b8KCrva+0LPadxZcVHRY6PbFzGJ8Fo8H3y75N+9UbjwjYA+lVFq6EkU72HAEkEOYR+5Dl52FA4YepqnZ5hFSSqt8jj8gn9u3Av3cCPNINpM/hQCqvZ9qHYVDJN97k2XEuah8gFqRtYnJdNq6Gm8solyilE4QrgNwETBdaWHocot48+rOnqxQNCmevDY8YFSDRHFAGWvwx/gCc6D8B+j50AnLRIgBVEHsKCXL2AZ65q7RwIrt3RIQ3H1Svw="
        );

        SetPos (-2.5, -59, 3.5);


    }

    @Override
    public void OnInteract(Player p) {
        InteractDialogue(p);
    }

    @Override
    public void InteractDialogue(Player p) {
        String msg = "";

        Random rand = new Random();
        int ni = rand.nextInt () % 99;

        if (ni <= 4)
            msg += "Eggs among SUS!";
        else if (ni <= 54)
            msg += "Don't take eggs when you are given the chance.";
        else
            msg += "Eggs look neat.";

        p.sendMessage ("<" + getName () + "> " + msg);
    }
}
