package com.moonsworkshop.vexcty.command;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexgot.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    Vexcty vexcty;

    public VanishCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    public static boolean isVanished;

    private ArrayList<Player> vanished = new ArrayList<Player>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("f2erg.vexcty.admin")) {
                if (isVanished) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.hidePlayer(player);
                    }
                    player.setPlayerListName("");
                    vanished.add(player);
                    player.sendMessage(CC.GREEN + "You have been vanished!");
                } else {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        target.showPlayer(player);
                    }
                    player.setPlayerListName(vexcty.getRankManager().getRank(player.getUniqueId()).getName() + " " + player.getName());
                    vanished.remove(player);
                    player.sendMessage(CC.GREEN + "You have un-vanished!");
                }
                isVanished =!isVanished;
            } else {
                player.sendMessage(CC.RED + "You do not have permission to use this command!");
            }
        }
        return false;
    }
}
