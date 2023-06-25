package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.HuntQuestItems;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Scubadiver extends StigglesNPC {
    public Scubadiver(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY1MjY1MzU4ODM3MiwKICAicHJvZmlsZUlkIiA6ICI4ODBiZWMwYTE0MmM0YzRlYTJlZjliMTFiMTBkNWNiNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJmZ2FiIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2VhN2QwZGIyYzI0ZGJhY2RiZGZiNjU5MGFiNTVmNDRmZDQ1NmU2MWZkMTc1YTk4ZjdlMzk3N2M2ZDk1M2Q1ZjQiCiAgICB9CiAgfQp9",
                "Er/bTz6I/gPy3N3VeLjJdbhZ9+DZ6z2gLMZ32DN+FNLIRA6hjSUSl3XrcuGmkyqUQ9MWeyaGrJWfHFkIBndyheVnqqlCoPYGeM71Pb7F+zh7crC1MKiQs4SLjQMBLNhT1IcQh7oxcvP/L9/1bQlT4LFHDjTKuXQ5Cw2bdERJDvAeuXk6ds0Yqvs4YVf6mC5HMWV457e5rWK6iAPgIx2z1Cz+q50xiFNDQGcxgvJC/rprr3vQUnmR5YjXPGPn0O4zGccghCynl9tO24ANkXEFhxBIV0iYq5zqd1UCy75SNLLgCnxFGgER3E0JNRqO2oNtZ0TgSK/Vxngq7Z44IRl4EQCm3stx7nn9NU9qQVw2mMRDQRkG9RlwXiOuD+XqUgPBMgP673Dhz67x4SXz8ttdUjKd8yy8WHo6J+ZrVRDHK4e8oONLKFnV2owBHF34T0j8opA80LwABAx0+w78t63/gn9uHFE6coepVYAZRqKa85YbDrApcqbsqgR9UxejQnk6TIPWc47IfkU8nYjJTwQnAlYrYGe9jyX9oS+TLSQv1mQVrAnpG6K1KVS0G4e19cJUitlfjzhqVINhMKrtiQGDkpmLv3GHS/reibqhETZMEmYRK/EUA0ZXvji21wYAYork/pc0B3v9i7tTLhLBkb3CXYZKcqFNXXI1lhSmboSWq0g="
        );
    }

    @Override
    public void interactDialogue(Player player) {

        if (player.getInventory().getItemInMainHand().equals(HuntQuestItems.theFriendsPendant())) {
            player.getInventory().remove(HuntQuestItems.theFriendsPendant());
            speak(player, "Wow, I have NOT seen that thing in years!", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE);
            speakLater(player, "Where did you get tha- wait you went to HIM for help?!", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*2);
            speakLater(player, "You must REALLY be desperate then. Anyways, I know what you want, but it'll cost you.", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*5);
            speakLater(player, "Maybe you can do some archaeology for me, how does that sound?", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*9);
            speakLater(player, "Maybe you can bring me back some artifact of importance?", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*13);
            speakLater(player, "I've got it! Not to long ago I lost something in the depths of this new beginning, bring it back to me and then we can continue.", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*17);

        } else if (player.getInventory().getItemInMainHand().equals(HuntQuestItems.theDiversWheel())){
            speak(player, "There she is... an artifact of my old ship! Thanks for getting it!", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE);
            speakLater(player, "Anyways, yes, back to business. It is said that this artifact once belonged to a very powerful being.", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*3);
            speakLater(player, "Have you heard of Cryptorg?", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*6);
            speakLater(player, "No? Yes? Either way, the artifact your looking for was thought to be wiped from this world! At least- that's what everyone said...", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*9);
            speakLater(player, "Then it was found by a unfamiliar being, dont know who, just know that that species has never been seen again.", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE, 20*14);

        } else {
            int ni = main.getRandom() % 5;

            if (ni <= 1) {
                speak(player, "blub blub blurb blub?", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE);
            } else if (ni <= 3) {
                speak(player, "blub blurb blub blub blub blurb blub!", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE);
            } else {
                speak(player, "Blub, blub blurb Blub blub blub blurb blub blub blub blub blub blub!", Sound.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE);
            }
        }
    }
}
