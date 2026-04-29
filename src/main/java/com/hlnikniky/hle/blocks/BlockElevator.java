package com.hlnikniky.hle.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockElevator extends Block {

    public BlockElevator() {
        super(Material.iron);
        setBlockName("elevator");
        setBlockTextureName("hle:elevator");
        setHardness(2.0F);
        setResistance(10.0F);
        // Без setBlockBounds – блок полноразмерный визуально и физически
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

    // Никаких onEntityCollidedWithBlock
}
