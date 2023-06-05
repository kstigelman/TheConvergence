package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Alejandro extends StigglesNPC {
    public Alejandro (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY4NTk4Mzk1ODUxNSwKICAicHJvZmlsZUlkIiA6ICJjYmYxNGIxMGJhNWU0NzgwYjIyNmFiNmQzOTUxODk4YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJFZ2d5QnV0dG9uMjQxMSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80ZjAyMTg0MGVmNWJmZTNiMDZkY2UxMDc3NDFiOWM1ZDFhYmJkZDU1NDE0MDk1ZDc1ZWFjZGVlYmJkOTVmZGUiCiAgICB9CiAgfQp9",
                "k70naui7E/ByDxcsasMq9HCgz8NQeL+5iA9hdO7zz3NUi/SyeYTt5oQSV19XCP5gBtrLFSpAq54taC+UHKuccx+y6ERVAx4FmXkB/pfVNHpTUR9SyhHSQlhif48AQnc08qpJfaD2oNir5Wjj8rK7q/gadoX/oDwLc+u8GAYe6lDM+ODOwPW35UYqrpFqtDXEYN6c9Y2uOg6cCrQ0MexullToqEDqsjIRF7b0VwiEckU3+R5TvwLgf17PhCJknUzC+nBvQfDkppnphA2QcDLp8I2PtN21evBnSmH+oHeBXWU68GPIXh3Qb161CL/77/+aU0qVyAwXkVHpUvbGdSufXFlrcr3MhjnyNvCOvmk8a8snCZvoi4jEzp7kz2cDVkktENdNFLGK1RmzevtkxmzAHr209wwukZgDCAAEO4GgKdt13sgwzf8igKndS6z7DrzITXzp/HDvcUjoz8t1II0c39GQzCX3f9nXpJqWoZo6G0nusrtw+OD0q2Xksqd5JfjRW372XEAs/cXIaWbWk4z0CKUsX/nlVOVmzfIXy6AROwWWVJytOkqtDFUYONZ5vdIQtK2VgRMTovJfAUuj3YoojHn9UZEEXIxv70k9XMZikhLjTrijud6Ev5rsYAYcYWNPVQdYe37LXxyTRQjHELkLzvTXZt5NriVqsOIQwUuWzPA="
        );
    }

    @Override
    public void onInteract(Player p) {
        interactDialogue(p);
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Hola.");
    }
}
