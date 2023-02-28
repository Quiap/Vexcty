package com.moonsworkshop.vexcty.rank;

import com.moonsworkshop.vexgot.CC;
import lombok.Getter;

public enum PlayerRank {

    OWNER(CC.DARK_RED + "[OWNER]", 13, true),
    CO_OWNER(CC.DARK_RED + "[CO-OWNER]", 12, true),
    MANAGER(CC.YELLOW + "[MANAGER]", 11, true),
    ADMIN(CC.RED + "[ADMIN]", 10, true),
    MOD(CC.DARK_GREEN + "[MODERATOR]", 9, true),
    DEV(CC.BLACK + "[DEVELOPER]", 8, true),
    YOUTUBE(CC.RED + "[YOUTUBE]", 7 ,false),
    TWITCH(CC.L_PURPLE + "[TWITCH]", 6, false),
    MOJANG(CC.GOLD + "[MOJANG]", 5, false),
    LEGEND(CC.BLACK + "[LEGEND]", 4, false),
    DONOR(CC.AQUA + "[DONOR]", 3, false),
    VOTER("⚔︎", 2, false),
    MEMBER(CC.GRAY + "", 1, false);


    @Getter
    private String name;
    @Getter
    private int level;
    @Getter
    private boolean isStaffRank;

    PlayerRank(String name, int level, boolean isStaffRank) {
        this.name = name;
        this.level = level;
        this.isStaffRank = isStaffRank;
    }



}
