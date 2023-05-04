package com.stiggles.smp5.entity.npc;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Morabito extends StigglesNPC {

    public Morabito (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MTk0MzkwNjg3OSwKICAicHJvZmlsZUlkIiA6ICJmNWYyNDcyZGVhNDY0ODJhYWUwNDllYjM2ODE5ODU0NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZXRhaWxlcnMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg2MzNhMmNmNGIyZTY2ODc0YzdhYmQ5YzlkYmQwYTljNWRiOTEyMDVhOTM4Y2JjOGM1MzQ3MjY0MmYzNjMwMiIKICAgIH0KICB9Cn0=",
                "iaxpVo6iKYZJQqSfnVdp1bclWSxkEsjwGGZ4wthwr4PupLePWEEUgb5oE7j24bgdLE+iD4zze2S9fRptVBoBJGiYrgBwhxLlSMVYNARHETNUYhPY34Nw8Wrplzg+zbtoX1LWR5tAAF/A8E9NII9i/AIVJBGqbtjh0sqIKtMkTKRmo2Ot/Pn8RFLUuAbErbhBtJpYjRSle1q4Ub3mmu2PU31RScIkeZ7Rk331JPsEmNkdCynIVLC0lWkLLIr64AmIjMMOyiBUBuIE1vrFtxvgAmDST6ZFVZoR9LldYnej2PIMxtbYgoIqpzxwSk5CHIyGTX5pyaAHU0Y13WaDqoOfzYFmG4DO52msHvgnwjBKIOzugJHvUSjWp2HIzi1Yx94tCRtSrZDv7KLzkrNGgEoQ2zIpMAtdQflnboXJp88yR3tqZzPiW0IAuBy8/0gCVR+Y04NAInPTh1QhF4iCqOFsV4zs2ub5Xc5YOMZQqWsIhekoxtvv6WnsrkQ8VEkZ/DECe02hg52A4zSXLr2kDg0fCkRIjrT4DDiXuSoZeue4ucEg+t1W7LlP448CwDrghSnJTvDNg6Iez1/+NgbzYt1NdapixHSN4B5kBNU8r70vWkCxjYyMMMF4EdQe9fFNlGkQM8HAIMKxjXgkaLPikK+ESkMRea+qUHUjNp+yD9iffc8="
        );
    }

    @Override
    public void interactDialogue(Player player) {
        int ni = main.getRandom() % 8;

        if (ni <= 2)
            sendMessage(player, "Hmmph who are you?");
        else if (ni <= 4)
            sendMessage(player, "Get out of my sight.");
        else if (ni <= 6)
            sendMessage(player, "You chose the wrong place to explore.");
        else
            sendMessage(player, "I'll call for the boys if you don't leave. That's a warning");

    }
}
