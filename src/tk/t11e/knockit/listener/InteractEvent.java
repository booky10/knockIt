package tk.t11e.knockit.listener;
// Created by booky10 in knockIt (18:59 14.01.20)

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if(!Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.POTTED_CORNFLOWER)) return;
        if(!world.getName().equalsIgnoreCase("knockIt")) return;
        if(!player.getGameMode().equals(GameMode.ADVENTURE)) return;

        event.setCancelled(true);
    }
}