package me.ltommi.ringsofhell.DungeonStructure;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Enemy {
    MythicMob mob;
    Location spawnLocation;
    public Enemy(String name, Location spawnLocation){
        this.mob= MythicBukkit.inst().getMobManager().getMythicMob(name).orElse(null);
        this.spawnLocation=spawnLocation;
    }
    public Enemy(String name, double x, double y, double z, String worldName){
        this.mob= MythicBukkit.inst().getMobManager().getMythicMob(name).orElse(null);
        this.spawnLocation=new Location(Bukkit.getWorld(worldName),x,y,z);
    }
    public MythicMob getMob(){
        return mob;
    }
    public Location getSpawnLocation(){
        return spawnLocation;
    }

}