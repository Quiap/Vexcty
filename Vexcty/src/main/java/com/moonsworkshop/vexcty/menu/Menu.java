package com.moonsworkshop.vexcty.menu;

import com.moonsworkshop.vexcty.util.inventory.InventoryUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Menu {

    public void openNetworkGUI() {
        Inventory network = Bukkit.createInventory(null, 27, "Server Menu");

        Material bb = Material.IRON_PICKAXE;

        bb.name().equals("Block Breaking");

        network.setItem(11, new ItemStack(bb));
    }

}
