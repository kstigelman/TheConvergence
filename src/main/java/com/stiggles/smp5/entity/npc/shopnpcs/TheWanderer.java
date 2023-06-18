package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Accent, if you are reading this, don't. This is a secret.
 *
 */
public class TheWanderer extends ShopNPC {

    public TheWanderer (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4NzAzMzI0MTEzMCwKICAicHJvZmlsZUlkIiA6ICJhNWVmNzE3YWI0MjA0MTQ4ODlhOTI5ZDA5OTA0MzcwMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJkZXBsZXRpbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E2ZmQxZGMzYjNiNGY5NjcxYWUwYTc5NzVhZmZkYjNkYmE5ZWFlOTMxMzQ1YTk1ZmMyZDJmZTdjZDUwZDFiYyIKICAgIH0KICB9Cn0=",
                "V5qTnPP9VCkR/rYDB/1IM785aNboFtvBcTUkZlzYbWtOT5Oh00M8FT5HvQ4EWQLob+tKCg4tVY7/fpVdBE77buvKOgQtTWID5chPcCbGXuTsT/xdnIQm8Mqg+iMkm5CtjXSm2+KgvrQ1neibWejBvSHFU0rfL8MX+msZGwOdMd6WdvKIgZ/YitXEdipKA8Bytywiwd5LnqYiSzYADq66Cs0mjA4qy4xQiaLd8UJmU6BMs3Ai5mt711dx5FE3Ygk8PJuZfRMMM5/VAgGGODdh5epiYh/YTMFhCSpXYfqb3wnJuOg1LaeXgdAmd0wpspW6jBhGLR1gvrcovksL6q0jcr8k37hNX3AH+CXOHC9wQsxsDvq0rroUQY0HOpieAtyQfxbvh4NvTEUm1KGzeFlu/RizRtT5CUjaLdbOHtgOg679FlyedSigenarHW2l821FKTQPj7yglgADVze6V1+sOFwFntZ9HKbgamqQ9w/JjzR8GsU6ySIjct3JsYny1+IEDpAQBPCfEo3zqAAlmzNz0auHAzvsN/woqx70uIjyBFy1ehu936C+/a3YH6lYMnsTa2K7oBYD9u4T1mi1p/EmxKbj3NMnO1hKd36M7GL4zTLEmq33a8+0PLQ57NP1+yfduIRl3sKDK7/dGR95hYqMnsgryFs9BXE4XFz44dGjxwY="
        );
    }

    @Override
    public void interactDialogue(Player player) {

    }

    @Override
    public void createGUI(Player player) {

    }

}
