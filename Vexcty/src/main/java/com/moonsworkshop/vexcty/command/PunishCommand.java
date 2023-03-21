package com.moonsworkshop.vexcty.command;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexgot.CC;
import com.moonsworkshop.vexgot.Time;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class PunishCommand implements CommandExecutor {

    public static int banID;

    private final Vexcty vexcty;

    public PunishCommand(Vexcty vexcty) {
        this.vexcty = vexcty;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
                if (player.hasPermission("f2erg.vexcty.admin")) {

                    if (args.length == 3) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            Player target = (Player) Bukkit.getOfflinePlayer(args[0]);

                            if (args[1].equalsIgnoreCase("-k")) {
                                switch (args[2].toLowerCase()) {
                                    case "sb" :
                                            target.kickPlayer(CC.RED + "You have been kicked for suspicious behaviour. \n" +
                                                    "Please contact a staff member if this repeatedly happens.");
                                        break;
                                    case "eg":
                                            target.kickPlayer(CC.RED + "You have been kicked for exploiting/glitching the network,\n" +
                                                    "This is your only warning.");
                                        break;
                                    default:
                                        player.sendMessage(CC.RED + "Invalid Usage! Please use /punish <player> -k \n" +
                                                "- sb (Suspicious behaviour) \n" +
                                                "- eg (Exploiting the network)");

                                }


                            } else if (args[1].equalsIgnoreCase("-b")) {
                                switch (args[2].toLowerCase()) { // get ban types
                                    case "in":
                                        banID = 1;
                                        Time.duration(TimeUnit.DAYS, 14);

                                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty. \n" + CC.GRAY +
                                                    "Reason: " + CC.WHITE + "Having an inappropriate name.", null, null);
                                            target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                        break;

                                    case "hac":
                                        banID = 2;
                                        Time.duration(TimeUnit.DAYS, 30);
                                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty for 30 days. \n" + CC.GRAY +
                                                    "Reason: " + CC.WHITE + "Hacking and giving other players and bots and unfair advantage", null, null);
                                            target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                        break;
                                    case "p":
                                        banID = 3;
                                        Time.duration(TimeUnit.DAYS, 90);
                                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty for 90 days.\n" + CC.GRAY +
                                                    "Reason: " + CC.WHITE + "Inappropriate language that can harm others.", null, null);
                                            target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                        break;
                                    case "tto":
                                        banID = 4;
                                        Time.duration(TimeUnit.DAYS, 90);
                                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty for 90 days. \n" + CC.GRAY +
                                                    "Reason: " + CC.WHITE + "Threats to other people on the network.", null, null);
                                            target.kickPlayer(CC.RED + "You have been banned from Vexcty.");
                                        break;
                                    case "en":
                                        banID = 5;
                                        Time.duration(TimeUnit.DAYS, 30);
                                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), CC.RED + "You have been banned from Vexcty.\n" + CC.GRAY +
                                                    "Reason: " + CC.WHITE + "Exploiting/Glitching the server.", null, null);
                                            target.kickPlayer(CC.RED + "You have been banned from Vexcty.");

                                        break;
                                    default:
                                        player.sendMessage(CC.RED + "Invalid Usage! Please use /ban <player> \n" +
                                                "- hac (Hacks and cheats) \n" +
                                                "- p (Profanity eg: YoU sHoUlD cOoMiT sUcIdE) \n" +
                                                "- tto (Threats to others eg: ddosing, doxing, harassing, blackmailing etc) \n" +
                                                "- en (Exploiting and glitching the server) \n" +
                                                "- in (Inappropriate Name)");
                                }
                            } else {
                                player.sendMessage(CC.RED + args[1] + " is not a valid argument, arguments are -k, -b or -tb");
                            }
                        }

                    } else {
                        player.sendMessage(CC.RED + "Invalid Usage! Please use /punish <player> -b|-k <REASON>");
                    }


                } else {
                        player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                }
        }

        return false;
    }
}
