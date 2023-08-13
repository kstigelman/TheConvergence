package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.AbstractItem;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.Arrays;

public class Drem extends ShopNPC {

    int interactCounter = 0;

    private class Warhorn extends StigglesBaseItem {
        public Warhorn (int price) {
            super (price);
            item = new ItemStack (Material.GOAT_HORN);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.GOAT_HORN)
                    .addLoreLines("Get a random goat horn.")
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Saddle extends StigglesBaseItem {
        public Saddle (int price) {
            super (price);
            item = new ItemStack(Material.SADDLE);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.SADDLE)
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class HorseArmor extends StigglesBaseItem {
        public HorseArmor (int price) {
            super (price);

            Material material;
            int n = ri % 10;
            if (n == 0)
                material = Material.DIAMOND_HORSE_ARMOR;
            else if (n <= 2) {
                material = Material.GOLDEN_HORSE_ARMOR;
                cost = price * 2 / 3;
            }
            else if (n <= 6) {
                material = Material.IRON_HORSE_ARMOR;
                cost = price / 2;
            }
            else {
                material = Material.LEATHER_HORSE_ARMOR;
                cost = price / 4;
            }

            item = new ItemStack(material);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.DIAMOND_HORSE_ARMOR)
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class DragonBreath extends StigglesBaseItem {
        public DragonBreath (int price) {
            super (price);
            item = new ItemStack(Material.DRAGON_BREATH);
            ItemMeta im = item.getItemMeta();
            im.setDisplayName(ChatColor.DARK_PURPLE + "Natalie's Breath");
            item.setItemMeta(im);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.DRAGON_BREATH)
                    .setDisplayName(ChatColor.DARK_PURPLE + "Natalie's Breath")
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Vlad extends StigglesBaseItem {
        public Vlad (int price) {
            super (price);
            item = new ItemStack(Material.CROSSBOW);
            ItemMeta meta = item.getItemMeta();
            if (meta == null)
                return;
            meta.setLocalizedName("vlad");
            item.setItemMeta(meta);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.CROSSBOW)
                    .setDisplayName(ChatColor.GRAY + "Vladmir")
                    .addLoreLines(ChatColor.GRAY + "Belonged to a friend of the Captain's.")
                    .addLoreLines(getCost())
                    .addEnchantment(Enchantment.PIERCING, 5, true);
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class BoomBow extends StigglesBaseItem {
        public BoomBow (int price) {
            super (price);
            item = new ItemStack (Material.BOW);
            ItemMeta meta = item.getItemMeta();
            if (meta == null)
                return;
            meta.setLocalizedName("boom_bow");
            item.setItemMeta(meta);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(Material.BOW)
                    .setDisplayName(ChatColor.RED + "Boom Bow")
                    .addLoreLines("This bow has an explosive personality")
                    .addLoreLines(getCost());
        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Pendant extends StigglesBaseItem {
        public Pendant (int price) {
            super (price, "pendant");
            /* We need to make sure that the player gets the horse armor back once horse is removed.
                or, what if the pendant is a 1 time use to spawn a horse?
             */
            item = new ItemStack(Material.CHARCOAL);
            ItemMeta meta = item.getItemMeta();
            if (meta == null)
                return;
            meta.setDisplayName(ChatColor.DARK_PURPLE + "Natalie's Pendant");
            meta.setLocalizedName("pendant");
            meta.setLore(Arrays.asList (
                    ChatColor.BLUE + "Special Item",
                    ChatColor.GRAY + "She was a symbol of justice.",
                    "",
                    ChatColor.GOLD + "Right click " + ChatColor.GRAY + "to spawn a horse on demand.",
                    ChatColor.GRAY + "This item will be consumed after use."
            ));
            item.setItemMeta(meta);
        }
        public ItemProvider getItemProvider () {
            return new ItemBuilder(item).addLoreLines(getCost());

        }
        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Locked extends AbstractItem {
        String lore;
        public Locked (String description) {
            lore = description;
        }
        @Override
        public ItemProvider getItemProvider() {
            return new ItemBuilder(Material.RED_STAINED_GLASS_PANE)
                    .setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED")
                    .addLoreLines(ChatColor.RED + lore);
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            playSound (player, Sound.ENTITY_VILLAGER_NO);
        }
    }
    public Drem (SMP5 main, String name, Location location) {
        super (main, name, location);

        setSkin ("ewogICJ0aW1lc3RhbXAiIDogMTY4MjE5NTQ5NDI0OCwKICAicHJvZmlsZUlkIiA6ICIzNzdmYzE2NzE2ZDY0NDM4YjNkYjQzYzYyOWExYTkyOCIsCiAgInByb2ZpbGVOYW1lIiA6ICJTd2VkZW5zdHlsZTM0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2MxZjYxODRmMDQyZjc1NTdlMjA1OGYzZDllOWVjMmFjY2UyNzU0YzRhYzFjMTFkZGI5MWZmMmU2YjAyNjhhZGEiCiAgICB9CiAgfQp9",
                "N083dFQnybPFkzN+Za1SOk4WSM387hIIGKOklJ/PP3qq3JLOFKewvbPNCQqWI65sbVmVkZn6vrBDcnfa0Gsf49G2ihi5T5n15Wn8VCeU45xg4/cpmTulzEnLq1dX1aKcJ7WTWp0XWdmb60CmaSk7lllECAqHkmNLNz6wq32kge2jG0pHkK2+Hym7UCib2CctSM92gkqqG48zn3hLmVME6c1T+SPsGZS/V1CioAJd83Mv2+Egn5hygNoGvwHnatkSKIkK8WkZdmV2eSegLvX+V+im9ZnFFfILow5Sz9XisQwp8+X6Ew5iXco5eOuzcKflntdkGdva2jpox6BcgfHIxswwWKrPcMdrs6tgb8wFSyQ9r0Wt5W8OppdBsKkE/DAWBSSxg0YmXpG6SeBUJ0K4j2DB/l6AiJWN1AcMqSejehxMeyLrWqSYZczMcncxZ7i+/eal8LhtZTVye9fD8yEuClsqLqY/Fb9mgJSmaNhDkcJaQ19+SyAvUK0tWfQjmYUws1hfyA7U6TBvNvNPsZSjd8e6zpaNeqGLxu72CIyFFTJc4SiCTGFUd2Ly1JEwr7H2ZC3ziMa0gJ0QkVhC2Hr8vXvUci8dScd8nVKScgPMk/eoIWecgxj1ERTkaSzJPV/JiTYTIDGwzT3YyR2rncTa4KDEKpeG2k2GpfO0rzFikao="
        );
        //setRotation (110, 0);
    }
    @Override
    public void onInteract (Player player) {
        /*
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im != null && im.getLocalizedName().equals("drem_book")) {
                if (!Quest.isQuestComplete(player, Quest.QuestName.NATALIES_REDEMPTION)) {
                    player.getInventory().getItemInMainHand().setAmount(0);
                    sendMessage(player, "What's this?");
                    Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "Wh- what?"), 60);
                    Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "But how?"), 120);
                    Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "I have gone by Captain Beast for years, but my real name is Drem. But I am not the same Drem written about in this book. There are things written in here that parallels my own life, but its different."), 180);
                    Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "This explains why Nouveau hates me so much. His greatest enemy was me. Another version of me."), 240);
                    Bukkit.getScheduler().runTaskLater(main, () -> sendMessage(player, "I can't sit by any longer. I will protect the people of this world against Nouveau. We will bring him to justice."), 300);


                    Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.NATALIES_REDEMPTION, "Natalie's Redemption", 200), 340);
                    return;
                }
            }
            if (im != null && im.getLocalizedName().equals("nats_breath")) {
                sendMessage(player, "Natalie's Breath? I mean, I had a horse named Natalie once. But what's this sword got to do with anything? Get out.");
                return;
            }
            if (im != null && im.getLocalizedName().equals("starry_letter")) {
                if (Quest.isQuestComplete(player, Quest.QuestName.NATALIES_REDEMPTION)) {
                    sendMessage(player, "What's this? Is this the same Starry who wrote that journal you brought me?");
                    sendMessageLater(player, "I do not know her, but she obviously wants to fight Nouveau too. Tell her you can count me in.", 60);
                    if (!Quest.isQuestComplete(player, Quest.QuestName.RECRUIT_DREM))
                        Quest.questComplete(player, Quest.QuestName.RECRUIT_DREM, "The Old Hero", 50);
                    return;
                }
                sendMessage(player, "Huh? Who's Starry? Get out!!");
                return;
            }
        }*/
        interactDialogue (player);

        createGUI(player);
        showGUI (player);
        /*
        if (Quest.isQuestComplete(player, Quest.QuestName.NATALIES_REDEMPTION)) {
            createGUI(player);
            showGUI (player);
        }*/
        talk (player);
    }
    @Override
    public void interactDialogue(Player player) {
        /*if (player.getName().contains ("YoDrem")) {
            sendMessage(player, "Impossible, are you trying to taunt me??? How do you know who I am??? Get out of here. Now.");
        }
        if (!Quest.isQuestComplete(player, Quest.QuestName.NATALIES_REDEMPTION)) {
            String msg = "";
            if (interactCounter == 0)
                msg = "Hello?";
            if (interactCounter == 1)
                msg = "You should leave.";
            if (interactCounter == 2) {
                msg = "I'm warning you.";
                interactCounter = 0;
            }

            sendMessage(player, msg);
            interactCounter++;
            return;
        }*/
        sendMessage(player, "This used to be Kaayylo and I's bunker during the Cryptorg outbreak. Now, we have to defend it from Nouveau.");
    }

    @Override
    public void createGUI(Player player) {
        //AbstractItem lockedSlot = new Locked ("? ? ?");
        /* if (player has visited ruins)*/
        //lockedSlot = new Pendant(4000);

        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# a b c d e f g #",
                        "# # # # # # # # #")
                .addIngredient ('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient( 'a', new Saddle (120))
                .addIngredient( 'b', new HorseArmor(80))
                .addIngredient( 'c', new Warhorn(200))
                .addIngredient( 'd', new DragonBreath(90))
                .addIngredient( 'e', new Vlad (300))
                .addIngredient( 'f', new Pendant (400))
                .addIngredient( 'g', new BoomBow (300))
                .build ();
    }
}
