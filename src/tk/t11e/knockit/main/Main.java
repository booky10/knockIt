package tk.t11e.knockit.main;
// Created by booky10 in knockIt (18:04 14.01.20)

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tk.t11e.knockit.commands.KnockIt;
import tk.t11e.knockit.listener.*;

import java.util.Objects;

public class Main extends JavaPlugin {

    public static final String PREFIX = "§7[§bT11E§7]§c ", NO_PERMISSION = PREFIX + "You don't have " +
            "the permissions for this!";

    @Override
    public void onEnable() {
        long milliseconds = System.currentTimeMillis();

        saveDefaultConfig();
        initListener(Bukkit.getPluginManager());
        initCommands();

        milliseconds = System.currentTimeMillis() - milliseconds;
        System.out.println("[KnockIt] It took " + milliseconds + "ms to initialise this plugin!");
    }

    private void initCommands() {
        Objects.requireNonNull(getCommand("knockIt")).setExecutor(new KnockIt());
    }

    private void initListener(PluginManager pluginManager) {
        pluginManager.registerEvents(new InteractEvent(), this);
        pluginManager.registerEvents(new DamageEvent(), this);
        pluginManager.registerEvents(new MoveEvent(), this);
        pluginManager.registerEvents(new HungerEvent(), this);
        pluginManager.registerEvents(new GrapplingHook(), this);
        pluginManager.registerEvents(new InventoryListener(), this);
        pluginManager.registerEvents(new DoubleJumpEvent(), this);
        pluginManager.registerEvents(new JoinLeaveListener(), this);
    }
}