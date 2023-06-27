package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class YetAnotherWanderer extends StigglesNPC {

    String signature = "Gq3gSx4TYP6y1J47O6MFNVeefSK5HxReWSfJ9E/3+cBKmTJuuYZNH2iDb+sDJLp/5GhPXeqgsEjG3GHfSERDoqIdr6JtdkvXv9xLx1FxjcStt5PMsUJ7i9I2XvXGlOuw1erY6MHCGL9nRI7UM8ZsOlB/1M4rIaKqMegXhobXelqgbxD5FAhlnV27ZNiUkbCiZ+JsMBcS2RGhMRYsjIylfo8LJ5jef99ElLHozBjuKC8kcwnQbigVXhfKUf6KdfTuElOWF9ly6mQuxBaUIwra78alkzzCLULpiVcpnk9ZeCsSgGf8K6Mwvv58maOalQ1umCYnZG/VRYY1CAgPNQa+R5hVW2iA40wxwCaNptaeh0hS6fOJJwXP7qzw1Ukxuq5hoiwnFz5uWxiG74jC9EHJh5sGkdDeANwcvHPjDx1+zin1kxOeb7MUgadApY17RhakdFsDE0Y2AwPjAmJSfWDyZnbj7pLz6Fv6098cxMJvcok55ptLeTWLo+LBCTuFSP+7xKuxmqzD1eXWKhlWJ9ne3bDr3hGYVj2IlaebAbGeyfkhsXA878utgJDa7MSGj8ZzjIvEn5paPWEgLgi58FoP0+j0PnnFULDoYR+mK6dh97xvxiam624AZccpXzAOamf+YLCKJ7RufJi+rdS3gMUWskbqRZzcmNjWGBisIRr6LEQ=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4Njk0OTY4NTI3MywKICAicHJvZmlsZUlkIiA6ICJjYmYxNGIxMGJhNWU0NzgwYjIyNmFiNmQzOTUxODk4YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJFZ2d5QnV0dG9uMjQxMSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xZDUzYmIyNWRiNGYxZWZiMjAwZTM4ZWJhNzE1ZTYyNzA0ZDdmNDNmYjY4ZDQyODEyOWQ1NmE4NWFhNTdlOTMiCiAgICB9CiAgfQp9";

    public YetAnotherWanderer(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value, signature);
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Im just followin this guy, dont question me.");
        speakLater(player, "Im too far away from water for me to be happy.", Sound.ENTITY_VILLAGER_NO, 100);
    }

}
