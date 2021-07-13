package me.isaiah.common.fabric;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import com.mojang.bridge.game.GameVersion;

import me.isaiah.common.IServer;
import me.isaiah.common.world.IWorld;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.server.MinecraftServer;

public class FabricServer implements IServer {

    private MinecraftServer mc;
    public HashMap<String, IWorld> worlds;

    public FabricServer(MinecraftServer mc) {
        this.mc = mc;
        this.worlds = new HashMap<>();
    }

    public void world(IWorld icommon, String name) {
        worlds.put(name, icommon);
    }

    @Override
    public String getMinecraftVersion() {
        return mc.getVersion();
    }

    @Override
    public Collection<IWorld> getWorlds() {
        return worlds.values();
    }

    @Override
    public IWorld getWorld(String name) {
        return worlds.get(name);
    }

    @Override
    public int getProtocolVersion() {
        return getGameVersion().getProtocolVersion();
    }

    public static GameVersion getGameVersion() {
        try {
            Method m = SharedConstants.class.getMethod(FabricLoader.getInstance().isDevelopmentEnvironment() ? "createGameVersion" : "method_36208");
            m.invoke(null, (Object[]) null); // 1.17
        } catch (Exception e) {/* 1.16 */}

        return SharedConstants.getGameVersion();
    }

}