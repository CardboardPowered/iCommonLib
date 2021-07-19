package com.example.examplemod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.Loader;
import me.isaiah.common.R116.ICommonMod_116;

@Mod("icommon")
public class ExampleMod {

    private static final Logger LOGGER = LogManager.getLogger();

    public ExampleMod() {

        // This is our mod's event bus, used for things like registry or lifecycle events
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        // This listener is fired on both client and server during setup.
        MOD_BUS.addListener(this::commonSetup);

        // Most other events are fired on Forge's bus.
        // If we want to use annotations to register event listeners,
        // we need to register our object like this!
        MinecraftForge.EVENT_BUS.register(this);

        // For more information on how to deal with events in Forge,
        // like automatically subscribing an entire class to an event bus
        // or using static methods to listen to events,
        // feel free to check out the Forge wiki!


        ICommonMod_116 pl = new ICommonMod_116();
        pl.onInitialize();
        
        ICommonMod.setLoader(Loader.FORGE);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Hello from iCommon!");
        //LOGGER.info("Look, I found a {}!", Items.DIAMOND.getRegistryName());
    }

}