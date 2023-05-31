package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Tiger extends StigglesNPC {
    public Tiger (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4NTU0MDI3NzQ4MywKICAicHJvZmlsZUlkIiA6ICJjMDI0Y2M0YTQwMzc0YWFjYTk2ZTE2Y2MwODM1ZDE4MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb3JlVGVjaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kZWIwNzI3ZmFkOGM2YTk0ZmZhZDIyOTdmMDVkZjdmY2RjZGRjYzRmMDQ1OGM4ZThmOTg1ODIyMjFhOGIxZDg3IgogICAgfQogIH0KfQ==",
                "VLIq/q4/FEosBwVQRL6JvXRPyvP4FdZ746P1FgEyPLoY/L3O0ts/3BYSl3l8VZY39DpYIVDw+3NH2ZfyorYFSNMvRFmXFOhK9Ju3ck78MBtw0gW58bIbUz5Z3vxenzMXgOUeZKhPuN3/aDaONLF2w5t/L8GDGf9hUmmCREdylO7AVDCtmfQKeyHFJcy01oI8v5BZmUmYQ7GF7GmkXHNBJiHBFYqaPKCzoQQjka2/xpE46Fk6MZ4iXqpcMlv7REC/IQBN5wy22lIa/tobLdsE9fZH/Du50W8vCaIl/btADJZM1SKqTS4olFUrdCGXsEiUUfWmJhw8AnTWLJDuyKQ5gUKMSeMag0KGANTLZRJWVj2d75WcIKfGxEK+/Agnpl3X+2C3dGUbCc+p8/xI0aNNZSVKiGUwTAZSfH8+VeQ7st8GVQhhEQ/XuX5SutCu9WzoREgRu3dtkrr5oYZ8RtqRYArjC0o7qid7ckk0y6gp5l6mAJrhxFyaTjOfbXdrBwTg6ho/0C41kpoixcChpknDf+UvymK0ZE9lhdgHFPeGtcGAJ4miw979Yjqp//lqK2crQphKwfDbP3W5KwAtroQQ6WektWsR6SXKtryLO/xnt0NjPOdHoIdqEHjPfGXRV/pGr7miK1EtkDPy21GBhElXql3MQBwuJkH86TX/RP3mn5Q="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "Welcome to the Community Chest!");
    }
}
