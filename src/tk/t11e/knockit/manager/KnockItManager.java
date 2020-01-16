package tk.t11e.knockit.manager;
// Created by booky10 in knockIt (19:34 14.01.20)

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import tk.t11e.knockit.main.Main;
import tk.t11e.knockit.util.ItemBuilder;
import tk.t11e.knockit.util.PlayerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KnockItManager {

    private static final Main main = Main.getPlugin(Main.class);

    public static String getWorldName() {
        FileConfiguration config = main.getConfig();

        return config.getString("Locations.World");
    }

    public static void respawn(Player player) {
        player.getInventory().clear();
        for (ItemStack itemStack : getItems())
            player.getInventory().addItem(itemStack);

        player.teleport(getSpawn());
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(PlayerUtil.getMaxHealth(player));
        player.setFoodLevel(20);

        player.sendMessage(Main.PREFIX+"You died!");
    }

    public static Location getSpawn() {
        FileConfiguration config = main.getConfig();

        World world = Bukkit.getWorld(Objects.requireNonNull(config.getString("Locations.World")));
        double x = config.getDouble("Locations.Spawn.X") + 0.5;
        double y = config.getDouble("Locations.Spawn.Y") + 0.5;
        double z = config.getDouble("Locations.Spawn.Z") + 0.5;

        return new Location(world, x, y, z, 0, 0);
    }

    public static void setSpawn(Location location) {
        FileConfiguration config = main.getConfig();

        config.set("Locations.Spawn.X", location.getBlockX());
        config.set("Locations.Spawn.Y", location.getBlockY());
        config.set("Locations.Spawn.Z", location.getBlockZ());

        main.saveConfig();
    }

    public static void setLoc1(Location location) {
        FileConfiguration config = main.getConfig();

        config.set("Locations.Loc1.X", location.getBlockX());
        config.set("Locations.Loc1.Y", location.getBlockY());
        config.set("Locations.Loc1.Z", location.getBlockZ());

        main.saveConfig();
    }

    public static void setLoc2(Location location) {
        FileConfiguration config = main.getConfig();

        config.set("Locations.Loc2.X", location.getBlockX());
        config.set("Locations.Loc2.Y", location.getBlockY());
        config.set("Locations.Loc2.Z", location.getBlockZ());

        main.saveConfig();
    }

    public static void setDeathHeight(Location location) {
        FileConfiguration config = main.getConfig();

        config.set("DeathHeight", location.getBlockY());

        main.saveConfig();
    }

    public static Integer getDeathHeight() {
        FileConfiguration config = main.getConfig();

        return config.getInt("DeathHeight");
    }

    public static List<ItemStack> getItems() {
        ArrayList<ItemStack> items = new ArrayList<>();

        items.add(new ItemBuilder(Material.STICK).setName("§bKnockback Stick")
                .addEnchantment(Enchantment.KNOCKBACK, 3).setUnbreakable(true)
                .addItemFlags(ItemFlag.values()).build());
        items.add(new ItemBuilder(Material.FISHING_ROD).setName("§7Grappling Hook")
                .addItemFlags(ItemFlag.values()).setUnbreakable(true).build());

        return items;
    }

    public static boolean isLocationInSpawn(Location location) {
        FileConfiguration config = main.getConfig();

        double X1 = config.getDouble("Locations.Loc1.X");
        double Y1 = config.getDouble("Locations.Loc1.Y");
        double Z1 = config.getDouble("Locations.Loc1.Z");

        double X2 = config.getDouble("Locations.Loc2.X");
        double Y2 = config.getDouble("Locations.Loc2.Y");
        double Z2 = config.getDouble("Locations.Loc2.Z");

        return !(location.getBlockX() < X1) && !(location.getBlockX() > X2) &&
                !(location.getBlockZ() < Z1) && !(location.getBlockZ() > Z2) &&
                !(location.getBlockY() < Y1) && !(location.getBlockY() > Y2);
    }
}