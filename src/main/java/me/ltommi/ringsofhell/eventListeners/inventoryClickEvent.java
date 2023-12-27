package me.ltommi.ringsofhell.eventListeners;

import me.ltommi.ringsofhell.DungeonStructure.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryClickEvent implements Listener {
    private GUI dungeonGUI;
    public inventoryClickEvent(GUI dungeonGUI){
       this.dungeonGUI=dungeonGUI;
    }
    @EventHandler
    public void onPlayerJoin(InventoryClickEvent event) {
        if (event.getClickedInventory()==dungeonGUI.getGUI()){
            event.setCancelled(true);
            if (event.getSlot()==14){
                dungeonGUI.getDungeon().Join((Player)event.getWhoClicked());
            }
        }
    }
}
