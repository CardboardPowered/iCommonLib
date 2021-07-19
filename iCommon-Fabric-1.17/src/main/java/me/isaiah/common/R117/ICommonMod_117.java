package me.isaiah.common.R117;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.Loader;

public class ICommonMod_117 extends ICommonMod {

    @Override
	public void onInitialize() {
	    super.onInitialize();
	    ICommonMod.setLoader(Loader.FABRIC);
	}

}
