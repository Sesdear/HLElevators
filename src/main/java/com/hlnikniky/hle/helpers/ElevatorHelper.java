package com.hlnikniky.hle.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.hlnikniky.hle.Config;

public class ElevatorHelper {

    private static final int RANGE = Config.elevatorRange;

    public static void teleportUp(World world, EntityPlayer player, int x, int y, int z) {
        if (world.isRemote) return;

        for (int i = y + 2; i < y + RANGE; i++) { // начинаем с y+2, чтобы не телепортировать на тот же блок
            if (isElevator(world, x, i, z)) {
                if (isSpaceClear(world, x, i + 1, z)) {
                    teleport(player, x, i + 1, z);
                }
                return;
            }
        }
    }

    public static void teleportDown(World world, EntityPlayer player, int x, int y, int z) {
        if (world.isRemote) return;

        for (int i = y - 1; i > y - RANGE; i--) {
            if (i < 0) break;
            if (isElevator(world, x, i, z)) {
                if (isSpaceClear(world, x, i + 1, z)) {
                    teleport(player, x, i + 1, z);
                }
                return;
            }
        }
    }

    private static boolean isElevator(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return block instanceof com.hlnikniky.hle.blocks.BlockElevator;
    }

    private static boolean isSpaceClear(World world, int x, int y, int z) {
        // Проверяем два блока над лифтом (ноги + голова)
        return world.isAirBlock(x, y, z) && world.isAirBlock(x, y + 1, z);
        // Можно сделать мягче: !world.getBlock(x,y,z).isOpaqueCube() и т.д.
    }

    private static void teleport(EntityPlayer player, int x, int y, int z) {
        player.setPositionAndUpdate(x + 0.5D, y + 0.01D, z + 0.5D); // +0.01 чтобы не влипнуть в блок

        // Сбрасываем скорость
        player.motionX = 0.0D;
        player.motionY = 0.0D;
        player.motionZ = 0.0D;

        // Небольшой cooldown через NBT (простой вариант)
        player.getEntityData()
            .setLong("elevatorCooldown", player.worldObj.getTotalWorldTime() + 10);
    }
}
