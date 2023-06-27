package com.stiggles.smp5.items.crafting;

import com.stiggles.smp5.main.SMP5;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

public class CustomCrafting {
    public CustomCrafting(SMP5 main) {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.GREEN + "Emerald Dagger");
        meta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GOLD + "-- SPECIAL ITEM --",
                ChatColor.GRAY + "Has a random chance to drop",
                ChatColor.GRAY + "multiple emeralds on a kill. ",
                String.valueOf(ChatColor.GRAY),
                ChatColor.GRAY + "When enchanted with Sharpness V",
                ChatColor.GRAY + "the dagger unlocks a special",
                ChatColor.GRAY + "ability."));
        meta.setLocalizedName("emerald_dagger");
        item.setItemMeta(meta);

        ItemStack finalItem = new ItemStack(Material.EMERALD);
        ItemMeta finalMeta = finalItem.getItemMeta();
        finalMeta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        finalMeta.setUnbreakable(true);
        finalMeta.setDisplayName(ChatColor.GREEN + "Emerald Dagger");
        finalMeta.setLore(Arrays.asList(
                String.valueOf(ChatColor.GRAY),
                ChatColor.GOLD + "-- SPECIAL ITEM --",
                ChatColor.GRAY + "Has a random chance to drop",
                ChatColor.GRAY + "multiple emeralds on a kill. ",
                String.valueOf(ChatColor.GRAY),
                ChatColor.GRAY + "Since enchanted with Sharpness V,",
                ChatColor.GRAY + "the dagger deals 15-30 damage"));
        finalMeta.setLocalizedName("emerald_dagger");
        finalItem.setItemMeta(finalMeta);

        // Create the enchanted book with Sharpness V
        ItemStack sharpnessBook = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta bookItemMeta = (EnchantmentStorageMeta) sharpnessBook.getItemMeta();
        bookItemMeta.addStoredEnchant(Enchantment.DAMAGE_ALL, 5, true);
        sharpnessBook.setItemMeta(meta);

        // Create the shapeless recipe
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(main, "emerald_blade"), finalItem);
        recipe.addIngredient(new RecipeChoice.ExactChoice(sharpnessBook));
        recipe.addIngredient(new RecipeChoice.ExactChoice(item));

        // Register the recipe
        getServer().addRecipe(recipe);
    }
}
