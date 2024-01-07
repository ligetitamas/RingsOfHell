package me.ltommi.ringsofhell;

import me.ltommi.ringsofhell.DungeonStructure.Dungeon;
import me.ltommi.ringsofhell.commands.DungeonCommand;
import me.ltommi.ringsofhell.utils.ConfigLoader;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Dungeon dungeon;
    private FileConfiguration config;
    private FileConfiguration messages;
    private FileConfiguration dungeonData;
    @Override
    public void onEnable() {
        // Plugin startup logic
        dungeon= new Dungeon();
        this.config= ConfigLoader.Load("config");
        this.messages=ConfigLoader.Load("messages");
        this.dungeonData=ConfigLoader.Load("dungeonData");
        this.getCommand("dungeon").setExecutor(new DungeonCommand(dungeon, config, messages, dungeonData));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
