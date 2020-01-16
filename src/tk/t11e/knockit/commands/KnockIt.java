package tk.t11e.knockit.commands;
// Created by booky10 in knockIt (20:34 14.01.20)

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import tk.t11e.knockit.main.Main;
import tk.t11e.knockit.manager.KnockItManager;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class KnockIt implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("knockIt"))
                if (args.length == 1)
                    if (args[0].equalsIgnoreCase("setDeathHeight")) {
                        KnockItManager.setDeathHeight(player.getLocation());
                        player.sendMessage(Main.PREFIX + "§aSuccessfully changed death height!");
                    } else if (args[0].equalsIgnoreCase("setSpawn")) {
                        KnockItManager.setSpawn(player.getLocation());
                        player.sendMessage(Main.PREFIX + "§aSuccessfully changed spawn!");
                    } else if (args[0].equalsIgnoreCase("setLoc1")) {
                        KnockItManager.setLoc1(player.getLocation());
                        player.sendMessage(Main.PREFIX + "§aSuccessfully changed loc1!");
                    } else if (args[0].equalsIgnoreCase("setLoc2")) {
                        KnockItManager.setLoc2(player.getLocation());
                        player.sendMessage(Main.PREFIX + "§aSuccessfully changed loc2!");
                    } else
                        return false;
                else
                    return false;
            else
                player.sendMessage(Main.NO_PERMISSION);
        } else
            sender.sendMessage("You must execute this command as a player!");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> list = new ArrayList<>();
            if (args.length == 1) {
                list.add("setDeathHeight");
                list.add("setSpawn");
                list.add("setLoc1");
                list.add("setLoc2");}
        return list;
    }
}