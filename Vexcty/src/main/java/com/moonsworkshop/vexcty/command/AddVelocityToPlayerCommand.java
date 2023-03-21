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

public class AddVelocityToPlayerCommand implements CommandExecutor {

    private Vexcty vexcty;

    public AddVelocityToPlayerCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("f2erg.vexcty.admin")) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (!(target == null)) { // check if the player is online
                        player.sendMessage(CC.RED + "That player is not online.");
                    } else {

                        if (args[1].equals(1)) {
                            target.setVelocity(new Vector(0, 1, 0));
                        } else if (args[1].equals(2)) {
                            target.setVelocity(new Vector(0, 2, 0));
                        } else if (args[1].equals(3)) {
                            target.setVelocity(new Vector(0, 3, 0));
                        } else if (args[1].equals(4)) {
                            target.setVelocity(new Vector(0, 4, 0));
                        } else if (args[1].equals(5)) {
                            target.setVelocity(new Vector(0, 5, 0));
                        } else if (args[1].equals(6)) {
                            target.setVelocity(new Vector(0, 6, 0));
                        } else if (args[1].equals(7)) {
                            target.setVelocity(new Vector(0, 7, 0));
                        } else if (args[1].equals(8)) {
                            target.setVelocity(new Vector(0, 8, 0));
                        } else if (args[1].equals(9)) {
                            target.setVelocity(new Vector(0, 9, 0));
                        } else if (args[1].equals(10)) {
                            target.setVelocity(new Vector(0, 10, 0));
                        }
                    }
                } else {
                    player.sendMessage(CC.RED + "Invalid Usage! Please use /addvelocity <player> <velocity>");
                }
            } else {
                player.sendMessage(CC.RED + "You do not have permission to use this command");
            }
        }

        return false;
    }
}
