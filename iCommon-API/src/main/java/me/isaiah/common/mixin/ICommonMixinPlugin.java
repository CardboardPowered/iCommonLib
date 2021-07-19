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
import me.isaiah.common.cmixin.SupportedVersion;
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
        /*try {
            Method m = SharedConstants.class.getMethod(FabricLoader.getInstance().isDevelopmentEnvironment() ? "createGameVersion" : "method_36208");
            m.invoke(null, (Object[]) null); // 1.17
        } catch (Exception e) {
            // 1.16
        }*/

        try { 
            return SharedConstants.getGameVersion();
        } catch (IllegalStateException ver117) {
            return MinecraftVersion.create();
        }
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (!start) {
            GameVersion ver = getGameVersion();
            logger.info("=======================================================");
            logger.info(" iCommon - Isaiah's common library for Fabric Mods.");
            logger.info(" Copyright (c) 2018-2021. Running on MC " + ver.getReleaseTarget() + " (" + ver.getProtocolVersion() + ")");
            logger.info("=======================================================");
            wait(50, "Loading...");
            wait(200, null);
        }
        start = true;

        return shouldApply(mixinClassName, "1");
    }
    
    public boolean shouldApply(String mixinClassName, String output) {
        try {
            SupportedVersion sv = Class.forName(mixinClassName).getAnnotation(SupportedVersion.class);
            String mixin = mixinClassName.substring(MIXIN_PACKAGE_ROOT.length());
            if (null != sv) {
                String s = SharedConstants.getGameVersion().getReleaseTarget();
                for (String val : sv.value()) {
                    if (val.equalsIgnoreCase(s)) {
                        logger.info("Applying versioned " + val + " mixin " + mixin + "..." + output);
                        return true;
                    }
                }
            } else {
                logger.info("Applying mixin: " + mixin + "...");
                return true;
            }
        } catch (ClassNotFoundException e) {
            return false;
        }

        return false;
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
            //List<String> l2 = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("icommon-mixin-list.txt").toURI()), Charset.defaultCharset());
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