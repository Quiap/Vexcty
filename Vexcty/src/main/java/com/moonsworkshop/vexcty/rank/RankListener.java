package com.moonsworkshop.vexcty.rank;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class RankListener implements Listener {

    private Vexcty main;

    public RankListener(Vexcty main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        main.getNametagManager().setNametags(player);
        main.getNametagManager().newTag(player);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        e.setCancelled(true);

        Player player = e.getPlayer();

        e.getMessage().replace("<" + player.getName() + ">", "main.getRankManager().getRank(player.getUniqueId()).getDisplay() + \" \" + player.getName() + \": \" + ChatColor.WHITE + e.getMessage()");

        Bukkit.broadcastMessage(main.getRankManager().getRank(player.getUniqueId()).getDisplay() + " " + player.getName() + ": " + ChatColor.WHITE + e.getMessage());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        main.getNametagManager().removeTag(e.getPlayer());
    }

}
