package com.hlnikniky.hle;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import com.hlnikniky.hle.blocks.BlockElevator;
import com.hlnikniky.hle.creativetab.CreativeTabHLE;
import com.hlnikniky.hle.handlers.ElevatorEventHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

    public static Block elevator;
    public static CreativeTabs tabHLE = new CreativeTabHLE("HLElevators");

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        HLElevators.LOG.info("===================");
        HLElevators.LOG.info("HLElevators version: " + Tags.VERSION);
        HLElevators.LOG.info("===================");
        // Убери старую строку и добавь эту:
        // FMLCommonHandler.instance().bus().register(new ElevatorEventHandler());
        elevator = new BlockElevator();
        GameRegistry.registerBlock(elevator, "elevator");

    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        // В CommonProxy.init()
        GameRegistry.addRecipe(
            new ItemStack(elevator),
            "IBI",
            "BEB",
            "IBI",
            'I',
            Items.iron_ingot,
            'B',
            Blocks.iron_block,
            'E',
            Items.ender_pearl);
    }

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        ElevatorEventHandler handler = new ElevatorEventHandler();
        MinecraftForge.EVENT_BUS.register(handler); // для LivingJumpEvent
        FMLCommonHandler.instance()
            .bus()
            .register(handler); // для PlayerTickEvent
    }

    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {}
}
