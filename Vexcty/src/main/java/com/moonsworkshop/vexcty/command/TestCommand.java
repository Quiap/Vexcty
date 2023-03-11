package com.moonsworkshop.vexcty.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.moonsworkshop.vexcty.rank.*;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (PlayerRank.ADMIN.isStaffRank()) {
                player.sendMessage("You are staff");
            } else {
                player.sendMessage("You are not staff");
            }
        }

        return false;
    }
}
