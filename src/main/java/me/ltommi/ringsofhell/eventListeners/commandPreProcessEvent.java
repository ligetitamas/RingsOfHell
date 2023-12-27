package me.ltommi.ringsofhell.eventListeners;

import me.ltommi.ringsofhell.DungeonStructure.Dungeon;
import me.ltommi.ringsofhell.utils.ColorTranslate;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class commandPreProcessEvent implements Listener {
    private Dungeon dungeon;
    private ConfigurationSection messages;
    public commandPreProcessEvent(Dungeon dungeon, ConfigurationSection messages){
        this.dungeon=dungeon;
        this.messages=messages;
    }
    @EventHandler
    public void onPlayerJoin(PlayerCommandPreprocessEvent event) {
        if (dungeon.getPlayerList().contains(event.getPlayer())){
            Player player = event.getPlayer();
            if(!event.getMessage().equals("/dungeon leave")){
                event.setCancelled(true);
                player.sendMessage(ColorTranslate.Translate(messages.getString("commandDeny")));
            }
        }
    }
}
