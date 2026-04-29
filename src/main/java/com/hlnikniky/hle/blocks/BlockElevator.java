package com.hlnikniky.hle.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.hlnikniky.hle.CommonProxy;

public class BlockElevator extends Block {

    private IIcon iconTop;
    private IIcon iconBottom;
    private IIcon iconSide;

    public BlockElevator() {
        super(Material.iron);
        setBlockName("elevator");
        setHardness(2.0F);
        setResistance(10.0F);

        setCreativeTab(CommonProxy.tabHLE);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        iconTop = reg.registerIcon("hle:elevator_top");
        iconBottom = reg.registerIcon("hle:elevator_bottom");
        iconSide = reg.registerIcon("hle:elevator_side");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 0:
                return iconBottom;
            case 1:
                return iconTop;
            default:
                return iconSide;
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }
}
