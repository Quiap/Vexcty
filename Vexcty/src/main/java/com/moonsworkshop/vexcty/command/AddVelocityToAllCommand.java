package com.moonsworkshop.vexcty.command;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexgot.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class AddVelocityToAllCommand implements CommandExecutor {

    private Vexcty vexcty;

    public AddVelocityToAllCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("f2erg.vexcty.admin")) {
                if (args.length == 0) {
                    for (Player target : Bukkit.getOnlinePlayers()) { // get all players
                        if (args[0].equals(0)) {
                            target.setVelocity(new Vector(0, 1, 0));
                        } else if (args[0].equals(2)) {
                            target.setVelocity(new Vector(0, 2, 0));
                        } else if (args[0].equals(3)) {
                            target.setVelocity(new Vector(0, 3, 0));
                        } else if (args[0].equals(4)) {
                            target.setVelocity(new Vector(0, 4, 0));
                        } else if (args[0].equals(5)) {
                            target.setVelocity(new Vector(0, 5, 0));
                        } else if (args[0].equals(6)) {
                            target.setVelocity(new Vector(0, 6, 0));
                        } else if (args[0].equals(7)) {
                            target.setVelocity(new Vector(0, 7, 0));
                        } else if (args[0].equals(8)) {
                            target.setVelocity(new Vector(0, 8, 0));
                        } else if (args[0].equals(9)) {
                            target.setVelocity(new Vector(0, 9, 0));
                        } else if (args[0].equals(10)) {
                            target.setVelocity(new Vector(0, 10, 0));
                        }
                    }

                    player.sendMessage(CC.GREEN + "Congrats, no but actually why tho?");
                } else {
                    player.sendMessage(CC.RED + "Invalid Usage! Please use /addvelocitytoall <power>");
                }


            } else {
               player.sendMessage("You do not have permission to use this command");
            }
        }

        return false;
    }
}
