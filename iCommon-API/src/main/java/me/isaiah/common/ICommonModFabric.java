package me.isaiah.common;

import me.isaiah.common.fabric.FabricServer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ICommonModFabric extends ICommonMod {

    @Override
	public void onInitialize() {
	    super.onInitialize();
	    ICommonMod.setLoader(Loader.FABRIC);
	    
	    ServerLifecycleEvents.SERVER_STARTING.register(server -> {
	    	LOGGER.info("Setting IServer instance..");
	        ICommonMod.set(new FabricServer(server));
	     // icommon$LOGGER.info("Setting IServer instance..");
	    });
	}

}