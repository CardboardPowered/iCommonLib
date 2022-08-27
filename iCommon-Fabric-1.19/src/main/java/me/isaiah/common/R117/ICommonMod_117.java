package me.isaiah.common.R117;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.Loader;
import me.isaiah.common.event.EventHandler;
import me.isaiah.common.event.entity.CampfireBlockEntityCookEvent;

public class ICommonMod_117 extends ICommonMod {

    @Override
	public void onInitialize() {
	    super.onInitialize();
	    ICommonMod.setLoader(Loader.FABRIC);

	    // EventRegistery.registerAll(this);
	}

    @EventHandler
    public void testEvent(CampfireBlockEntityCookEvent event) {
        System.out.println("Debug: CampfireBlockEntityCookEvent fired!");
    }

}
