package com.moonsworkshop.vexcty.command;

import com.moonsworkshop.vexgot.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VersionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            commandSender.sendMessage(CC.GREEN + "This server is running VexctyHub Build: 0.0#12");
        return false;
    }
}
