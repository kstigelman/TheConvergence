package com.stiggles.smp5.entity.npc.dialoguenpc.NetheriteQuest;

import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MineManager extends StigglesNPC {

    Pickaxes pickaxes = new Pickaxes();

    String signature = "sKEvFTkJOLAs2KSDRodjpJXTTPMh7tX4mquAdNuRomN21FTC1Q/JEbVw+9u+tg0nchQF/llSZuCJSNE0hnLQFhKmqaIcwWj03Uepp1aNR/Wf08eLk1wizJJcro1x3OW1xzGMf48IgpyxNa2YpPfuxsZBd5RxRg4xOEv4qvhK8Ye4M3dYyXCTtMklY3mMVRb0DXFqqKfv5KDs7vGMr5zybZXybIJ2vDmRb+4AaDDMAw19su2jCXk5s50zQSsnNZtpox/EEbc+qsLdTfmiJ0bP0WdsxeKz45byfVqvqcBq/FuRpdC4Kpgc0aZdAiYUyP1GwzkFqYda0LU66eFmN6Z1HjPGtOf70qs4GzZ8AB1nyxloD6n/0NYb+7TR+bSUeV9OMrMqlmyLpZSQo+yfTRXvIBWO8qy+oJP3lHTtN5V5HsLKs6fuEJefXwa25waRzgI77zwhcZbFot48lZa2xoQ3y8g4cE4gtU9qbFx+4SecQeNYSi2nUFFfvJswkHCigm5kLkxxhHQAiuH24GyhMmNUUlf/+etht6kQrc2UsOQWSdM5x2r7KCnI2iU4XYk6/5Kg6uWWbX39lvEzHew3UOGUdRu1Y13iaWiJ3hxgG5AxuzSiKtW0pHoIAv2FeAqBdDhYS/WEWTKbGxoNqiKe6nAKPcZRFvQucJ0O5Jy3dBuoTXg=";
    String value = "ewogICJ0aW1lc3RhbXAiIDogMTY4NzIyODM1NTgyNywKICAicHJvZmlsZUlkIiA6ICIzNmMxODk4ZjlhZGE0NjZlYjk0ZDFmZWFmMjQ0MTkxMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJMdW5haWFuIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2QzY2U4NDNlZjY1NWNiZmI0NDMwOWMwNDI2OGUxZDA3NTk3ZmExYjE3NTAwN2E3M2Q2ZjE2MjQ1MGU4ZDA2YmUiCiAgICB9CiAgfQp9";
    public MineManager(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin (value, signature);
    }

    @Override
    public void onInteract(Player p) {
        if (p.getInventory().contains(pickaxes.hardenedPickaxe())){
            sendMessage(p, "I've already given you what you need! Now go... or comeback later");
        } else {
            sendMessage(p, "Here, take this, you'll need down there depending on what you plan on mining.");
            p.getInventory().addItem(pickaxes.hardenedPickaxe());
        }
    }

    @Override
    public void interactDialogue(Player player) {
        return;
    }


}
