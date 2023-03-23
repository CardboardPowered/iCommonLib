package me.isaiah.common;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.Loader;

public class ICommonModFabric extends ICommonMod {

    @Override
	public void onInitialize() {
	    super.onInitialize();
	    ICommonMod.setLoader(Loader.FABRIC);
	}

}