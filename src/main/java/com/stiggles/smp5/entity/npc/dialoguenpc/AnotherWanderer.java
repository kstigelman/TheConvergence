package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AnotherWanderer extends StigglesNPC {

    String signature = "xPYTbMql5LQlPs10Zxg3MLB4x8tvRi3hN2qX77ao/W8WSPm5mSLZe028aCMCZxG/WGUXunKWWtkxLQF/2ovLn+l/cudr/DNAOCKUEyXdrG2YSwVLQV6drw3ZQ4uHsDO+duf1NvmQa7dVX9p+tD4rL7zi5ZbpGajER4QeVDQP4oeJgnmVITvQrk/DzdoDnAmWzzJX/gzOOzMMrTCwq+HxfsvcSsR+gCBvns6/CriToaB6qlwiVVyS2qXZU6xg6V42TEyAxkcFoHnJNBCOgCU7n2HR7pKjuEZ1aLyyuwKXtzS4TJ6Zbm18lZRlSsk6mSxL5/h00ETbPGNU+JPK9Pa6V39wBlS82wmPoVz0rvmEEbnk5QEvkoxA/hJT+ddGvlhte5Bw1hDQsqG1u3Rvys3NIx2llqOBykD5nlceeTS+CCUA78mVrvirLtPi79EPYdOpshgDD6lrwzh+cJeBUtQ4NSmCyeoualEGKkXO5WsBhTiUbySURhn2sYVksmpd7ViGj8/ej5Vg2ZuEPPpsFDk2sYa7CcKni+9e8DDM9RaLv9v6NwGoq/psbkDxawwDbOE065Ld9INTcrc1VckSEAP15zohiAAeCpKGty9E772QxUro0/tvcvRBS2mEflMsUmtVKefglVxVAQetHYjsSZqEAqYTIH6j1cKSQzQM3YS+D00=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4Njk0OTQwOTE2MiwKICAicHJvZmlsZUlkIiA6ICI3ZGY4NmY1MWFjZmI0MjQzYTkzNDQ1OTAyZDEzYTc0MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNclJpcHRpZGUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQ2YzE1YzhkZTJmMWM5ZWM3YTAzOTM4NzNlZjc3NDQ5YzY3OWQ3Nzc1MDlhNWZkNGRjMzg5MjAzODY3MzQyMCIKICAgIH0KICB9Cn0=";
    public AnotherWanderer(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value,signature);
    }

    @Override
    public void interactDialogue(Player player) {
        sendMessage(player, "This guy said there'd be glorious things!");
        speakLater(player, "I've only gotten sunburn...", Sound.ENTITY_VILLAGER_NO, 80);
    }

}
