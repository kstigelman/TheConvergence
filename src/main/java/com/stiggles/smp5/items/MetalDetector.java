/** This class contains everything relating to the metal detector.
 *
 * @author Kael Hufford, Kyler Stigelman
 */

package com.stiggles.smp5.items;

public class MetalDetector {
    /**
     *
     * The Metal Detector as an ItemStack will be breakable, but not be able to break anything.
     *  it will also be "Material.NETHERITE_SHOVEL"
     *  the item wil not be able to be enchanted with Mending or Unbreaking
     *  the item will lose durability based off of the action if preformed:
     *              Mineral Mode | Mode 1 --> Removes 127 Durability / Use (15 <=> 16 Max uses)
     *              Treasure Mode | Mode 2 --> Removes 254 Durability / Use (7 <=> 8 Max uses)
     *
     * The metal detector thorough description:
     * The detector can find what's described below:
     * - Mineral Mode (Normal and Deepslate): -> Only in Mineral Mode | Mode 1
     *      - Coal Ore
     *      - Iron Ore
     *      - Copper ore
     *      - Gold Ore
     *      - Redstone Ore
     *      - Emerald Ore
     *      - Lapis Ore
     *      - Diamond Ore
     *      - Ancient Debris
     * - "Treasures" -> Only in Treasure Mode | Mode 2
     *      - Chests that are hidden and spawned during a scan. They replace a piece of sand (or dirt) and glow.
     * <br>
     * Functionality:
     * - Uses PlayerInteractEvent
     * (Action.RIGHT_CLICK_BLOCK || Action.RIGHT_CLICK_AIR)
     * - Gets the mode that it is currently in
     * - Then switches it - setMode(ItemStack metalDetector) -
     * - Then changes the ItemMeta based on the mode (apart of - setMode() - function)
     * - Plays a little sound effect (apart of - setMode() - function)
     * <br>
     * (Action.LEFT_CLICK_BLOCK || Action.LEFT_CLICK_AIR)
     * - If the player left-clicked, prevent the block from breaking using BlockBreakEvent.setCancelled(true);
     * - If player is holding the metal detector, scan within a 5x5x5 radius from where the player interacted --> PlayerInteractEvent.getBlock()
     * - During the scan, get the mode that it's in - getMode(ItemStack metalDetector) -
     * - When scanning, play a little beep(ish) sound effect to play while scanning (per Y level).
     * Mode conditions:
     * -- Mineral Mode:
     *      - Gets a 5x4x5 (Offset of x:0, y:-4 ,z:0) from the clicked block --> PlayerInteractEvent.getBlock()
     *          The Metal Detector searches one Y-level at a time. (5x1x5) / 1/2 Second (2 Seconds for whole search, 8 second full cooldown)
     *      - Finds any ores listed above,
     *      - If they are found, an invulnerable, invisible shulker spawns on that location.
     *          That shulker gets glowing for 5 seconds and then gets removed.
     *      - The Metal Detector is then put on a 10 seconds cooldown.
     *          If the player tries to use it during the cooldown, they get an error message:
     *              "Your Metal Detector is currently on a cooldown! Please wait before using it!"
     * <br>
     * -- Treasure Mode:
     *      - Gets a 8*4*8 (Offset of x:0, y:-4 ,z:0) from the clicked block --> PlayerInteractEvent.getBlock()
     *          The Metal Detector searches one Y-level at a time. (8*1*8) / 1/2 Second (2 Seconds for whole search, 12 second full cooldown)
     *      - Finds a spot to place the chest.
     *          Rolls a number (1 <=> 3)
     *              If the right number is rolled, it spawns a chest.
     *                  The chest loot - getLoot(int randomNumber) -
     *      - If the correct number is rolled, an invulnerable, invisible shulker spawns on that location.
     *          That shulker gets glowing for 5 seconds and then gets removed.
     *      - The Metal Detector is then put on a 20 seconds cooldown.
     *          If the player tries to use it during the cooldown, they get an error message:
     *              "Your Metal Detector is currently on a cooldown! Please wait before using it!"
     *<br>
     *  -- Functions Needed --
     *      getMode(ItemStack metalDetector)
     *          Will return with the int of the mode that "metalDetector" is in.
     *          Modes:
     *              Mineral Mode | Mode 1
     *              Treasure Mode | Mode 2
     *<br>
     *      setMode(ItemStack metalDetector)
     *          Will set the mode that "metalDetector" is in to the opposite.
     *          Modes:
     *              Mineral Mode | Mode 1 -Will Be Set To-> Treasure Mode | Mode 2
     *              Treasure Mode | Mode 2 -Will Be Set To-> Mineral Mode | Mode 1
     *<br>
     *      getLoot(Location location, int randomNumber)
     *          Will return with a set inventory that is decided by 10 loot tables that are influenced on Ancient City and Desert Temple.
     *              Get 5 from ancient city and 5 from desert temple. Then make 2 OP, 3 Good, 3 Decent, 2 Bad loot tables.
     *                  Roll a number, 1 <=> 20
     *                      1 / 2 --> Make it the OP chest
     *                      3 / 7 --> Make it the Good chest
     *                      8 / 13 --> Make it the Decent chest
     *                      (else) --> Make it the Bad chest
     *          The chest slot loot is decided using a technique like used for Merchants (InventoryManager.java) except limited to 5 options per slot.
     *              Every chest has anywhere from 5-15 slots being used (Dependent on a roll from 1/10)
     */
}
