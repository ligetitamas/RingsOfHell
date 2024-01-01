package me.ltommi.ringsofhell.DungeonStructure;

import me.ltommi.ringsofhell.eventListeners.commandPreProcessEvent;
import me.ltommi.ringsofhell.utils.ColorTranslate;
import me.ltommi.ringsofhell.utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Dungeon {
    private String state;
    private ConfigurationSection config;
    private ConfigurationSection messages;
    private GUI dungeonGUI;
    private ArrayList<Player> playerList;
    private ArrayList<Level> levels;
    private int currentLevel=1;
    private BukkitTask task;
    private int countDownTime;
    private Location spawnLocation;
    private Location leaveLocation;
    public Dungeon(){
        this.state="idle";
        this.config= ConfigLoader.Load("config");
        this.messages= ConfigLoader.Load("messages");
        this.dungeonGUI= new GUI(this);
        this.playerList= new ArrayList<>();
        this.dungeonGUI=new GUI(this);
        this.levels= new ArrayList<>();

        Bukkit.getServer().getPluginManager().registerEvents(new commandPreProcessEvent(this,messages), Bukkit.getPluginManager().getPlugin("RingsOfHell"));
    }
    private void DungeonSetup(){
        ConfigurationSection dungeonData=ConfigLoader.Load("dungeonData");
        for (int i=1 ; i<= dungeonData.getKeys(false).size() ; i++){
            Level newLevel=new Level(dungeonData.getConfigurationSection("level_"+i),playerList);
            levels.add(newLevel);
        }
    }
    public void Join(Player player){
        if(!state.equals("running") && playerList.size()<config.getInt("maxPlayers")){
            if(!playerList.contains(player)){
                playerList.add(player);
                player.teleport(spawnLocation);
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
                player.teleport(leaveLocation);
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
            countDownTime = config.getInt("countDownTime");
            task = Bukkit.getScheduler().runTaskTimer(Bukkit.getPluginManager().getPlugin("RingsOfHell"), ()-> {
                if (countDownTime >5 && countDownTime %10==0){
                    for (Player player : playerList) {
                        player.sendMessage(countDownTime + "");
                    }
                }
                else if(countDownTime <6 && countDownTime >0){
                    for (Player player : playerList) {
                        player.sendMessage(countDownTime + "");
                    }
                }
                if(countDownTime == 0){
                    for (Player player : playerList) {
                        player.sendMessage("start");
                    }
                    task.cancel();
                }
                countDownTime--;
        }, 20, 20);
    }
    private void Start(){
        state= "running";
        levels.get(currentLevel).Run();
    }



    public GUI GUI(){
        return dungeonGUI;
    }
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }
}
