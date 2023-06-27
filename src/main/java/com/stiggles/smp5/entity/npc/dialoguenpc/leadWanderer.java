package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class leadWanderer extends StigglesNPC {

    String signature = "CdaQJGKGBkFp8rc5ObhZLPuxuqze/cPrtywIfeQOT5vcqtl7Z03+/DqZfsAf2lIqA5aWbJxeppkXxyEiYmD7vif38SgmBLLn5jqmAhkrczUmXey6oQO7yB6zDaCwd1FV80rar2+mdOpn0mqfeFTSRR9e5h8bM3ZY/7o4amiJMjancK5gwByEKPKVL30Gcsc4twUWjfrgG88EV8I9tHDVYixIB2aLPXf3Nw2IMjyYJwtK0+znnXeId7ldHlVavaPWBIayzeVOdqveGCWRcqXe24ojr0CECFkSUZgcf6XvuE7qlIDiP4IFCOEbRu4yKg8OQkC3b3clBiFnxlLPz5tfH/41KGiqN9UoupETbLcHCHX49R6C0uMHuapI4wGmDXfw2F16eQCMq5IOu6Qkm9XpxuMy9cE7iB9YCmFVGo1ge3n7SQTH7Iy95MO/3YUr3gSVPMluGKc488Ls58iBj05ZTFrJAN/U7K1R4aycQn7j4NAexFWDnjrb1svXPINnwIDDkJV/l5AjCiu+YlWLyrDDaxe+0tzJ4YazX/KC64YKhIWcXG1t/x24UdDBF0NADQIIgnfuvAv/AlqZ/JSjFqg0c7oJs5ge+mdNiHPqJmO60HoGCQ4hGlfrd4A5mkpjErEuYf/REgbhzlkJuFntsdJSqMSKdNlLl3vmv3jUvfbCYC0=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4NTY0MDE3ODkxMiwKICAicHJvZmlsZUlkIiA6ICI0OWIzODUyNDdhMWY0NTM3YjBmN2MwZTFmMTVjMTc2NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJiY2QyMDMzYzYzZWM0YmY4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RkYTg5ZGE0NjEyNWFlMDY4ODViMjUwM2I1NzgzMjcwZDY5OWViZTBmODliZmQ3MGM3MDA3ZjQxZmFjYzZjN2MiCiAgICB9CiAgfQp9";

    public leadWanderer(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value, signature);
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Go on! Explore down the caves, the cry of being trapped can be heard...");
        speakLater(player, "Remember this: Right, then an immediate Left, then Right-- don't die trying to be a hero...", Sound.ENTITY_VILLAGER_CELEBRATE, 120);
    }
}
