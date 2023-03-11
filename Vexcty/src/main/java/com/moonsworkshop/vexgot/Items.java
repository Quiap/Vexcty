package com.moonsworkshop.vexgot;

import lombok.experimental.UtilityClass;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import java.awt.*;

@UtilityClass
public class Items {

    public static final ItemBuilder TELEPORTER_ITEM = new ItemBuilder(Material.COMPASS).setDisplayName(CC.PRIMARY + "Teleporter").setLore("Teleport around the hub").build();

   public static final CUIItemMaker network = new CUIItemMaker("compass", CC.BLUE + "Network Menu", new ItemStack(Material.COMPASS)).setName(CC.BLUE + "Network Menu").setMaterial(Material.COMPASS);




}
