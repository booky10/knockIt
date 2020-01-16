package tk.t11e.knockit.listener;
// Created by booky10 in knockIt (19:12 15.01.20)

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import tk.t11e.knockit.main.Main;
import tk.t11e.knockit.manager.KnockItManager;

public class DoubleJumpEvent implements Listener {

    @EventHandler
    public void onEvent(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (!world.getName().equalsIgnoreCase(KnockItManager.getWorldName())) return;
        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        event.setCancelled(true);
        player.setAllowFlight(false);

        double multiplier = 1.0;
        Vector velocity = new Vector(0.0, multiplier, 0.0);
        Vector vector = player.getLocation().getDirection().multiply(multiplier).add(velocity);
        player.setVelocity(vector);

        world.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 0.125f, 0.5f);

        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class),
                () -> player.setAllowFlight(true), 30);
    }
}