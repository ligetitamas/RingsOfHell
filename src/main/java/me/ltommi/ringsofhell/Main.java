package me.ltommi.ringsofhell;

import me.ltommi.ringsofhell.DungeonStructure.Dungeon;
import me.ltommi.ringsofhell.commands.DungeonCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Dungeon dungeon;
    @Override
    public void onEnable() {
        // Plugin startup logic
        dungeon= new Dungeon();
        this.getCommand("dungeon").setExecutor(new DungeonCommand(dungeon));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
