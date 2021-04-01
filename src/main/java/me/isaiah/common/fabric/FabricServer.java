package me.isaiah.common.fabric;

import me.isaiah.common.IServer;
import net.minecraft.server.MinecraftServer;

public class FabricServer implements IServer {

    private MinecraftServer mc;
    public FabricServer(MinecraftServer mc) {
        this.mc = mc;
    }

    @Override
    public String getMinecraftVersion() {
        return mc.getVersion();
    }

}
