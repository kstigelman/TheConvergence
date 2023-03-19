package com.stiggles.smp5.dungeons;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;


import java.util.ArrayList;
import java.util.Stack;

public abstract class Dungeon {
    private World world;

    private int currentStage;
    ArrayList<Entity> worldEntities;

    int getCurrentStage () {
        return currentStage;
    };

    Dungeon () {
        world = Bukkit.getWorld("test_dungeon");
    }
    abstract int nextStage ();
    abstract boolean monstersDead ();
    abstract boolean completedStage ();
    abstract boolean completedDungeon ();
    void spawnMonsters (Stack<Entity> stack) {
        while (!stack.empty()) {
            Entity e = stack.pop ();
            worldEntities.add (e);


        }

    }
    abstract void initialize ();
    void initNPC () {

    }

}
