package tk.t11e.knockit.listener;
// Created by booky10 in knockIt (19:30 14.01.20)

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerEvent implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER))return;
        Player player = (Player) event.getEntity();
        World world = player.getWorld();
        if(!world.getName().equalsIgnoreCase("knockIt"))return;

        event.setCancelled(true);
    }
}