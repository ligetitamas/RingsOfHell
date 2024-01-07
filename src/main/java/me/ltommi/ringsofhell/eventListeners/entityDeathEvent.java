package me.ltommi.ringsofhell.eventListeners;

import me.ltommi.ringsofhell.DungeonStructure.Dungeon;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class entityDeathEvent implements Listener {
    private Dungeon dungeon;
    private Entity bossEntity;
    public entityDeathEvent(Dungeon dungeon, Entity bossEntity){
        this.dungeon = dungeon;
        this.bossEntity=bossEntity;
    }
    @EventHandler
    public void onPlayerJoin(EntityDeathEvent event) {
        if (event.getEntity()==bossEntity){
            dungeon.NextLevel();
            HandlerList.unregisterAll(this);
        }
    }
}
