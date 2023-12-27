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
    private Dungeon dungeon;
    public DungeonCommand(Dungeon dungeon){
        this.config= ConfigLoader.Load("config");
        this.messages= ConfigLoader.Load("messages");
        this.dungeon=dungeon;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if (args.length==0){
                //   /dungeon
                if (player.hasPermission("roh.player")){
                    dungeon.GUI().Open(player);
                }
                else{
                    player.sendMessage(ColorTranslate.Translate(messages.getString("noPermission")));
                }
            }
            else if(args.length==1 && args[0].equals("leave")){
                if(player.hasPermission("roh.player")){
                    dungeon.Leave(player);
                }
                else{
                    player.sendMessage(ColorTranslate.Translate(messages.getString("noPermission")));
                }
            }


        }
        return true;
    }
}
