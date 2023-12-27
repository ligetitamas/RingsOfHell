package me.ltommi.ringsofhell.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigLoader {
    public static FileConfiguration Load(String name){
        YamlConfiguration config;
        File configFile =new File(Bukkit.getPluginManager().getPlugin("RingsOfHell").getDataFolder(),name+".yml");
        if (!configFile.exists()){
            Bukkit.getPluginManager().getPlugin("RingsOfHell").saveResource(name+".yml",false);
        }
        config=YamlConfiguration.loadConfiguration(configFile);

        return config;
    }
}
