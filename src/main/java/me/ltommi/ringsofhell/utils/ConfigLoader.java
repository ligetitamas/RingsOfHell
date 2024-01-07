package me.ltommi.ringsofhell.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.IOException;

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
    public static void Save(String name, FileConfiguration config){
        try{
            config.save(new File(Bukkit.getPluginManager().getPlugin("RingsOfHell").getDataFolder().getAbsolutePath()+name+".yml"));
        } catch (Exception e){
            Bukkit.getLogger().info(""+e);
        }
    }
}
