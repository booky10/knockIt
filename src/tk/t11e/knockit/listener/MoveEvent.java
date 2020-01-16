package tk.t11e.knockit.listener;
// Created by booky10 in knockIt (19:15 14.01.20)

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import tk.t11e.knockit.manager.KnockItManager;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (!world.getName().equalsIgnoreCase("knockIt")) return;
        if (!player.getGameMode().equals(GameMode.ADVENTURE)) return;

        if (KnockItManager.isLocationInSpawn(event.getTo()))
            player.setInvulnerable(true);
        else {
            player.setInvulnerable(false);

            if (event.getTo().getBlockY() <= KnockItManager.getDeathHeight())
                KnockItManager.respawn(player);
        }
    }
}