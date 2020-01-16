package tk.t11e.knockit.listener;
// Created by booky10 in knockIt (20:56 14.01.20)

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.Vector;
import tk.t11e.knockit.main.Main;
import tk.t11e.knockit.manager.KnockItManager;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class GrapplingHook implements Listener {

    public static final HashMap<UUID, Boolean> timeout = new HashMap<>();

    public GrapplingHook() {
        for (Player player : Bukkit.getOnlinePlayers())
            timeout.put(player.getUniqueId(), false);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if (!player.getWorld().getName().equalsIgnoreCase(KnockItManager.getWorldName()))
            return;
        if(timeout.get(player.getUniqueId())) return;

        if (event.getState().equals(PlayerFishEvent.State.REEL_IN)) {
            move(player, event.getHook().getLocation());
            timeout.put(player.getUniqueId(), true);

            Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class),
                    () -> timeout.put(player.getUniqueId(), false), 30);
        } else if (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY))
            move(Objects.requireNonNull(event.getCaught()), player.getLocation());
        else if (event.getState().equals(PlayerFishEvent.State.IN_GROUND)) {
            moveHigh(player, event.getHook().getLocation());
            timeout.put(player.getUniqueId(), true);

            Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class),
                    () -> timeout.put(player.getUniqueId(), false), 15);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        timeout.put(player.getUniqueId(), false);
        if (!player.getWorld().getName().equalsIgnoreCase(KnockItManager.getWorldName())) return;
        if (!player.getGameMode().equals(GameMode.ADVENTURE)) return;
        player.setAllowFlight(true);
    }

    public static void move(Entity entity, Location location) {
        Location location1 = entity.getLocation();
        location1.add(0.0d, 0.0d, 0.0d);

        double gravity = -0.008d;
        double distance = location.distance(location1);

        double vectorX = (1.0d + 0.07000000000000001d * distance) * (location.getX() - location1.getX()) / distance;
        double vectorY = (1.0d + 0.03d * distance) * (location.getY() - location1.getY()) / distance - 0.5d * gravity * distance;
        double vectorZ = (1.0d + 0.07000000000000001d * distance) * (location.getZ() - location1.getZ()) / distance;

        Vector vector = entity.getVelocity();
        vector.setX(vectorX);
        vector.setY(vectorY + 0.5);
        vector.setZ(vectorZ);

        entity.setVelocity(vector);
    }

    public static void moveHigh(Entity entity, Location location) {
        Location location1 = entity.getLocation();
        location1.add(0.0d, 0.0d, 0.0d);
        entity.teleport(location1);

        double gravity = -0.008d;
        double distance = location.distance(location1);

        double vectorX = (1.0d + 0.07000000000000001d * distance) * (location.getX() - location1.getX()) / distance;
        double vectorY = (1.0d + 0.03d * distance) * (location.getY() - location1.getY()) / distance - 0.5d * gravity * distance;
        double vectorZ = (1.0d + 0.07000000000000001d * distance) * (location.getZ() - location1.getZ()) / distance;

        Vector vector = entity.getVelocity();
        vector.setX(vectorX);
        vector.setY(vectorY + 1);
        vector.setZ(vectorZ);

        entity.setVelocity(vector);
    }
}