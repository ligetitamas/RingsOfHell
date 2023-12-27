package me.ltommi.ringsofhell.DungeonStructure;

import me.ltommi.ringsofhell.Main;
import me.ltommi.ringsofhell.eventListeners.commandPreProcessEvent;
import me.ltommi.ringsofhell.eventListeners.inventoryClickEvent;
import me.ltommi.ringsofhell.utils.ColorTranslate;
import me.ltommi.ringsofhell.utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dungeon {
    private String state;
    private ConfigurationSection config;
    private ConfigurationSection messages;
    private GUI dungeonGUI;
    private ArrayList<Player> playerList;
    private BukkitTask task;
    private int count;
    public Dungeon(){
        this.state="idle";
        this.config= ConfigLoader.Load("config");
        this.messages= ConfigLoader.Load("messages");
        this.dungeonGUI= new GUI(this);
        this.playerList= new ArrayList<>();
        this.dungeonGUI=new GUI(this);
        Bukkit.getServer().getPluginManager().registerEvents(new commandPreProcessEvent(this,messages), Bukkit.getPluginManager().getPlugin("RingsOfHell"));
    }
    public void Join(Player player){
        if(!state.equals("running") && playerList.size()<config.getInt("maxPlayers")){
            if(!playerList.contains(player)){
                playerList.add(player);
                player.sendMessage(ColorTranslate.Translate(messages.getString("joinSuccess")));
                if (state.equals("idle")){
                    CountDown();
                }
            }
            else{
                player.sendMessage(ColorTranslate.Translate(messages.getString("joinAlready")));
            }
        }
        else{
            player.sendMessage(ColorTranslate.Translate(messages.getString("joinFailed")));
        }
    }
    public  void Leave(Player player){
        if (playerList.contains(player)){
            if (!state.equals("running")){
                playerList.remove(player);
                player.sendMessage(ColorTranslate.Translate(messages.getString("leaveSuccess")));
                if (playerList.size()==0){
                    task.cancel();
                    state="idle";
                }
            }
            else{
                player.sendMessage(ColorTranslate.Translate(messages.getString("leaveFailed")));
            }
        }
        else{
            player.sendMessage(ColorTranslate.Translate(messages.getString("leaveAlready")));
        }


    }
    private void CountDown(){
        state="countdown";
            count = config.getInt("countDownTime");
            task = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("RingsOfHell"), ()-> {
                if (count>5 && count%10==0){
                    for (Player player : playerList) {
                        player.sendMessage(count + "");
                    }
                }
                else if(count<6 && count>0){
                    for (Player player : playerList) {
                        player.sendMessage(count + "");
                    }
                }
                if(count == 0){
                    for (Player player : playerList) {
                        player.sendMessage("start");
                    }
                    task.cancel();
                }
                count--;
        }, 20, 20);
    }
    private void Start(){

    }



    public GUI GUI(){
        return dungeonGUI;
    }
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
}
