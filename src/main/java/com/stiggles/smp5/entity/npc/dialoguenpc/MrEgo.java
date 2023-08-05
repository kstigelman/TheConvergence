package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MrEgo extends StigglesNPC {
    public MrEgo(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4NTkyMzE0OTU3MywKICAicHJvZmlsZUlkIiA6ICIxNGEwNThiNDc0NzE0ZTY2YjFhMTRmNDY3MDNlOWY3NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJFaW5tZXJhIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMzZDEzNDE5NWQxZTY4ZDAxOTEwNTQwZDNmNTMwYzdkMDdjYmM1MDRhNmU0YzhlZDdlNDI5ODYxNjg1NjAwOWIiCiAgICB9CiAgfQp9",
                "MgsWtUiG6Ju8XBjqmQXySLXeouhdeUqB4Z0XMrOB/zq5XtLQbrl7qMtPrLgAbfsw6GwV4rM4rgOHXOL66plzKd8crnq8GUB6j2ppBkzhuji4nA1emjknWeFeqFw0Rea3/vOdYR/B4jDXfP6LP5oMOFfvOVHcfNxVyq0vSbM/7VLRDkprY+WAhl/IpLLJ26ajpg2jJ+OlBOCOzlZpq1jVYlTUDaqrw8GlwJN9R52MK06ORbkc7XxPPoXy8wiqdj70OWZMd/6vMyM1aq27W1TxNYR4RiGPpTL8d9UOFs6qLAF/kGyCUykX8j4xzs4KY35lyN27OT3yVPRNKfU7ZFXIMZyAtNlCtdTSc6ris/q047Ql1Fkdn/JQlv+6EbVLGUeQiTADHYVr7kaj1QxnZZddzjkIzfqpqzwdJPcMDZ9C7I64fA6Vq21V29YbQxFUG3nlU4N5uIOfaR9xEXciAKT+9UCamCX0/EGQyTwH405TFnOWmD2zX7dAywLgXQsWt/ruknzCUuTOTE2O8NJyrvvM4AqxzxGdCVRdZ5etQMfd58YNOB63jg2ayCt3zP4zU19+veT3GAmfFEeNTWG1MJh/8ZMV/IL0t4xBEeZ6h/2GRIf6acyRNTV96GJOTjrBFeOietQEEvDs3/VMGycItDBPLttmF68wAFnDk+7S678C+jo="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        int n = Math.abs (main.getRandom() % 2);
        if (n == 0)
            sendMessage(player, "Hmmph. Hello there.");
        else
            sendMessage(player, "I'm Mr. Ego, President of EGO Labs.");
    }
}
