package com.stiggles.smp5.entity.npc.questnpc;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.entity.Player;

public class Drem extends QuestNPC {

    int interactCounter = 0;

    public Drem (SMP5 main) {
        super (main, "Drem");

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY3MTgyMDMxMTk0MSwKICAicHJvZmlsZUlkIiA6ICI2MTJmZDAxMWY4YTQ0ZGIwOTU3ZTNjM2MyZTBkYmFlZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJDYWxlbnRhZG94MTIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjM4NGE2Yzc3MmExY2MzMjU0NzFmNjhmODE2ODQwZDIzMzMzYTdjOTE5YjQzYTk1Y2M4NjkxMzY5NmQ1NWE0OCIKICAgIH0KICB9Cn0=",
                 "VzSj1fXlwV+gCEAGoJVaHSVOj0hgP0RHReUPcXSSDxNovRZxEs1k1iFkcHEIpBKh+On5omNhLGqP2PzXA8rBJLu+TVnM5EhxT+mcEZK49vcGVSbz52X4mF7sobvFDHIIhEAIMXvjZMM81gYE//Xe38WTl7wN7ZYIA5BMDn+HUNdvgIEfIaKFbsBiPxQQpttdzbMyJPU+7yTKixuPhOHAo4yhKmyNUW3wKcbLEgpPTt8asoYaToLEXgXwOoU7AFvBHYNi1DVNfaXaDlC8IDUDNu+YwrCcbonHd8v2J2MPE9w1sizDWegnK0AdUsG303OQ8Ukjxm0Bh689NNb5H6X1D8fiTHTD44NQhrWlO7GEDJAPcuo5/w26+T1gMaXrZByJIgtRdOrFCUiGAr/4rSspVj86YYvgji49c7W8pA6lUk1WTufiNcEOFprrQbtIYBOvKf3HAlY9J1IdKHgHGj+n9+OFV4QBiYPgH8QSSCE7H7i8MxvcvF3T3IODuzumv7EdRF46ur20ztVOW+j/kNC0pWCTbUg2r2GzDbIfUuSrlVm5MWltqkvE6f64k8/qEaEu2EWwF3W2mjCxYWMHUjS6XbEGpwDlhPv0jIAIGRNlQGmIojiA0jQsTSQ++LTyTJCOCtELK6CoW3cwqhAMw+XIlpdrH2ZsWvSUVH/q+yh/xo0="
        );
        setPos (-0.5, -59, 3.5);
    }


    @Override
    public void interactDialogue(Player player) {
        String msg = "";
        if (interactCounter == 0)
            msg = "Hello?";
        if (interactCounter == 1)
            msg = "You should leave.";
        if (interactCounter == 2) {
            msg = "I'm warning you.";
            interactCounter = 0;
        }

        player.sendMessage ("<" + getName () + "> " + msg);
        interactCounter++;

    }
    @Override
    public void onInteract (Player player) {
        interactDialogue(player);
    }
    @Override
    public boolean giveQuest(Player player) {
        return false;
    }
}
