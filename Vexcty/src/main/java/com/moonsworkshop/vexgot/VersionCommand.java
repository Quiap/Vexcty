package com.moonsworkshop.vexgot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VersionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        commandSender.sendMessage("This server is a heavily modified version Spigot by the MoonsWorkshop Team");

        return false;
    }
}
