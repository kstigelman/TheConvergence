package com.stiggles.smp5.entity.npc.dialoguenpc;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.UUID;

public class MindlessGuy extends StigglesNPC {
    String signature = "MZkN9FMFgjbe5X1nI5hgp/opBwkWC6u+0mntyZH6HIiIXcUacsIQpxeAoJwgsK0SPq0g/jZ7z5yN5MbrbIx58l+V979/ZBqapbeGEjmTFMjAy6YOBVcj6yVkKMK2mgZstciqkPLHoaMafTbsl9NR0HwyT+GDP7C5lnp+1yQJD60SH7t9mvKyWOnaLUpPmXhRBX3FIpY4EYS/JMNPRPaOCb3M4MwPj7yH7KEujbTKCApxCq6uS7/KMZW9hbNUsbRy6i+U2I1I9F/gVQZWz7Up+UgZSEP/l/9dqKtvdB0TPeX5+Rk4S/VBtA75jlBtMFuFjjAWT8+wzK73F/9gTBHXIiv5s75/B8VOqvvpS+srd08ViPDPkEctvj4tPdu0clGqV7NhTN+zXh1590nWT3SSRdBOCMoTpUMNde928o2tqoK+P7hcj/b5PZO+19Hk+gRVn6GBX3zPrqkz/ApSHz8p3FkSsuBYABI5rkSTmSF0GBjWGp6nozIW4xdfeibccAnhuwQPamxZX30gamNfoHVPUW5fAWGaWBeyf+kwNj/JGfV95GFj7p/I/Ymflqm0PR0OSxDC2KgX1wf8PyVwmK3nHWbMkvGnYQApvzI2F19Ygb8qwjxMbqPLMvDD6ngZmcwVVGDqTGyU2FG4ybMgdzbFSrppom+7IOgeDyOmTkpiorU=";

    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4NjQ0MjMzNDI5OCwKICAicHJvZmlsZUlkIiA6ICJkOTcwYzEzZTM4YWI0NzlhOTY1OGM1ZDQ1MjZkMTM0YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDcmltcHlMYWNlODUxMjciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ0NDY2YTY2ZDg1YzE1ZGEyZTM4YTU2OGQxOTVkM2I3YTI5YTQwZGM4OTM3MGZmNjFkODFiNGJhMWM4MmFlNSIKICAgIH0KICB9Cn0=";
    public MindlessGuy(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin(value,signature);
    }

    @Override
    public void interactDialogue(Player player) {
        int ni = main.getRandom() % 3;

        if (ni <= 1) {
            sendMessage(player, "Sup?");
        } else {
            if (player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("pet_rock")){
                sendMessage(player, "I see your looking for me?");
                speakLater(player, "Well, I like your confidence and determination.", Sound.ENTITY_VILLAGER_CELEBRATE, 60);
                speakLater(player, "Here's your reward, take it as gift for following through with the quest.", Sound.ENTITY_VILLAGER_CELEBRATE, 60);
                speakLater(player, "Take it to the desert, there, you'll find a wizard. Give it to him.", Sound.ENTITY_VILLAGER_CELEBRATE, 120);
                player.getInventory().addItem(netherArtifact());
            } else {
                sendMessage(player, "Just movin around, nothin to see here.");
            }
        }
    }

    private ItemStack netherArtifact(){
        ItemStack netherArtifact = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) netherArtifact().getItemMeta();
        skullMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Nether Artifact");
        skullMeta.setLore(Arrays.asList(ChatColor.BLUE + "Quest Item", ChatColor.GRAY + ChatColor.ITALIC.toString() + "The beginning of a new quest..."));
        skullMeta.setLocalizedName("nether_artifact");

        PlayerProfile p = Bukkit.createPlayerProfile(UUID.randomUUID());
        try {
            PlayerTextures pt = p.getTextures();
            pt.setSkin(new URL("http://textures.minecraft.net/texture/cda26e1cfe392326ee12a4ba658710927cc988f8c150a2ee7886340068b531a4"
            ));
            p.setTextures(pt);
        }
        catch (MalformedURLException e) {

        }
        skullMeta.setOwnerProfile(p);


        netherArtifact.setItemMeta(skullMeta);
        return netherArtifact;
    }
}
