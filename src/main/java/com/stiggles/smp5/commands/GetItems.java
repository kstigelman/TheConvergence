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


/** Represents a command that gives an opped player custom items.
 *
 * @author Kael Hufford
 */
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

    /** Gives the player a multitude of custom items if they are opped.
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return True if the command succeeded, false otherwise.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
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
                inv.addItem (pendant);
                inv.addItem(pickaxes.hardenedPickaxe());
                MetalDetector.getTreasureModeDetector();
            }
        }
        return false;
    }
}
