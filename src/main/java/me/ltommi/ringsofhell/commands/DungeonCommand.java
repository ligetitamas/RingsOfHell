package me.ltommi.ringsofhell.commands;

import me.ltommi.ringsofhell.DungeonStructure.Dungeon;
import me.ltommi.ringsofhell.utils.ColorTranslate;
import me.ltommi.ringsofhell.utils.ConfigLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class DungeonCommand implements CommandExecutor {
    private ConfigurationSection config;
    private ConfigurationSection messages;
    private ConfigurationSection dungeonData;
    private Dungeon dungeon;
    public DungeonCommand(Dungeon dungeon){
        this.config= ConfigLoader.Load("config");
        this.messages= ConfigLoader.Load("messages");
        this.dungeonData= ConfigLoader.Load("dungeonData");
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

            }
            else{
                player.sendMessage(ColorTranslate.Translate(messages.getString("noPermission")));
            }
        }
        return true;
    }
}
