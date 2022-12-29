package me.isaiah.common.fabric;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.bridge.game.GameVersion;

import me.isaiah.common.IServer;
import me.isaiah.common.Side;
import me.isaiah.common.cmixin.IMixinMinecraftServer;
import me.isaiah.common.world.IWorld;
import net.minecraft.MinecraftVersion;
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
            return SharedConstants.getGameVersion();
        } catch (IllegalStateException ver117) {
            return MinecraftVersion.create();
        }
    }

    @Override
    public Side getSide() {
        return mc.isDedicated() ? Side.SERVER : Side.CLIENT;
    }

    @Override
    public MinecraftServer getMinecraft() {
        return mc;
    }

    /**
     * See IMixinMinecraftServer.get_uuid_from_profile
     */
	@Override
	public UUID get_uuid_from_profile(GameProfile profile) {
		return ((IMixinMinecraftServer)mc).get_uuid_from_profile(profile);
	}

}