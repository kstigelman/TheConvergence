package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.managers.NPCManager;
import com.stiggles.smp5.player.StigglesPlayer;
import com.stiggles.smp5.stats.Quest;
import net.citizensnpcs.Citizens;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.npc.CitizensNPC;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import xyz.xenondevs.invui.item.Click;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class DremBot extends StigglesNPC {

    public DremBot(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(
                "ewogICJ0aW1lc3RhbXAiIDogMTY3OTUxMjY0NzkwNSwKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYjc0NTc4MDU0ZGZjZmY3NzUwYWUwNGQwYjI4ZDVkMWJhNzMxODM5OWIxMjYzNTFhNTMyYmY1YTkwYzllOTEzIgogICAgfQogIH0KfQ==",
                "HIPot9WOTBd3A1GVkvHaoCCABYivarECxcdHPk4gqkLuXksXirZyWrzzrIm6qbDOvgt7X02a6eT2PZJeVG8xAdN4cji7vj99kuNd2MeERr8g9VdVuKzfFHO6Lw9682jzPiXwtgmlM/DqDrpeXAUTqVC87pHFzlBe8c9rKfcGQtk1Evur6hActWb73Sp9B3TdREYnHr1mD6O0STuQnjQ0AIOyQHTWNebors/t14mzQx3DERd29XIT13rcgP/LzloZVHLALpoOfqAv33UhVBSv00kUbxiqSidXBHLnp9pmrmnPfhZ4mca6IVb9xj0yZqUvUWCGZfc9/p/IxvfZpMLze1hq6s1aeSkISvFRnLRVLWGMQoQXI5UyOwBweuiatQbJM/bkw3jGmDp8EW/YFaUfHy2EtKl54y5xfuLjCSv2D1Gw6TKKGZmkE3HDU7MtgX3Xv9al8Di3XffvYovgasxQfV0BJuo5caXoSmXy4x+k3PWxAfRAHfzzthPHngK5bplhdltlOn/IqCMNJpXkaC+8QKxBWHenFBuIcrBYApPUp1f3ZXrixHkWqo9PLx8TFZ+djjs/kBpG/oUvFp0S2iJtVfAHzGqkbgGlxg7l2tihkyfpG9G3SNWl6BOVs11e3zWsSZhpSLIoAh5Ejh1YX0PVXzWUOosMInPxG/LvkpSYcmA="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, ChatColor.GRAY + "bzzz-- Hello there-- " + player.getName() + " -- my name is-- Drem-Bot.");
        if (player.getName().contains("YoDrem")) {
            sendMessage(player, "Interesting. You are me, but human.");
        }
    }


    public void talk2 (Player p) {
        //String dialogue = getDialogue();

        //if (dialogue == null || dialogue.equals(""))
        //    return;

        StigglesPlayer sp = main.getPlayerManager().getStigglesPlayer(p.getUniqueId());
        HashSet<Quest.QuestName> quests = sp.getQuestsCompleted();

        ArrayList<TextComponent> clickables = new ArrayList<>();

        if (sp.hasQuestCompleted(Quest.QuestName.MORABITO_RECIPE)) {
            TextComponent new_clickable = new TextComponent("§6§l[Mr. Morabito]");
            new_clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dbc " + p.getName() + NPCManager.getNPCByName("Mr. Morabito").getId()));
            clickables.add (new_clickable);
        }
        if (sp.hasQuestCompleted(Quest.QuestName.NATALIES_REDEMPTION)) {
            TextComponent new_clickable = new TextComponent("§6§l[Captain Beast]");
            new_clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dbc " + p.getName() + NPCManager.getNPCByName("Captain Beast").getId()));
            clickables.add (new_clickable);
        }
        if (sp.hasQuestCompleted(Quest.QuestName.APPLE_A_DAY)) {
            TextComponent new_clickable = new TextComponent("§6§l[Mr. Orangeflips]");
            new_clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dbc " + p.getName() + NPCManager.getNPCByName("Mr. Orangeflips").getId()));
            clickables.add (new_clickable);
        }
        if (sp.hasQuestCompleted(Quest.QuestName.FISHING)) {
            TextComponent new_clickable = new TextComponent("§6§l[Mr. Orangeflips]");
            new_clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dbc " + p.getName() + NPCManager.getNPCByName("Luke the Fisherman").getId()));
            clickables.add (new_clickable);
        }

        if (!hasDialogue())
            return;

        TextComponent clickable = new TextComponent("§6§l<<CLICK TO TALK MORE>>");
        //clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tellraw " + p.getName() + " \"" + dialogue + "\""));
        clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/smm " + p.getName() + " " + getId ()));

        p.spigot().sendMessage(new BaseComponent[]{clickable});
    }
}
