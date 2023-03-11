package com.moonsworkshop.vexgot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

class CUIGenericItem{

    private final Material material;
    private final CUIItem customItem;

    public CUIGenericItem(Material material){this(material, null);}
    public CUIGenericItem(CUIItem customItem){this(null, customItem);}

    private CUIGenericItem(Material material, CUIItem customItem){
        this.material = material;
        this.customItem = customItem;
    }

    /**
     * @return true iff the item exists either as a custom item or native Minecraft item
     */
    public boolean exists(){
        return this.material != null || this.customItem != null;
    }

    /**
     * @return the custom item represented by this object, or null
     */
    public CUIItem getCustomItem(){
        return this.customItem;
    }

    /**
     * @return the native Minecraft item represented by this object, or null
     */
    public Material getNativeItem(){
        return this.material;
    }

    /**
     * @return true iff the item is a custom item
     */
    public boolean isCustomItem(){
        return this.customItem != null;
    }

}
