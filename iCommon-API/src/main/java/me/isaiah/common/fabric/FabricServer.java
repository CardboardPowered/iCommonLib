package me.isaiah.common.fabric;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import me.isaiah.common.GameVersion;
import net.minecraft.util.JsonHelper;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    public int getProtocolVersion() {
        return getGameVersion().getProtocolVersion();
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