package com.stiggles.smp5.commands;

import com.stiggles.smp5.items.Pickaxes;
import com.stiggles.smp5.items.Swords;
import com.stiggles.smp5.items.armor.AnarchysWardrobe;
import com.stiggles.smp5.items.armor.PeacesSymphony;
import com.stiggles.smp5.items.bows.BoomBow;
import com.stiggles.smp5.items.bows.GlowBow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetItems implements CommandExecutor {
        Pickaxes pickaxes = new Pickaxes();
        Swords swords = new Swords();
        BoomBow boomBow = new BoomBow();
        GlowBow glowBow = new GlowBow();
        AnarchysWardrobe anarchysWardrobe = new AnarchysWardrobe();
        PeacesSymphony peacesSymphony = new PeacesSymphony();

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if (sender instanceof Player){
                Player p = (Player) sender;
                if (p.isOp()) {
                    p.getInventory().addItem(pickaxes.giveHandyToolPickaxe());
                    p.getInventory().addItem(pickaxes.giveWardenWeaknessPickaxe());
                    p.getInventory().addItem(swords.getTheEmeraldDagger());
                    p.getInventory().addItem(swords.getTheMagmaCutlass());
                    p.getInventory().addItem(boomBow.getBoomBowPlayer(p));
                    p.getInventory().addItem(glowBow.getGlowBowPlayer());
                    peacesSymphony.getItems(p);
                    anarchysWardrobe.getItems(p);
                }
            }
            return false;
        }
}
