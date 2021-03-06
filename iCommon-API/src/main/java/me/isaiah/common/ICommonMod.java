package me.isaiah.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;

public class ICommonMod implements ModInitializer {

    private static final double API_VERSION = 0.1;

    public static final Logger LOGGER = LogManager.getLogger("iCommon");
    private static IServer iserver;
    static Loader modloader;

    /**
     * Set the instance of the {@link IServer}
     */
    public static void set(IServer impl) {
        iserver = impl;
    }

    /**
     * Set the value of the mod loader.
     */
    public static void setLoader(Loader load) {
        modloader = load;
    }

    /**
     */
    public double getAPIRevision() {
        return API_VERSION;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("iCommon Mod initialized.");
	}

    public static IServer getIServer() {
        return iserver;
    }

}
