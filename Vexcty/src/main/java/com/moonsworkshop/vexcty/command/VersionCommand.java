package com.moonsworkshop.vexcty.command;

import com.moonsworkshop.vexgot.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VersionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender.hasPermission("f2erg.vexcty.admin")) {
            commandSender.sendMessage(CC.GREEN + "This server is running VexctyHub");
            commandSender.sendMessage(CC.GREEN + "If this command works correctly then VexctyHub works properly.");
        } else {
            commandSender.sendMessage(CC.GREEN + "This server is running VexctyHub");
        }
        return false;
    }
}
