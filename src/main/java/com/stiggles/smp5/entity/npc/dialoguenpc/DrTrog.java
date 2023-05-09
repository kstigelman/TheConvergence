package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DrTrog extends StigglesNPC {
    public DrTrog (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY3MTA0Mzg3MzkwNiwKICAicHJvZmlsZUlkIiA6ICI0ZTA4M2ZmNzExOGU0ODMxODcyMTFjYzA5M2QxNzNkNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJEclRyb2ciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWMyNTczMDEwNjkyYzg1NTU4YWYwMTA5NjQ5NjNmYTcxNWU5NGM1M2U1YjU4M2M0NmY5YTAxMzU1ZWEyYzk3ZCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9",
                "FpPKWl4pZRukqNUNIDKNLarURywjAdfiWvBnylH25LuCYTlQtqV6K3jnpswyWnCH81n8eURkBNNqKUvsJZfWhf7EK9uUn9UPtJa9xfUtDeYB/pOJdG5mcju+sgV/oPLUD55rfMauZXUAFwN2M1UG2iWTaAzUWKke7dc2yMz3s5jtRtuBIHIUVHIpKRn6sK90oZykmu5BR7U4UJOK5rmJ2T5RjCFxPza0UtXGXbqCD7t5cukMVdYmqVX2NBO5OCcASDSs+yzVxXIPUGOpgbm8pVeh7gStCfoq4ygU2SIi1B3vP9QVTkQ3CpZHF5Qscad9/tOqcl6RSglkMjCL1xYtpcxAm1Za/U3W4vEuWgAatoedqZQuLHxyrQ3/HqEdcN6gAvLkL/04rre2X4VDp/jWDkJaf48vGBEqJ7nHRfdG2qoZ39A0LtKRUrDDDjRStTAydNVX12N/pBZ0ivhcgwuRxnklsS2aR/C7U7l6kg+Abnzjw/1Puct4Ov/Vz0LuNOITgx7uratO3fMsmeCp2bayPLqLjNP2lhqJ594TRcATd0LMPfJcKMYUPDtukgkfRC06y6f7HZBCuOEB4bz9YwxWiUQhXIJgfFVByBlS/QFqKLNq/l2aUr5R9GM5i0Z7wOQtrkEVNe7x0JvGHQxLnTlYnXrAHGuTy/GiViIyNCZb4MA="
        );
    }
    
    @Override
    public void interactDialogue(Player player) {
        sendMessage(player,"Uh, hello?");
    }
}
