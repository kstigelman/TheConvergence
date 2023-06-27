package com.stiggles.smp5.towns;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a region from one location to another. It can be used for blocks protection and things like WorldEdit.
 * Changes by Kaayylo -- Converted from 3-dimensional Cuboid to a 2-dimensional "CubeField". Ignores Y-coordinate.
 *
 * @author desht (Original code), KingFaris10 (Editor of code), Kaayylo (Editor of code)
 */


public class CubeField implements Cloneable, ConfigurationSerializable {
    protected final String worldName;
    protected final int x1, z1;
    protected final int x2, z2;

    /**
     * Construct a CubeField given two Location objects which represent any two corners of the CubeField.
     * Note: The 2 locations must be on the same world.
     *
     * @param l1 - One of the corners
     * @param l2 - The other corner
     */
    public CubeField(Location l1, Location l2) {
        if (!l1.getWorld().equals(l2.getWorld()))
            throw new IllegalArgumentException("Locations must be on the same world");
        this.worldName = l1.getWorld().getName();
        this.x1 = Math.min(l1.getBlockX(), l2.getBlockX());
        this.z1 = Math.min(l1.getBlockZ(), l2.getBlockZ());
        this.x2 = Math.max(l1.getBlockX(), l2.getBlockX());
        this.z2 = Math.max(l1.getBlockZ(), l2.getBlockZ());
    }

    /**
     * Construct a one-block CubeField at the given Location of the CubeField.
     *
     * @param l1 location of the CubeField
     */
    public CubeField(Location l1) {
        this(l1, l1);
    }

    /**
     * Copy constructor.
     *
     * @param other - The CubeField to copy
     */
    public CubeField(CubeField other) {
        this(other.getWorld().getName(), other.x1, other.z1, other.x2, other.z2);
    }

    /**
     * Construct a CubeField in the given World and xyz co-ordinates
     *
     * @param world - The CubeField's world
     * @param x1    - X co-ordinate of corner 1
     * @param z1    - Z co-ordinate of corner 1
     * @param x2    - X co-ordinate of corner 2
     * @param z2    - Z co-ordinate of corner 2
     */
    public CubeField(World world, int x1, int z1, int x2, int z2) {
        this.worldName = world.getName();
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);
        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    /**
     * Construct a CubeField in the given world name and xyz co-ordinates.
     *
     * @param worldName - The CubeField's world name
     * @param x1        - X co-ordinate of corner 1
     * @param z1        - Z co-ordinate of corner 1
     * @param x2        - X co-ordinate of corner 2
     * @param z2        - Z co-ordinate of corner 2
     */
    private CubeField(String worldName, int x1, int z1, int x2, int z2) {
        this.worldName = worldName;
        this.x1 = Math.min(x1, x2);
        this.x2 = Math.max(x1, x2);

        this.z1 = Math.min(z1, z2);
        this.z2 = Math.max(z1, z2);
    }

    /**
     * Construct a CubeField using a map with the following keys: worldName, x1, x2, z1, z2
     *
     * @param map - The map of keys.
     */
    public CubeField(Map<String, Object> map) {
        this.worldName = (String) map.get("worldName");
        this.x1 = (Integer) map.get("x1");
        this.x2 = (Integer) map.get("x2");
        this.z1 = (Integer) map.get("z1");
        this.z2 = (Integer) map.get("z2");
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("worldName", this.worldName);
        map.put("x1", this.x1);
        map.put("z1", this.z1);
        map.put("x2", this.x2);
        map.put("z2", this.z2);
        return map;
    }

    /**
     * Get the Location of the lower northeast corner of the CubeField (minimum XYZ co-ordinates).
     *
     * @return Location of the lower northeast corner
     */
    public Location getLowerNE() {
        return new Location(this.getWorld(), this.x1, 0, this.z1);
    }

    /**
     * Get the Location of the upper southwest corner of the CubeField (maximum XYZ co-ordinates).
     *
     * @return Location of the upper southwest corner
     */
    public Location getUpperSW() {
        return new Location(this.getWorld(), this.x2, 0, this.z2);
    }


    /**
     * Get the the centre of the CubeField.
     *
     * @return Location at the centre of the CubeField
     */
    public Location getCenter() {
        int x1 = this.getUpperX() + 1;
        int z1 = this.getUpperZ() + 1;
        return new Location(this.getWorld(), this.getLowerX() + (x1 - this.getLowerX()) / 2.0, 0, this.getLowerZ() + (z1 - this.getLowerZ()) / 2.0);
    }

    /**
     * Get the CubeField's world.
     *
     * @return The World object representing this CubeField's world
     * @throws IllegalStateException if the world is not loaded
     */
    public World getWorld() {
        World world = Bukkit.getWorld(this.worldName);
        if (world == null) throw new IllegalStateException("World '" + this.worldName + "' is not loaded");
        return world;
    }

    /**
     * Get the size of this CubeField along the X axis
     *
     * @return Size of CubeField along the X axis
     */
    public int getSizeX() {
        return (this.x2 - this.x1) + 1;
    }

    /**
     * Get the size of this CubeField along the Z axis
     *
     * @return Size of CubeField along the Z axis
     */
    public int getSizeZ() {
        return (this.z2 - this.z1) + 1;
    }

    /**
     * Get the minimum X co-ordinate of this CubeField
     *
     * @return the minimum X co-ordinate
     */
    public int getLowerX() {
        return this.x1;
    }


    /**
     * Get the minimum Z co-ordinate of this CubeField
     *
     * @return the minimum Z co-ordinate
     */
    public int getLowerZ() {
        return this.z1;
    }

    /**
     * Get the maximum X co-ordinate of this CubeField
     *
     * @return the maximum X co-ordinate
     */
    public int getUpperX() {
        return this.x2;
    }

    /**
     * Get the maximum Z co-ordinate of this CubeField
     *
     * @return the maximum Z co-ordinate
     */
    public int getUpperZ() {
        return this.z2;
    }

    /**
     * Get the Blocks at the eight corners of the CubeField.
     *
     * @return array of Block objects representing the CubeField corners
     */
    public Block[] corners() {
        Block[] res = new Block[4];
        World w = this.getWorld();
        res[0] = w.getBlockAt(this.x1, 0, this.z1);
        res[1] = w.getBlockAt(this.x1, 0, this.z2);
        res[2] = w.getBlockAt(this.x2, 0, this.z1);
        res[3] = w.getBlockAt(this.x2, 0, this.z2);
        return res;
    }

    /**
     * Expand the CubeField in the given direction by the given amount.  Negative amounts will shrink the CubeField in the given direction.  Shrinking a CubeField's face past the opposite face is not an error and will return a valid CubeField.
     *
     * @param dir    - The direction in which to expand
     * @param amount - The number of blocks by which to expand
     * @return A new CubeField expanded by the given direction and amount
     */
    public CubeField expand(CubeFieldDirection dir, int amount) {
        switch (dir) {
            case North:
                return new CubeField(this.worldName, this.x1 - amount, this.z1, this.x2, this.z2);
            case South:
                return new CubeField(this.worldName, this.x1, this.z1, this.x2 + amount, this.z2);
            case East:
                return new CubeField(this.worldName, this.x1, this.z1 - amount, this.x2, this.z2);
            case West:
                return new CubeField(this.worldName, this.x1, this.z1, this.x2, this.z2 + amount);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }

    /**
     * Shift the CubeField in the given direction by the given amount.
     *
     * @param dir    - The direction in which to shift
     * @param amount - The number of blocks by which to shift
     * @return A new CubeField shifted by the given direction and amount
     */
    public CubeField shift(CubeFieldDirection dir, int amount) {
        return expand(dir, amount).expand(dir.opposite(), -amount);
    }

    /**
     * Outset (grow) the CubeField in the given direction by the given amount.
     *
     * @param dir    - The direction in which to outset (must be Horizontal, Vertical, or Both)
     * @param amount - The number of blocks by which to outset
     * @return A new CubeField outset by the given direction and amount
     */
    public CubeField outset(CubeFieldDirection dir, int amount) {
        CubeField c;
        c = expand(CubeFieldDirection.North, amount).expand(CubeFieldDirection.South, amount).expand(CubeFieldDirection.East, amount).expand(CubeFieldDirection.West, amount);
        return c;
    }

    /**
     * Inset (shrink) the CubeField in the given direction by the given amount.  Equivalent
     * to calling outset() with a negative amount.
     *
     * @param dir    - The direction in which to inset (must be Horizontal, Vertical, or Both)
     * @param amount - The number of blocks by which to inset
     * @return A new CubeField inset by the given direction and amount
     */
    public CubeField inset(CubeFieldDirection dir, int amount) {
        return this.outset(dir, -amount);
    }

    /**
     * Return true if the point at (x,y,z) is contained within this CubeField.
     *
     * @param x - The X co-ordinate
     * @param y - The Y co-ordinate
     * @param z - The Z co-ordinate
     * @return true if the given point is within this CubeField, false otherwise
     */
    public boolean contains(int x, int y, int z) {
        return x >= this.x1 && x <= this.x2 && z >= this.z1 && z <= this.z2;
    }

    /**
     * Check if the given Block is contained within this CubeField.
     *
     * @param b - The Block to check for
     * @return true if the Block is within this CubeField, false otherwise
     */
    public boolean contains(Block b) {
        return this.contains(b.getLocation());
    }

    /**
     * Check if the given Location is contained within this CubeField.
     *
     * @param l - The Location to check for
     * @return true if the Location is within this CubeField, false otherwise
     */
    public boolean contains(Location l) {
        if (!this.worldName.equals(l.getWorld().getName())) return false;
        return this.contains(l.getBlockX(), l.getBlockY(), l.getBlockZ());
    }

    /**
     * Get the volume of this CubeField.
     *
     * @return The CubeField volume, in blocks
     */
    public int getArea() {
        return this.getSizeX() * this.getSizeZ();
    }


    /**
     * Get the CubeField representing the face of this CubeField.  The resulting CubeField will be one block thick in the axis perpendicular to the requested face.
     *
     * @param dir - which face of the CubeField to get
     * @return The CubeField representing this CubeField's requested face
     */
    public CubeField getFace(CubeFieldDirection dir) {
        switch (dir) {
            case Up:
                return new CubeField(this.worldName, this.x1, this.z1, this.x2, this.z2);
            case North:
                return new CubeField(this.worldName, this.x1, this.z1, this.x1, this.z2);
            case South:
                return new CubeField(this.worldName, this.x2, this.z1, this.x2, this.z2);
            case East:
                return new CubeField(this.worldName, this.x1, this.z1, this.x2, this.z1);
            case West:
                return new CubeField(this.worldName, this.x1, this.z2, this.x2, this.z2);
            default:
                throw new IllegalArgumentException("Invalid direction " + dir);
        }
    }


    /**
     * Get the CubeField big enough to hold both this CubeField and the given one.
     *
     * @param other - The other CubeField.
     * @return A new CubeField large enough to hold this CubeField and the given CubeField
     */
    public CubeField getBoundingCubeField(CubeField other) {
        if (other == null) return this;

        int xMin = Math.min(this.getLowerX(), other.getLowerX());
        int zMin = Math.min(this.getLowerZ(), other.getLowerZ());
        int xMax = Math.max(this.getUpperX(), other.getUpperX());
        int zMax = Math.max(this.getUpperZ(), other.getUpperZ());

        return new CubeField(this.worldName, xMin, zMin, xMax, zMax);
    }


    /**
     * Get a list of the chunks which are fully or partially contained in this CubeField.
     *
     * @return A list of Chunk objects
     */
    public List<Chunk> getChunks() {
        List<Chunk> res = new ArrayList<Chunk>();

        World w = this.getWorld();
        int x1 = this.getLowerX() & ~0xf;
        int x2 = this.getUpperX() & ~0xf;
        int z1 = this.getLowerZ() & ~0xf;
        int z2 = this.getUpperZ() & ~0xf;
        for (int x = x1; x <= x2; x += 16) {
            for (int z = z1; z <= z2; z += 16) {
                res.add(w.getChunkAt(x >> 4, z >> 4));
            }
        }
        return res;
    }

    @Override
    public CubeField clone() {
        return new CubeField(this);
    }

    @Override
    public String toString() {
        return "CubeField: " + this.worldName + "," + this.x1 + "," + this.z1 + "=>" + this.x2 + "," + this.z2;
    }


    public enum CubeFieldDirection {
        North, East, South, West, Up, Down, Horizontal, Vertical, Both, Unknown;

        public CubeFieldDirection opposite() {
            switch (this) {
                case North:
                    return South;
                case East:
                    return West;
                case South:
                    return North;
                case West:
                    return East;
                case Horizontal:
                    return Vertical;
                case Vertical:
                    return Horizontal;
                case Up:
                    return Down;
                case Down:
                    return Up;
                case Both:
                    return Both;
                default:
                    return Unknown;
            }
        }

    }

}