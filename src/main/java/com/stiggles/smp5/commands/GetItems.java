package com.stiggles.smp5.commands;

import com.stiggles.smp5.items.*;
import com.stiggles.smp5.items.armor.AnarchysWardrobe;
import com.stiggles.smp5.items.armor.LunarArmor;
import com.stiggles.smp5.items.armor.PeacesSymphony;
import com.stiggles.smp5.items.bows.BoomBow;
import com.stiggles.smp5.items.bows.GlowBow;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GetItems implements CommandExecutor {
    Pickaxes pickaxes = new Pickaxes();
    Swords swords = new Swords();
    BoomBow boomBow = new BoomBow();
    GlowBow glowBow = new GlowBow();
    AnarchysWardrobe anarchysWardrobe = new AnarchysWardrobe();
    PeacesSymphony peacesSymphony = new PeacesSymphony();
    LunarArmor lunarArmor = new LunarArmor();
    BAGEL bagel = new BAGEL();
    GrapplingHook grapplingHook = new GrapplingHook();
    ItemStack pendant = Pendant.getPendant();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.isOp()) {
                Inventory inv = p.getInventory();
                inv.addItem(pickaxes.giveHandyToolPickaxe());
                inv.addItem(pickaxes.giveWardenWeaknessPickaxe());
                inv.addItem(swords.getTheEmeraldDagger());
                inv.addItem(swords.getTheMagmaCutlass());
                inv.addItem(boomBow.getBoomBowPlayer(p));
                inv.addItem(glowBow.getGlowBowPlayer());
                inv.addItem(lunarArmor.getLunarBoots());
                inv.addItem(lunarArmor.getLunarLeggings());
                inv.addItem(lunarArmor.getLunarHelmet());
                inv.addItem(lunarArmor.getLunarChestplate());
                inv.addItem(lunarArmor.getMoonShards(64));
                inv.addItem(grapplingHook.getHook());
                inv.addItem(bagel.getThatBagel());
                inv.addItem(pendant);
                inv.addItem(MetalDetector.getMineralModeDetector());
                inv.addItem(MetalDetector.getTreasureModeDetector());
                peacesSymphony.getItems(p);
                anarchysWardrobe.getItems(p);
            }
        }
        return false;
    }
}
