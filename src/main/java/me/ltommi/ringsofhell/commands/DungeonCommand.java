package me.ltommi.ringsofhell.commands;

import me.ltommi.ringsofhell.DungeonStructure.Dungeon;
import me.ltommi.ringsofhell.utils.ColorTranslate;
import me.ltommi.ringsofhell.utils.ConfigLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DungeonCommand implements CommandExecutor {
    private FileConfiguration config;
    private FileConfiguration messages;
    private FileConfiguration dungeonData;
    private Dungeon dungeon;
    public DungeonCommand(Dungeon dungeon, FileConfiguration config, FileConfiguration messages, FileConfiguration dungeonData){
        this.config= config;
        this.messages= messages;
        this.dungeonData= dungeonData;
        this.dungeon=dungeon;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("roh.player")){
                if (args.length==0){
                    //   /dungeon
                    dungeon.GUI().Open(player);
                }
                else if(args.length==1 && args[0].equals("leave")){
                    dungeon.Leave(player);
                }
            }
            else if (player.hasPermission("roh.admin"))
            {
                if (args.length==4 && args[0].equals("setspawn")){
                    //egyelőre ez gatya
                }
            }
            else{
                player.sendMessage(ColorTranslate.Translate(messages.getString("noPermission")));
            }
        }
        return true;
    }
}
