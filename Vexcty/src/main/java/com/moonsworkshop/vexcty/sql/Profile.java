package com.moonsworkshop.vexcty.sql;

import com.moonsworkshop.vexcty.Vexcty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private Vexcty plugin = Vexcty.getInstance();

    private PlayerData data;
    private UUID UUID;
    private String playerName;

    public Profile(UUID uuid, String name) {
        this.UUID = uuid;
        this.playerName = name;
        this.data = new PlayerData(uuid, name);
    }
}
