package me.isaiah.common.R116;

import me.isaiah.common.ICommonMod;
import me.isaiah.common.Loader;

public class ICommonMod_116 extends ICommonMod {

    @Override
    public void onInitialize() {
        super.onInitialize();
        ICommonMod.setLoader(Loader.FABRIC);
    }

}