package com.moonsworkshop.vexcty.sql;

import com.moonsworkshop.vexcty.Vexcty;
import com.moonsworkshop.vexcty.rank.PlayerRank;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

@Getter
@Setter
public class PlayerData {

    private Vexcty plugin = Vexcty.getInstance();

    private java.util.UUID UUID;
    private String playerName;
    private PlayerRank rank = PlayerRank.MEMBER;

    public PlayerData(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
    }

    public void load(Player player) {
        Vexcty.getInstance().getMySQLManager().select("SELECT * FROM player WHERE uuid=?", resultSet -> {
            try {
                if(resultSet.next()) {
                    rank = (PlayerRank.valueOf(resultSet.getString("rank")));
                } else {
                    Vexcty.getInstance().getMySQLManager().execute("INSERT INTO player(uuid, name, rank) VALUES (?,?,?);", player.getUniqueId().toString(), player.getName(), PlayerRank.MEMBER.toString());
                }
            } catch(SQLException e) {
                Bukkit.getConsoleSender().sendMessage(e.getMessage());
            }
        }, player.getUniqueId().toString());


    }

    public void save(Player player) {
        Vexcty.getInstance().getMySQLManager().execute("UPDATE player SET rank=? WHERE uuid=?",
                rank.toString(), player.getUniqueId().toString());
    }

}
