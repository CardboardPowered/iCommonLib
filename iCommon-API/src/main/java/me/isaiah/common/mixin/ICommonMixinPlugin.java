package me.isaiah.common.mixin;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import me.isaiah.common.GameVersion;

import me.isaiah.common.cmixin.MixinList;
import me.isaiah.common.event.EventRegistery;
import me.isaiah.common.event.ShouldApplyMixinEvent;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.MinecraftVersion;
import net.minecraft.util.JsonHelper;

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
        if (null == GameVersion.INSTANCE) {
            GameVersion.INSTANCE = create();
        }
        return GameVersion.INSTANCE;
    }

    /*
     */
    public static GameVersion create() {
        try (InputStream inputStream = MinecraftVersion.class.getResourceAsStream("/version.json");){
            if (inputStream == null) return null;
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);){
                return new GameVersion(JsonHelper.deserialize(inputStreamReader));
            }
        } catch (Exception exception) {
            throw new IllegalStateException("Bad version info", exception);
        }
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!start) {
            GameVersion ver = getGameVersion();
            logger.info("=======================================================");
            logger.info(" iCommon - Isaiah's common library for mods.");
            logger.info(" Copyright (c) 2018-2023 Isaiah. Running on MC " + ver.getReleaseTarget());
            logger.info("=======================================================");
        }
        start = true;

        return shouldApply(mixinClassName, "1");
    }

    public boolean shouldApply(String mixinClassName, String output) {
        String mixin = mixinClassName.substring(MIXIN_PACKAGE_ROOT.length()).trim();

        String relTar = getGameVersion().getReleaseTarget();
        boolean six = relTar.startsWith("1.16");
        boolean sev = relTar.startsWith("1.17");
        boolean r8  = relTar.startsWith("1.18");
        boolean r9  = relTar.startsWith("1.19");
		boolean r20 = relTar.startsWith("1.20");

        if (mixin.length() < 7 || mixin.startsWith("RALL") || mixin.startsWith("R.") || mixin.contains("MCVER") || mixin.equalsIgnoreCase("R1_16.Mixin")
                || (mixin.contains("R1_") && mixin.length() < 12)) {
            return false;
        }

        ShouldApplyMixinEvent ev = (ShouldApplyMixinEvent)
                EventRegistery.invoke(ShouldApplyMixinEvent.class, new ShouldApplyMixinEvent(mixin));

        if (ev.isCanceled()) {
            return false;
        }
        
        boolean has_lithium = FabricLoader.getInstance().isModLoaded("lithium");

        if (mixin.contains("CampfireBlockEntity")) {
        	if (has_lithium) {
        		logger.info("Lithium detected");
        		return false;
        	}
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

        if (mixin.contains("1_18")) {
            if (r8)
                logger.info("Applying mixin: " + mixin + "...");
            return r8;
        }
        
        if (mixin.contains("1_19")) {
            if (r9)
                logger.info("Applying mixin: " + mixin + "...");
            return r9;
        }

        if (mixin.contains("1_20")) {
            if (r20)
                logger.info("Applying mixin: " + mixin + "...");
            return r20;
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