package com.moonsworkshop.vexcty.rank;

import com.moonsworkshop.vexcty.Vexcty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    private Vexcty vexcty;

    public NametagManager(Vexcty vexcty) { this.vexcty = vexcty; }

    public void setNametags(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for (PlayerRank rank : PlayerRank.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.name());
            team.setPrefix(rank.getName() + " ");
        }
        for (Player tar : Bukkit.getOnlinePlayers()) {

            if (player.getUniqueId() != tar.getUniqueId()) {
                player.getScoreboard().getTeam(vexcty.getRankManager().getRank(tar.getUniqueId()).name()).addEntry(tar.getName());
            }
        }
    }

    public void newTag(Player player) {
        PlayerRank rank = vexcty.getRankManager().getRank(player.getUniqueId());
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
