package com.moonsworkshop.vexcty.pm;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexgot.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    private Vexcty main;

    public MessageCommand(Vexcty main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }

                    if (target == player) {
                        player.sendMessage(ChatColor.RED + "You cannot send a message to yourself.");
                    } else {

                        target.sendMessage(ChatColor.GRAY + main.getRankManager().getRank(target.getUniqueId()).getName() + " " + target.getName() + CC.WHITE + " »» " + CC.GRAY + "You: " + CC.WHITE + builder);
                        // From [OWNER] epicprogamer975 »» You: hi
                        player.sendMessage(CC.GRAY + "You " + CC.WHITE + "»» " + main.getRankManager().getRank(target.getUniqueId()).getName() + " " + target.getName() + CC.WHITE +   ": " + builder.toString());
                        // You »» [ADMIN] 2bak: u ded

                        main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                    }

                } else {
                    player.sendMessage(ChatColor.RED + "This Player was not Found!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid usage! Please use /msg <player> <message>.");
            }
        }

        return false;
    }
}
