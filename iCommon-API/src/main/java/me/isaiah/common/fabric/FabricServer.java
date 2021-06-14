package me.isaiah.common.fabric;

import java.util.Collection;
import java.util.HashMap;

import me.isaiah.common.IServer;
import me.isaiah.common.world.IWorld;
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

}
