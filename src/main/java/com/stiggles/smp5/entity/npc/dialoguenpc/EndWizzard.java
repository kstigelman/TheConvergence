package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EndWizzard extends StigglesNPC {

    String signature = "Qf73r57ugvShPpNmEmL++4sJ4PY/sSeY+fLwYq6B5mJQNa7Cg+NCYf8IrKu0jYf5cN1ksUmLfOeWh/7CkemaoLEKfW6yJPNiEp/aT4DW+nwDzwQvpWy8vBQTDrb9mJgAuVUpaCW8M57t/88sikjb3v+Zh1kV36bxBuxMDVC2HfV8sFrP7ycjKC2BX2/9KWWeor6lP/Ng0KBypoU3HZq5NrcyR2jOTpqNw5ICcuLk2lENC+Tja1fGXDxr3LEjg151p0pxq1stPWo/vetk3Jf+PK3EBeWPZmU9tsxQrO6OHGOJrdqZ/KUhu4Y/ApPTSLfXU4ORCvHvrxta57IJZFj0C6tGHJjkMj+Gk3FycCORw7a6+DhbjfYIGfp9zX26xtSpTutSMrjI9LKWHp9gBtAmVbSKJYgHw774sTbSPKMHhH0Ml3sJCFh9FkBaWJIB1y2ExHVor+QnDq3wXv0e6TaLG01cXBOfOLOUqGxm9napMGJehWnnE60s0WTUCrSL/mQoRZiaBjz3EggKWQI6V8B9VEfY3tW94iOs5+hjMMdz+Rapi3Z/Uf9IIsFDyLRXTX1XvGOjQPxs8gYCXPs9G5wsoXs1Ge54FYe1y2+yb9ETBhG0Yp3sMMHlqg93MJCc/f7mQkm1eqXWNjdskcbgdByW6TFyF2uwV0oyPXJUSvZObCc=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4Njk1MzUwOTM0NywKICAicHJvZmlsZUlkIiA6ICI1N2I0MTZlNjJjZGE0MTAzOTRiNzZkNmNkNDA3MjFiOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSME1CSUVTIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RiYWY2ZTVkNDZkNDZmZTczNDU1MzA2OTk2YTdjNzZiODZmNDE5YTAyNjJiZjAxMjllMDAwOWJlOTYzYjIwNWIiCiAgICB9CiAgfQp9";
    public EndWizzard(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value,signature);
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Wanna travel? The dragon has its chance to get us, we must strike first.");
        speakLater(player, "Go to the Mesa, there, adventure awaits you.", Sound.ENTITY_VILLAGER_CELEBRATE, 20*3);
        speakLater(player, "Look for a campsite, start looking near -662 {#} -518.", Sound.ENTITY_VILLAGER_CELEBRATE, 20*10);
    }

}
