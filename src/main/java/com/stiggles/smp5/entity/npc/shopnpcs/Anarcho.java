package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class Anarcho extends StigglesNPC {

    public Anarcho (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4NjYyMzcyMzY5NCwKICAicHJvZmlsZUlkIiA6ICJiNTAwOGEyMGJkY2U0YjJlOTU5NWZlNzY2MDlmYjUzNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNT1JJU3hTVE9QQkFOIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMwOWE0MTJjMzY5MTQ2NmFhOTEzY2U5Yjg2NDRjNzkyYTNiZjgwODJmMjNjNDI5ZWJkNmVjYTNlYTEzMDViMDIiCiAgICB9CiAgfQp9",
                "S0lrTqEW1Y9jIqzF/S334REc8vBlKKi4uZZzT6p/py8FtPWo5NyZ4tgJQytBFDYCznEKNDe49iVwkn897Qdfr/GdOdq+//aUlTRsa3DVB51pP5a2Y7ntqMU+GJ39LoObQX5eBl25eTp8Y/025O1pgpbEHMNCCi4qfQYbnD4c5sjCrz2QUwc2FVUPrt1gt816y5AcquAYzwfMhAWS0y9aHbAAoBp++k0uIjT5alI4YR7ajADMQrSmFoY05DE0crRkRwecINPNywNBMhVNJFKRxHG2+bLZPqVSIkXUuUWaDy7Y3gXroBey9xXh8OdaGSdT9IpRho2IomCS/P+bTTatn+Gg1ODILu6TPfw8N5KtKFcBwJz+I9OKNoiKjSMOyrxMY3lBcDQrcTjXGTI1Wg4TV/PP8OmLvuuaETQ91oUxZZR/kAuVQoLwNFbfy/gYLPX+v3IZ6YM+o7ArCJHqnFw8M/nrfl1u+NwANES/99+5G74uZwgNjKlh6AK9wRxFEfmIp8i6Tfs282/4Vuuo48NvRJ68XlNnjsksp0JveB5jyUr53VDSFlCpefdmHqGwaxZRPe1eYC0mdxWOVYc46Y6fj0eb/Xwuxmo+wBc2PMxpTH6NvittcW/+wmIrXWkQY7iM/9/05Q8m47B0Gbr14vKDzRnyMgH54O04D/gw/dFbcrc="
        );
        //setRotation (110, 0);
    }
    @Override
    public void onInteract (Player player) {
        interactDialogue (player);
        /*if (Quest.isQuestComplete(player, Quest.QuestName.NATALIES_REDEMPTION)) {
            createGUI(player);
            showGUI (player);
        }*/
        talk (player);
    }
    @Override
    public void interactDialogue(Player player) {
        if (player.getName().contains ("ItsTigerFist")) {
            sendMessage(player, "Tiger... hello.");
            return;
        }
        if (Math.abs (main.getRandom()) % 2 == 0) {
            sendMessage(player, "Welcome to Anarcho's Castle. Heh.");
        }
        else {
            sendMessage(player, "Hi there. I didn't expect any visitors today.");
        }
    }
}
