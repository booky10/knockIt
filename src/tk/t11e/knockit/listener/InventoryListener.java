package tk.t11e.knockit.listener;
// Created by booky10 in knockIt (18:48 15.01.20)

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tk.t11e.knockit.manager.KnockItManager;

public class InventoryListener implements Listener {

    @EventHandler
    public void onItemMove(InventoryClickEvent event) {
        if(!event.getWhoClicked().getType().equals(EntityType.PLAYER)) return;
        if(!event.getWhoClicked().getWorld().getName().equalsIgnoreCase(KnockItManager.getWorldName()))
            return;
        Player player = (Player) event.getWhoClicked();
        if(!player.getGameMode().equals(GameMode.ADVENTURE)) return;
        event.setCancelled(true);
    }
}