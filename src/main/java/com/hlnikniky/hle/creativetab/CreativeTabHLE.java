package com.hlnikniky.hle.creativetab;

import com.hlnikniky.hle.blocks.BlockElevator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabHLE extends CreativeTabs {

    public CreativeTabHLE(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(com.hlnikniky.hle.CommonProxy.elevator);
    }


}
