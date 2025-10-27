package me.isaiah.common;

public class ICommonModFabric extends ICommonMod {

    @Override
	public void onInitialize() {
	    super.onInitialize();
	    ICommonMod.setLoader(Loader.FABRIC);
	}

}