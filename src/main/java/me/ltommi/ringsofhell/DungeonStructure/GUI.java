package me.ltommi.ringsofhell.DungeonStructure;

import me.ltommi.ringsofhell.eventListeners.inventoryClickEvent;
import me.ltommi.ringsofhell.utils.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUI {
    private ConfigurationSection config;
    private ConfigurationSection messages;
    private Inventory inv;
    private Dungeon dungeon;

    public GUI(Dungeon dungeon){
        this.config= ConfigLoader.Load("config");
        this.messages= ConfigLoader.Load("messages");
        this.inv= Bukkit.createInventory(null,27,config.getString("gui.title"));
        GUISetup();
        this.dungeon=dungeon;
        Bukkit.getServer().getPluginManager().registerEvents(new inventoryClickEvent(this), Bukkit.getPluginManager().getPlugin("RingsOfHell"));
    }
    private void GUISetup(){
        ItemStack dungeonButton = new ItemStack(Material.IRON_SWORD);
        //NBT.modify(dungeonButton, nbt ->{
        //    nbt.setString("buttonType","join");
        //});
        inv.setItem(14,dungeonButton);
    }
    public void Open(Player player){
        player.openInventory(inv);
    }


    public Inventory getGUI(){
        return inv;
    }
    public Dungeon getDungeon(){
        return dungeon;
    }
}
