package me.ltommi.ringsofhell.DungeonStructure;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Level {
    private ArrayList<Wave> waves;
    public Level(ConfigurationSection levelSection){
        waves = new ArrayList<>();

    }
    private void SetupWaves(ConfigurationSection levelSection){
        int waveCount=0;
        Set<String> keys = levelSection.getKeys(false);
        for (String key : keys){
            if (key.contains("wave")){
                waveCount++;
            }
        }
        for (int i=1 ; i <= waveCount; i++){
            List<Map<?, ?>> enemyList = levelSection.getMapList("wave_"+i);
            Wave newWave = new Wave();
            for (Map enemyMap : enemyList){
                newWave.AddEnemy(new Enemy(enemyMap.get("mobName").toString(), Integer.parseInt(enemyMap.get("x").toString()),Integer.parseInt(enemyMap.get("y").toString()), Integer.parseInt(enemyMap.get("z").toString()),enemyMap.get("world").toString()));
                waves.add(newWave);
            }
        }
    }
    public void Run(){

    }
}
