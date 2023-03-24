package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.entity.Player;

import java.util.Random;

public class DremBot extends StigglesNPC {


    SMP5 main;

    public DremBot (SMP5 main) {
        super (main, "Drem-Bot");

        SetSkin (
                "ewogICJ0aW1lc3RhbXAiIDogMTY3OTUxMjY0NzkwNSwKICAicHJvZmlsZUlkIiA6ICJmODJmNTQ1MDIzZDA0MTFkYmVlYzU4YWI4Y2JlMTNjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZXNwb25kZW50cyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yYjc0NTc4MDU0ZGZjZmY3NzUwYWUwNGQwYjI4ZDVkMWJhNzMxODM5OWIxMjYzNTFhNTMyYmY1YTkwYzllOTEzIgogICAgfQogIH0KfQ==",
                "HIPot9WOTBd3A1GVkvHaoCCABYivarECxcdHPk4gqkLuXksXirZyWrzzrIm6qbDOvgt7X02a6eT2PZJeVG8xAdN4cji7vj99kuNd2MeERr8g9VdVuKzfFHO6Lw9682jzPiXwtgmlM/DqDrpeXAUTqVC87pHFzlBe8c9rKfcGQtk1Evur6hActWb73Sp9B3TdREYnHr1mD6O0STuQnjQ0AIOyQHTWNebors/t14mzQx3DERd29XIT13rcgP/LzloZVHLALpoOfqAv33UhVBSv00kUbxiqSidXBHLnp9pmrmnPfhZ4mca6IVb9xj0yZqUvUWCGZfc9/p/IxvfZpMLze1hq6s1aeSkISvFRnLRVLWGMQoQXI5UyOwBweuiatQbJM/bkw3jGmDp8EW/YFaUfHy2EtKl54y5xfuLjCSv2D1Gw6TKKGZmkE3HDU7MtgX3Xv9al8Di3XffvYovgasxQfV0BJuo5caXoSmXy4x+k3PWxAfRAHfzzthPHngK5bplhdltlOn/IqCMNJpXkaC+8QKxBWHenFBuIcrBYApPUp1f3ZXrixHkWqo9PLx8TFZ+djjs/kBpG/oUvFp0S2iJtVfAHzGqkbgGlxg7l2tihkyfpG9G3SNWl6BOVs11e3zWsSZhpSLIoAh5Ejh1YX0PVXzWUOosMInPxG/LvkpSYcmA="
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
        int ni = rand.nextInt () % 9;

        if (ni <= 4)
            msg += "bzzz. Hello there <user> my name is";
        else if (ni <= 54)
            msg += "Don't take eggs when you are given the chance.";
        else
            msg += "Eggs look neat.";

        msg = msg.replace ("<user>", p.getName());

        p.sendMessage ("<" + getName () + "> " + msg);
    }
}
