package me.ltommi.ringsofhell.DungeonStructure;

import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.configuration.ConfigurationSection;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Wave {

    private ArrayList<Enemy> enemies;
    private ArrayList<ActiveMob> activeEnemies;

    public Wave(){
        this.enemies= new ArrayList<>();
        this.activeEnemies = new ArrayList<>();
    }
    public void AddEnemy(Enemy enemy){
       enemies.add(enemy);
    }
    public void SpawnWave(){
        for (Enemy enemy: enemies){
            activeEnemies.add(enemy.Spawn());
        }
    }
    public void DespawnWave(){
        for (ActiveMob mob :activeEnemies){
            mob.despawn();
        }
    }
}
