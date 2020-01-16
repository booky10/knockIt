package tk.t11e.knockit.listener;
// Created by booky10 in knockIt (19:05 14.01.20)

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import tk.t11e.knockit.manager.KnockItManager;

public class DamageEvent implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!event.getEntityType().equals(EntityType.PLAYER)) return;
        Player player = (Player) event.getEntity();
        World world = player.getWorld();
        if (!world.getName().equalsIgnoreCase("knockIt")) return;

        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL))
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntityType().equals(EntityType.PLAYER)) return;
        if (!event.getDamager().getType().equals(EntityType.PLAYER)) return;
        Player player = (Player) event.getEntity();
        Player killer = (Player) event.getDamager();
        World world = player.getWorld();
        if (!world.getName().equalsIgnoreCase("knockIt")) return;

        if(!killer.getGameMode().equals(GameMode.CREATIVE))
            event.setDamage(0);

        if (event.getFinalDamage() > player.getHealth())
            KnockItManager.respawn(player);
    }
}