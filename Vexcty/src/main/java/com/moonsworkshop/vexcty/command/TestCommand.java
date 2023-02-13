package com.moonsworkshop.vexcty.command;

import com.moonsworkshop.vexcty.api.craftbukkit.VexctyPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof VexctyPlayer) {
            VexctyPlayer player = (VexctyPlayer) sender;
                    if (player.isStaff(true)) {
                        player.sendMessage("IsStaff = true");
                    } else {
                        player.sendMessage("issStaff = fasle");
                    }
        }
        return false;
    }
}
