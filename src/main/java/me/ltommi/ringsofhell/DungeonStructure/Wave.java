package me.ltommi.ringsofhell.DungeonStructure;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.mobs.ActiveMob;

import java.util.ArrayList;

public class Wave {

    private ArrayList<Enemy> enemies;
    private ArrayList<ActiveMob> activeEnemies;
    public void SpawnEnemies(){
        for (Enemy enemy: enemies){
            enemy.getMob().spawn(BukkitAdapter.adapt(enemy.getSpawnLocation()),1);

        }
    }
}
