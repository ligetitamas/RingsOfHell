package me.ltommi.ringsofhell.DungeonStructure;

import me.ltommi.ringsofhell.eventListeners.entityDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Level {
    private Dungeon dungeon;
    private Enemy boss;
    private ArrayList<Wave> waves;
    private ArrayList<Player> playerList;
    private Location spawnLocation;
    private int currentWave;
    private int timeBetweenWaves;
    private BukkitTask task;

    public Level(Dungeon dungeon, ConfigurationSection levelSection, ArrayList<Player> playerList){
        this.dungeon=dungeon;
        this.waves = new ArrayList<>();
        this.playerList=playerList;
        this.spawnLocation=new Location(Bukkit.getWorld(levelSection.getString("spawnLocation.world")),levelSection.getInt("spawnLocation.x"),levelSection.getInt("spawnLocation.y"),levelSection.getInt("spawnLocation.z"));
        currentWave=0;
        SetupWaves(levelSection);
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
        ConfigurationSection bossSection = levelSection.getConfigurationSection("boss");
        boss=new Enemy(bossSection.getString("mobName"), bossSection.getInt("x"),bossSection.getInt("y"),bossSection.getInt("z"), bossSection.getString("world"));
    }
    public void Run(){
        TeleportPlayers();
        ScheduleWave();
    }
    private void TeleportPlayers(){
        for (Player player: playerList){
            player.teleport(spawnLocation);
        }
    }
    private void ScheduleWave(){
        currentWave++;
        timeBetweenWaves = 45;
        task = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("RingsOfHell"), ()-> {
            if(timeBetweenWaves <6 && timeBetweenWaves >0){
                for (Player player : playerList) {
                    player.sendMessage(timeBetweenWaves + "");
                }
            }
            if(timeBetweenWaves == 0){
                for (Player player : playerList) {
                    player.sendMessage("start");
                }
                waves.get(currentWave).SpawnWave();
                if (currentWave<waves.size()){
                    ScheduleWave();
                }
                else{
                    ScheduleBoss();
                }
                task.cancel();
            }
            timeBetweenWaves--;
        }, 20, 20);
    }
    private void ScheduleBoss(){
        Bukkit.getServer().getPluginManager().registerEvents(new entityDeathEvent(dungeon, boss.Spawn().getEntity().getBukkitEntity()), Bukkit.getPluginManager().getPlugin("RingsOfHell"));
    }
}
