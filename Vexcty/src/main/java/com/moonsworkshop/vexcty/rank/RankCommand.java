package com.moonsworkshop.vexcty.rank;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.api.craftbukkit.VexctyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RankCommand implements CommandExecutor {

    private Vexcty main;

    public RankCommand(Vexcty main) {
        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof VexctyPlayer) {
            VexctyPlayer player = (VexctyPlayer) commandSender;

            if (player.isOp()) {
                if (args.length == 2) {
                    if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer tar = Bukkit.getOfflinePlayer(args[0]);

                        for (PlayerRank rank : PlayerRank.values()) {
                            if (rank.name().equalsIgnoreCase(args[1])) {
                                main.getRankManager().setRank(tar.getUniqueId(), rank);

                                if (tar == player) {
                                    player.sendMessage(ChatColor.GREEN + "You changed your rank to " + main.getRankManager().getRank(player.getUniqueId()).getDisplay() + ".");
                                    // You changed your rank to [OWNER].
                                } else {
                                    if (tar.isOnline()) {
                                        tar.getPlayer().sendMessage(ChatColor.GREEN + "Your rank has been changed to " + rank.getDisplay() + ChatColor.GREEN + "."); // TODO: Translate this
                                        // Your rank has been changed to [BUILDER].
                                    } else {
                                        player.sendMessage(ChatColor.RED + "You Changed " + tar.getName() + "'s rank to " + main.getRankManager().getRank(tar.getUniqueId()).getDisplay() + " while they were offline.");

                                        // You changed Wawamc02's rank to [ADMIN] While they were offline.
                                    }
                                }

                                return false;
                            }
                        }

                        player.sendMessage(ChatColor.RED + "You did not Specify a valid rank!");
                    } else {
                        player.sendMessage(ChatColor.RED + "This Player has never joined the server before!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please use /rank <player> <rank>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must be Oped to use that Command!");
            }
        }
        return false;
    }
}
