package me.isaiah.common.mixin;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.mojang.bridge.game.GameVersion;

import me.isaiah.common.cmixin.SupportedVersion;
import net.minecraft.SharedConstants;

public class ICommonMixinPlugin implements IMixinConfigPlugin {

    private static final String MIXIN_PACKAGE_ROOT = "me.isaiah.common.mixin.";
    private final Logger logger = LogManager.getLogger("iCommon");

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    private boolean start = false;

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!start) {
            GameVersion ver = SharedConstants.getGameVersion();
            logger.info("============================================================================");
            logger.info(" ");
            logger.info("  iCommon - Common Library for Fabric Mods by Isaiah.");
            logger.info("  Copyright (c) 2017-2021. All Rights Reserved.");
            logger.info("  Parts inspired from Minecarts by Bayside308 & Isaiah");
            logger.info(" ");
            logger.info("====================================================================" + ver.getReleaseTarget().substring(2) + "/" + ver.getProtocolVersion());
            logger.info("Loading...");
            wait(200);
        }
        start = true;

        try {
            SupportedVersion sv = Class.forName(mixinClassName).getAnnotation(SupportedVersion.class);
            String mixin = mixinClassName.substring(MIXIN_PACKAGE_ROOT.length());
            if (null != sv) {
                String s = SharedConstants.getGameVersion().getReleaseTarget();
                for (String val : sv.value()) {
                    if (val.equalsIgnoreCase(s)) {
                        logger.info("Applying versioned " + val + " mixin " + mixin + "...");
                        return true;
                    }
                }
            } else {
                logger.info("Applying mixin: " + mixin + "...");
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void wait(int ms) {
        try { Thread.sleep(ms*10); } catch (InterruptedException e) {}
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode target, String mixinClassName, IMixinInfo info) {
    }

}