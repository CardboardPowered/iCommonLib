package me.isaiah.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class ICommonMod implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("iCommon");
    private static IServer iserver;

    public static void set(IServer impl) { iserver = impl;}

    @Override
    public void onInitialize() {
        LOGGER.info("iCommon Mod initialized.");
	}

    public static IServer getIServer() {
        return iserver;
    }

}
