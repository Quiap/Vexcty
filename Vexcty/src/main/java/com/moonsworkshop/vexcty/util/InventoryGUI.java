package com.moonsworkshop.vexcty.util;

import org.bukkit.inventory.ItemStack;

public class InventoryGUI {
    public static boolean getClickedItem(ItemStack clicked, String message){
        return clicked.getItemMeta().getDisplayName().equalsIgnoreCase(message);
    }
}
