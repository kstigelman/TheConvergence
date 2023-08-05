package com.stiggles.smp5.entity.npc.shopnpcs;

import com.stiggles.smp5.entity.npc.ShopNPC;
import com.stiggles.smp5.entity.npc.StigglesNPC;
import com.stiggles.smp5.entity.npc.dialoguenpc.Tiger;
import com.stiggles.smp5.main.SMP5;
import com.stiggles.smp5.stats.Quest;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;

import java.util.Arrays;

public class Anarcho extends ShopNPC {

    public Anarcho(SMP5 main, String name, Location location) {
        super(main, name, location);

        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY4NjYyMzcyMzY5NCwKICAicHJvZmlsZUlkIiA6ICJiNTAwOGEyMGJkY2U0YjJlOTU5NWZlNzY2MDlmYjUzNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNT1JJU3hTVE9QQkFOIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMwOWE0MTJjMzY5MTQ2NmFhOTEzY2U5Yjg2NDRjNzkyYTNiZjgwODJmMjNjNDI5ZWJkNmVjYTNlYTEzMDViMDIiCiAgICB9CiAgfQp9",
                "S0lrTqEW1Y9jIqzF/S334REc8vBlKKi4uZZzT6p/py8FtPWo5NyZ4tgJQytBFDYCznEKNDe49iVwkn897Qdfr/GdOdq+//aUlTRsa3DVB51pP5a2Y7ntqMU+GJ39LoObQX5eBl25eTp8Y/025O1pgpbEHMNCCi4qfQYbnD4c5sjCrz2QUwc2FVUPrt1gt816y5AcquAYzwfMhAWS0y9aHbAAoBp++k0uIjT5alI4YR7ajADMQrSmFoY05DE0crRkRwecINPNywNBMhVNJFKRxHG2+bLZPqVSIkXUuUWaDy7Y3gXroBey9xXh8OdaGSdT9IpRho2IomCS/P+bTTatn+Gg1ODILu6TPfw8N5KtKFcBwJz+I9OKNoiKjSMOyrxMY3lBcDQrcTjXGTI1Wg4TV/PP8OmLvuuaETQ91oUxZZR/kAuVQoLwNFbfy/gYLPX+v3IZ6YM+o7ArCJHqnFw8M/nrfl1u+NwANES/99+5G74uZwgNjKlh6AK9wRxFEfmIp8i6Tfs282/4Vuuo48NvRJ68XlNnjsksp0JveB5jyUr53VDSFlCpefdmHqGwaxZRPe1eYC0mdxWOVYc46Y6fj0eb/Xwuxmo+wBc2PMxpTH6NvittcW/+wmIrXWkQY7iM/9/05Q8m47B0Gbr14vKDzRnyMgH54O04D/gw/dFbcrc="
        );
        //setRotation (110, 0);
    }

    @Override
    public void onInteract(Player player) {
        if (checkQuestItems(player))
            return;
        interactDialogue(player);
        if (Quest.isQuestComplete(player, Quest.QuestName.RECRUIT_ANARCHO)) {
            createGUI(player);
            showGUI(player);
        }
        talk(player);
    }

    @Override
    public void createGUI(Player player) {
        gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# # a b c d e # #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)))
                .addIngredient('a', new AnarchyHelmet(120))
                .addIngredient('b', new AnarchyChestplate(250))
                .addIngredient('c', new AnarchyLeggings(200))
                .addIngredient('d', new AnarchyBoots(90))
                .addIngredient('d', new Totem (80))
                .build();
    }

    @Override
    public void interactDialogue(Player player) {
        if (player.getName().contains("ItsTigerFist")) {
            sendMessage(player, "Tiger... hello.");
            return;
        }
        if (Math.abs(main.getRandom()) % 2 == 0) {
            sendMessage(player, "Welcome to Anarcho's Castle. Heh.");
        } else {
            sendMessage(player, "Hi there. I didn't expect any visitors today.");
        }
    }

    @Override
    public boolean handleTrade(Player player, StigglesBaseItem item) {
        if (super.handleTrade(player, item)) {
            sendMessage(player, "Thanks.");
            return true;
        }
        sendMessage(player, "Hey, don't you even think about trying to steal!");
        return false;
    }

    public boolean checkQuestItems(Player player) {
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
            if (im == null || !im.hasLocalizedName())
                return true;
            if (im.getLocalizedName().equals("starry_letter")) {
                sendMessage(player, "...");
                sendMessageLater(player, "From Starry? Is this real?", 60);
                sendMessageLater(player, "We definitely need to stop Nouveau. He's caused us enough trouble in the past, and he doesn't need to keep it up now. I'm sick of his control.", 120);
                sendMessageLater(player, "Plus, this gives me a way to make up for all the things I've done in the past. Tell Starry I will be there.", 180);
                Bukkit.getScheduler().runTaskLater(main, () -> Quest.questComplete(player, Quest.QuestName.RECRUIT_ANARCHO, "Reconciliation", 100), 220);
                return true;
            }
        }

        return false;
    }

    private class AnarchyChestplate extends StigglesBaseItem {
        public AnarchyChestplate(int price) {
            super(price);
            item = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(166, 0, 199));
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Chestplate");
            meta.setLore(Arrays.asList(
                    String.valueOf(ChatColor.GRAY),
                    ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                    ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                    ChatColor.GRAY + "When paired with the full",
                    ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                    ChatColor.GRAY + "gain the following buffs",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                    ChatColor.GRAY + "- Regeneration 1",
                    ChatColor.GRAY + "- Strength 1",
                    ChatColor.GRAY + "- Speed 1"));
            meta.setLocalizedName("a_chestplate");

            AttributeModifier genericArmor = new AttributeModifier("generic.armor", 8, AttributeModifier.Operation.ADD_NUMBER);
            AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class AnarchyLeggings extends StigglesBaseItem {
        public AnarchyLeggings(int price) {
            super(price);
            item = new ItemStack(Material.LEATHER_LEGGINGS);
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(166, 0, 199));
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Chestplate");
            meta.setLore(Arrays.asList(
                    String.valueOf(ChatColor.GRAY),
                    ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                    ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                    ChatColor.GRAY + "When paired with the full",
                    ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                    ChatColor.GRAY + "gain the following buffs",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                    ChatColor.GRAY + "- Regeneration 1",
                    ChatColor.GRAY + "- Strength 1",
                    ChatColor.GRAY + "- Speed 1"));
            meta.setLocalizedName("a_chestplate");

            AttributeModifier genericArmor = new AttributeModifier("generic.armor", 8, AttributeModifier.Operation.ADD_NUMBER);
            AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }

    private class AnarchyHelmet extends StigglesBaseItem {
        public AnarchyHelmet(int price) {
            super(price);
            item = new ItemStack(Material.LEATHER_HELMET);
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(166, 0, 199));
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Helmet");
            meta.setLore(Arrays.asList(
                    String.valueOf(ChatColor.GRAY),
                    ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                    ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                    ChatColor.GRAY + "When paired with the full",
                    ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                    ChatColor.GRAY + "gain the following buffs",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                    ChatColor.GRAY + "- Regeneration 1",
                    ChatColor.GRAY + "- Strength 1",
                    ChatColor.GRAY + "- Speed 1"));
            meta.setLocalizedName("a_helmet");
            AttributeModifier genericArmor = new AttributeModifier("generic.armor", 3, AttributeModifier.Operation.ADD_NUMBER);
            AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class AnarchyBoots extends StigglesBaseItem {
        public AnarchyBoots(int price) {
            super(price);
            item = new ItemStack(Material.LEATHER_BOOTS);
            LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
            meta.setUnbreakable(true);
            meta.setColor(Color.fromRGB(166, 0, 199));
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Anarchy's Boots");
            meta.setLore(Arrays.asList(
                    String.valueOf(ChatColor.GRAY),
                    ChatColor.LIGHT_PURPLE + "-- SPECIAL ARMOR --",
                    ChatColor.LIGHT_PURPLE + "- ANARCHY'S WARDROBE -",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "USELSS BY ITSELF",
                    ChatColor.GRAY + "When paired with the full",
                    ChatColor.GRAY + "set of ANARCHY'S WARDROBE you",
                    ChatColor.GRAY + "gain the following buffs",
                    ChatColor.GRAY + ChatColor.BOLD.toString() + "IN COMBAT",
                    ChatColor.GRAY + "- Regeneration 1",
                    ChatColor.GRAY + "- Strength 1",
                    ChatColor.GRAY + "- Speed 1"));
            meta.setLocalizedName("a_boots");

            AttributeModifier genericArmor = new AttributeModifier("generic.armor", 2, AttributeModifier.Operation.ADD_NUMBER);
            AttributeModifier toughnessArmor = new AttributeModifier("generic.armor_toughness", 2, AttributeModifier.Operation.ADD_NUMBER);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR, genericArmor);
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessArmor);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
    private class Totem extends StigglesBaseItem {
        public Totem (int price) {
            super(price);
            item = new ItemStack(Material.TOTEM_OF_UNDYING);
        }

        public ItemProvider getItemProvider() {
            return new ItemBuilder(item).addLoreLines(getCost());
        }

        @Override
        public void handleClick(@NotNull ClickType clickType, @NotNull Player player, @NotNull InventoryClickEvent event) {
            handleTrade(player, this);
        }
    }
}
