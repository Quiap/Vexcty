package com.moonsworkshop.vexcty.rank;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.afk.AFKManager;
import com.moonsworkshop.vexgot.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    private Vexcty vexcty;

    AFKManager afkManager;

    Player player = null;

    PlayerRank getRank = vexcty.getRankManager().getRank(player.getUniqueId());

    public NametagManager(Vexcty vexcty) { this.vexcty = vexcty; }

    public void setNametags(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for (PlayerRank rank : PlayerRank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            if (rank == PlayerRank.MEMBER) {
                team.setPrefix(CC.GRAY + "");
            } else {
                team.setPrefix(rank.getName().toString() + " ");
            }
        }
        for (Player tar : Bukkit.getOnlinePlayers()) {

            if (player.getUniqueId() != tar.getUniqueId()) {
                player.getScoreboard().getTeam(vexcty.getRankManager().getRank(tar.getUniqueId()).name()).addEntry(tar.getName());
            }
        }
    }

    public void newTag(Player player) {
        PlayerRank rank = getRank;
        for (Player tar : Bukkit.getOnlinePlayers()) {
            tar.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
        }
    }

    public void removeTag(Player player) {
        for (Player tar : Bukkit.getOnlinePlayers()) {
            tar.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

}