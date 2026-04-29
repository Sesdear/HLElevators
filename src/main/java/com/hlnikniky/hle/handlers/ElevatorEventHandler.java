package com.hlnikniky.hle.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

import com.hlnikniky.hle.helpers.ElevatorHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;

public class ElevatorEventHandler {

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.entity instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) event.entity;
        World world = player.worldObj;
        if (world.isRemote) return;

        int x = (int) Math.floor(player.posX);
        int y = (int) Math.floor(player.posY - 0.01D);
        int z = (int) Math.floor(player.posZ);
        if (y < 0) return;

        Block block = world.getBlock(x, y, z);
        if (block instanceof com.hlnikniky.hle.blocks.BlockElevator) {
            long currentTime = world.getTotalWorldTime();
            long cooldown = player.getEntityData()
                .getLong("elevatorCooldown");
            if (currentTime < cooldown) return;

            ElevatorHelper.teleportUp(world, player, x, y, z);
            player.getEntityData()
                .setLong("elevatorCooldown", currentTime + 8);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;
        if (event.side != Side.SERVER) return;

        EntityPlayer player = event.player;
        if (!player.isSneaking()) return;

        World world = player.worldObj;

        // Проверяем, стоит ли игрок вообще на земле (onGround)
        if (!player.onGround) return;

        int x = (int) Math.floor(player.posX);
        int y = (int) Math.floor(player.posY - 0.01D);
        int z = (int) Math.floor(player.posZ);
        if (y < 0) return;

        Block block = world.getBlock(x, y, z);
        if (block instanceof com.hlnikniky.hle.blocks.BlockElevator) {
            ElevatorHelper.teleportDown(world, player, x, y, z);
        }
    }
}
