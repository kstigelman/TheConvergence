package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.HuntQuestItems;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MindlessGuy extends StigglesNPC {
    String signature = "MZkN9FMFgjbe5X1nI5hgp/opBwkWC6u+0mntyZH6HIiIXcUacsIQpxeAoJwgsK0SPq0g/jZ7z5yN5MbrbIx58l+V979/ZBqapbeGEjmTFMjAy6YOBVcj6yVkKMK2mgZstciqkPLHoaMafTbsl9NR0HwyT+GDP7C5lnp+1yQJD60SH7t9mvKyWOnaLUpPmXhRBX3FIpY4EYS/JMNPRPaOCb3M4MwPj7yH7KEujbTKCApxCq6uS7/KMZW9hbNUsbRy6i+U2I1I9F/gVQZWz7Up+UgZSEP/l/9dqKtvdB0TPeX5+Rk4S/VBtA75jlBtMFuFjjAWT8+wzK73F/9gTBHXIiv5s75/B8VOqvvpS+srd08ViPDPkEctvj4tPdu0clGqV7NhTN+zXh1590nWT3SSRdBOCMoTpUMNde928o2tqoK+P7hcj/b5PZO+19Hk+gRVn6GBX3zPrqkz/ApSHz8p3FkSsuBYABI5rkSTmSF0GBjWGp6nozIW4xdfeibccAnhuwQPamxZX30gamNfoHVPUW5fAWGaWBeyf+kwNj/JGfV95GFj7p/I/Ymflqm0PR0OSxDC2KgX1wf8PyVwmK3nHWbMkvGnYQApvzI2F19Ygb8qwjxMbqPLMvDD6ngZmcwVVGDqTGyU2FG4ybMgdzbFSrppom+7IOgeDyOmTkpiorU=";

    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjQ0MjMzNDI5OCwKICAicHJvZmlsZUlkIiA6ICJkOTcwYzEzZTM4YWI0NzlhOTY1OGM1ZDQ1MjZkMTM0YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDcmltcHlMYWNlODUxMjciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ0NDY2YTY2ZDg1YzE1ZGEyZTM4YTU2OGQxOTVkM2I3YTI5YTQwZGM4OTM3MGZmNjFkODFiNGJhMWM4MmFlNSIKICAgIH0KICB9Cn0=";

    public MindlessGuy(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value, signature);
    }

    @Override
    public void interactDialogue(Player player) {

        if (player.getInventory().contains(HuntQuestItems.getNetherArtifact())) {
            int ni = main.getRandom() % 4;

            if (ni <= 1) {
                sendMessage(player, "Stay if you'd like!");
            } else if (ni == 2) {
                sendMessage(player, "Didn't I give you what you needed?");
            } else {
                sendMessage(player, "Got other things for me?");
            }
        } else if (player.getInventory().getItemInMainHand().equals(HuntQuestItems.petRock())) {
            sendMessage(player, "I see your looking for me?");
            speakLater(player, "Well, I like your confidence and determination.", Sound.ENTITY_VILLAGER_CELEBRATE, 20 * 2);
            speakLater(player, "Here's your reward, take it as gift for following through with the quest.", Sound.ENTITY_VILLAGER_CELEBRATE, 20 * 4);
            speakLater(player, "Take it to the desert, there, you'll find a wizard. Give it to him.", Sound.ENTITY_VILLAGER_CELEBRATE, 20 * 8);
            new BukkitRunnable() {
                public void run() {
                    player.getInventory().addItem(HuntQuestItems.getNetherArtifact());
                }
            }.runTaskLater(main, 20 * 8);

        } else {
            int ni = main.getRandom() % 4;

            if (ni <= 1) {
                sendMessage(player, "Sup?");
            } else if (ni == 2) {
                sendMessage(player, "Just movin around, nothin to see here.");
            } else {
                sendMessage(player, "Got anything for me?");
            }

        }

    }

}
