package me.isaiah.common.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.mojang.bridge.game.GameVersion;

import me.isaiah.common.cmixin.MixinList;
import net.minecraft.MinecraftVersion;
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
    
    public static GameVersion getGameVersion() {
        try { 
            return SharedConstants.getGameVersion();  // 1.16
        } catch (IllegalStateException ver117) {
            return MinecraftVersion.create();         // 1.17+
        }
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!start) {
            GameVersion ver = getGameVersion();
            logger.info(" iCommon - Isaiah's common library for mods - Copyright (c) 2018-2021.");
            logger.info(" Running on MC " + ver.getReleaseTarget() + " (" + ver.getProtocolVersion() + ")");
            logger.info("=======================================================");
        }
        start = true;

        return shouldApply(mixinClassName, "1");
    }
    
    public boolean shouldApply(String mixinClassName, String output) {
        String mixin = mixinClassName.substring(MIXIN_PACKAGE_ROOT.length()).trim();

        boolean six = getGameVersion().getReleaseTarget().startsWith("1.16");
        boolean sev = getGameVersion().getReleaseTarget().startsWith("1.17");

        if (mixin.length() < 7 || mixin.startsWith("RALL") || mixin.startsWith("R.") || mixin.contains("MCVER") || mixin.equalsIgnoreCase("R1_16.Mixin")
                || (mixin.contains("R1_") && mixin.length() < 10)) {
            return false;
        }

        if (mixin.contains("1_16")) {
            if (six)
                logger.info("Applying mixin: " + mixin + "...");
            return six;
        }
        if (mixin.contains("1_17")) {
            if (sev)
                logger.info("Applying mixin: " + mixin + "...");
            return sev;
        }

        logger.info("Applying mixin: " + mixin + "...");
        return true;
    }

    public void wait(int ms, String s) {
        try { Thread.sleep(ms*10); } catch (InterruptedException e) {}
        if (s != null) logger.info(s);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        ArrayList<String> list = new ArrayList<>();

        String prefix = "";
        String[] forVer = {""};

        try {
            
            List<String> l2 = MixinList.list;
            for (String s : l2) {
                if (s.startsWith("#")) continue;
                if (s.startsWith("MCVER=")) forVer = new String[] {s.split("=")[1]};
                if (s.startsWith("PREFIX=")) prefix = s.split("=")[1];
                if (s.startsWith("MCVER=") && s.contains(",")) {
                    String vers = s.split("=")[1];
                    forVer = vers.split(",");
                }

                for (String mver : forVer) {
                    String pack = "R" + mver.replace('.', '_') + ".";
                    if (!list.contains( pack + prefix + s.trim() )) {
                        boolean should = shouldApply(MIXIN_PACKAGE_ROOT + "R" + mver.replace('.', '_') + "." + prefix + s.trim(), "");
                        if (should) list.add(pack + prefix + s.trim());
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error: Try reinstalling iCommon.");
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode target, String mixinClassName, IMixinInfo info) {
    }

}