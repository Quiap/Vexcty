package com.moonsworkshop.vexgot;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;

@UtilityClass
public class Items {

    public static final ItemBuilder SERVER_SELECTOR_ITEM = new ItemBuilder(Material.COMPASS).setDisplayName(CC.PRIMARY + "Server Selector").build();


}
