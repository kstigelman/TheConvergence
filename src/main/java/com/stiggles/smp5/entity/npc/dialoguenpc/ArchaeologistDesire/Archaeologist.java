package com.stiggles.smp5.entity.npc.dialoguenpc.ArchaeologistDesire;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.NetheriteQuestItems;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Archaeologist extends StigglesNPC {

    String signature = "SKXl6nPvrLTTBAVeZ5BQGPeoT0r3ptOLSjKnfueCL9O46ZxW9jKg8MZyWDrvwfWGgvZmtFRVaw8D7Vijr6JdWPUDO9cYdpUns+v0Ux8lrzflLfi1E+1vBEWnCwHjhRd7ZwnuAvWP6KV9VshgUmdrULTAdj7zmT2wqcHlEM1UKdPoml5ctMOBiwHVftrOuLcQ4MfLuK9rOJzrpGMpJYgU99LS77OCOTuab5mRSSDQoeIQiTdLTSYmBzfsBmG/IVFGZRLNSiz56qE/RDNSH+nXYLsxQukrdwkr3Eprn0qpPMQqsCo74OxW3alMRJi1N6Ws+znIr6+Loqz/6i5nhQPYwv9d9s1mfU6zvfaJ5ya23sncj0k/igy8MGZAiP6OEYQA0PkopRrsm7+S6JtqMVQo0f/o328Hh8z/0JmKYLPfA/wERw+bv90dDNqESSkf98o7uJV7euKnAjN/PnDs/j93tRj2rgL3p7FqKo1c7ZCotEf6Tk56Nuv/wSwlFwBF0YtG85ZImuNRxg98qBDmqABRqGNF2ggEghfPBu8CVwYHsEEPKTco9LuVIjM7rb0KQSgFZefwe3yLr2kkXRCqgtqW7BkYqTLvv/DTO8xNpm7aZt29+1MDFulAMt3A3FmIVMgeriLJR26ZthWclxeZtFirVvU7MBF4nQcRxLcWOcJTLCs=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4MzU1MDAwNjE5MywKICAicHJvZmlsZUlkIiA6ICJkZTU3MWExMDJjYjg0ODgwOGZlN2M5ZjQ0OTZlY2RhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNSEZfTWluZXNraW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FlZmJiMzY5ZDIwNWM2ODg4OGUxYzhiYzdmMTg4ODU1MDA4ZDFlODhlM2ZjYjZiNmVhMWMzMjU5NjI2OWI5NCIKICAgIH0KICB9Cn0=";

    public Archaeologist(SMP5 main, String name, Location location) {
        super(main, name, location);
        // 16, 92, 744
        setSkin(value, signature);
        setHolding(Material.BRUSH);
    }

    @Override
    public void onInteract(Player p) {
        if (Quest.isQuestComplete(p, Quest.QuestName.ARCHAEOLOGIST_ARMOR_QUEST)) {
            sendMessageLater(p, "Lets fix that. I know a couple of people who wouldn't mind your help.", 20 * 4);
            sendMessageLater(p, "Lets get you suited up like a true Archaeologist.", 20 * 7);
            sendMessageLater(p, "Here some places you may want to check out:", 20 * 9);
            sendMessageLater(p, "The Mesa Archaeology Site", 20 * 12);
            sendMessageLater(p, "The Mangrove Archaeology Site", 20 * 13);
            sendMessageLater(p, "The Spruce Archaeology Site", 20 * 14);
            sendMessageLater(p, "The Archaeology Site somewhat close to the Lighthouse", 20 * 15);
            sendMessageLater(p, "After that, a nice drink wouldn't hurt, you could head to the Archaeologists League Tavern.", 20 * 17);
        } else {
            int ni = main.getRandom() % 4;

            if (ni <= 1) {
                sendMessage(p, "Just diggin up old things...");
                sendMessageLater(p, "Nothing new", 20*2);
            } else if (ni <= 3) {
                sendMessage(p, "The Archaeologists Tavern is pretty good!");
                sendMessageLater(p, "Have you been? No? Look in the Spruce Forest", 20*3);
            } else {
                sendMessageLater(p, "You dont look like you wanna do archaeology.", 0);
                sendMessageLater(p, "You better fix that before we talk next...", 20*3);
            }
        }
    }

    @Override
    public void interactDialogue(Player player) {
        return;
    }

}
