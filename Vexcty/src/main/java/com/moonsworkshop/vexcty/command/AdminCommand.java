package com.moonsworkshop.vexcty.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage(getWittyComment());
        return false;
    }

    private static String getWittyComment()
    {
        String[] astring = new String[] {
                "Try 49 times, maybe you will find something.", "nah i ain\'t gon give you admin", "Invalid Usage! Please use /admin <player> <rank> <level> <isOp> <hasPermission> <displayedInTab>"};
        try
        {
            return astring[(int)(System.nanoTime() % (long)astring.length)];
        }
        catch (Throwable var2)
        {
            return "";
        }
    }
}
